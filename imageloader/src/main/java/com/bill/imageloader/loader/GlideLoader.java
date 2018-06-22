package com.bill.imageloader.loader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bill.imageloader.R;
import com.bill.imageloader.config.GlideApp;
import com.bill.imageloader.config.GlideRequest;
import com.bill.imageloader.config.GlideRequests;
import com.bill.imageloader.config.GlobalConfig;
import com.bill.imageloader.config.ImageConfig;
import com.bill.imageloader.config.ImageContact;
import com.bill.imageloader.config.ImageMode;
import com.bill.imageloader.db.ImageDbManager;
import com.bill.imageloader.listener.OnProgressListener;
import com.bill.imageloader.progress.ProgressManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by Bill on 2017/11/3.
 */

public class GlideLoader implements ILoader {

    private Handler mMainThreadHandler;

    @Override
    public void init(Context context) {
        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void test(ImageConfig config) {
        // 测试glide内容
        Glide.with((Activity) config.requestContext)
                .load(config.load)
                .into(config.imageView);


    }

    @Override
    public void show(final ImageConfig config) {
        if (config.checkUrl && checkIsLoad(config.load, config.imageView)) {
            return;
        }

        boolean noDownloadImg = false;
        if (!config.forceDisplay) {
            if (ImageContact.netVerify) {
                noDownloadImg = true;
            }
        }

        GlideRequests glideRequests = getRequests(config);

        GlideRequest glideRequest;
        if (config.bitmapListener != null) {
            glideRequest = glideRequests.asBitmap();
        } else {
            glideRequest = glideRequests.asDrawable();
        }

        glideRequest.load(config.load).onlyRetrieveFromCache(noDownloadImg);
        setImageAttribute(config, glideRequest);

        if (config.preload)
            glideRequest.preload();
        else {
            if (config.bitmapListener != null) {
                glideRequest.into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        config.bitmapListener.onResourceReady(resource);
                    }
                });
            } else {
                glideRequest.into(config.imageView);
            }
        }

        if (config.progressListener != null) {
            addProgressListener(config);
        }
    }

    private GlideRequests getRequests(ImageConfig config) {
        GlideRequests glideRequests;
        Object requestContext = config.requestContext;
        if (requestContext instanceof Activity) {
            glideRequests = GlideApp.with((Activity) requestContext);
        } else if (requestContext instanceof Fragment) {
            glideRequests = GlideApp.with((Fragment) requestContext);
        } else if (requestContext instanceof Context) {
            glideRequests = GlideApp.with((Context) requestContext);
        } else {
            glideRequests = GlideApp.with(GlobalConfig.getContext());
        }
        return glideRequests;
    }

    @SuppressLint("CheckResult")
    private <T> void setImageAttribute(final ImageConfig config, GlideRequest<T> glideRequest) {
        if (config.placeholder > -1)
            glideRequest.placeholder(config.placeholder);
        if (config.placeholderDrawable != null)
            glideRequest.placeholder(config.placeholderDrawable);
        if (config.error > -1)
            glideRequest.placeholder(config.error);
        if (config.errorDrawable != null)
            glideRequest.placeholder(config.errorDrawable);

        if (config.size > 0) {
            glideRequest.override(config.size);
        }
        if (config.width > 0 && config.height > 0) {
            glideRequest.override(config.width, config.height);
        }

        if (config.scaleMode != null) {
            switch (config.scaleMode) {
                case FIT_CENTER:
                    glideRequest.fitCenter();
                    break;
                case CENTER_CROP:
                    glideRequest.centerCrop();
                    break;
                case CENTER_INSIDE:
                    glideRequest.centerInside();
                    break;
                case CIRCLE_CROP:
                    glideRequest.circleCrop();
                    break;
            }
        }

        transform(config, glideRequest);

        glideRequest
                .diskCacheStrategy(config.diskCacheStrategy.getStrategy())
                .priority(config.priority.getPriority())
                .skipMemoryCache(config.skip)
                .listener(new RequestListener<T>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<T> target, boolean isFirstResource) {
                        if (config.loaderListener != null)
                            config.loaderListener.onFail();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(T resource, Object model, Target<T> target, DataSource dataSource, boolean isFirstResource) {
                        saveImg(config);
                        if (config.loaderListener != null)
                            config.loaderListener.onSuccess();
                        return false;
                    }
                });
    }

    private <T> void transform(ImageConfig config, GlideRequest<T> glideRequest) {
        if (config.filterType == null) {
            return;
        }

        int length = config.filterType.length;

        if (length == 0) {
            return;
        }

        List<Transformation> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            ImageMode.FilterType filterType = config.filterType[i];

            if (ImageMode.FilterType.RoundedCorners == filterType) {
                // 圆角
                int radius = ImageContact.dip2px(config.rectRoundRadius);
                list.add(new RoundedCornersTransformation(radius, 0, config.cornerMode.getCorner()));
            }
            if (ImageMode.FilterType.CropCircle == filterType) {
                // 圆
                list.add(new CropCircleTransformation());
            }
            if (ImageMode.FilterType.CropSquare == filterType) {
                // 正方形
                list.add(new CropSquareTransformation());
            }
            if (ImageMode.FilterType.Crop == filterType) {
                // Crop
                int cropWidth = ImageContact.dip2px(config.cropWidth);
                int cropHeight = ImageContact.dip2px(config.cropHeight);
                list.add(new CropTransformation(cropWidth, cropHeight, config.cropMode.getCrop()));
            }
            if (ImageMode.FilterType.Blur == filterType) {
                // 高斯模糊
                list.add(new BlurTransformation(config.blurRadius));
            }
            if (ImageMode.FilterType.ColorFilter == filterType) {
                // 滤镜
                list.add(new ColorFilterTransformation(config.colorFilter));
            }
            if (ImageMode.FilterType.Grayscale == filterType) {
                // 黑白
                list.add(new GrayscaleTransformation());
            }
            if (ImageMode.FilterType.Sepia == filterType) {
                // 水墨画
                list.add(new SepiaFilterTransformation(config.intensity));
            }
            if (ImageMode.FilterType.Toon == filterType) {
                //  油画
                list.add(new ToonFilterTransformation(config.toonThreshold, config.toonQuantizationLevels));
            }
            if (ImageMode.FilterType.Contrast == filterType) {
                // 锐化
                list.add(new ContrastFilterTransformation(config.contrast));
            }
            if (ImageMode.FilterType.Brightness == filterType) {
                // 亮度
                list.add(new BrightnessFilterTransformation(config.brightness));
            }
            if (ImageMode.FilterType.Sketch == filterType) {
                // 素描
                list.add(new SketchFilterTransformation());
            }
            if (ImageMode.FilterType.Pixelation == filterType) {
                // 马赛克
                list.add(new PixelationFilterTransformation(config.pixelation));
            }
            if (ImageMode.FilterType.Invert == filterType) {
                // 胶片
                list.add(new InvertFilterTransformation());
            }
            if (ImageMode.FilterType.Swirl == filterType) {
                // 旋涡
                list.add(new SwirlFilterTransformation(config.swirlRadius, config.swirlAngle, new PointF(config.swirlX, config.swirlY)));
            }
            if (ImageMode.FilterType.Vignette == filterType) {
                // 晕映
                list.add(new VignetteFilterTransformation(new PointF(config.vignetteX, config.vignetteY),
                        new float[]{config.vignetteColorRed, config.vignetteColorGreen, config.vignetteColorBlue}, config.vignetteStart, config.vignetteEnd));
            }
            if (ImageMode.FilterType.Mask == filterType) {
                // 遮罩
                list.add(new MaskTransformation(config.maskId));
            }

        }

        int listLength = list.size();
        if (listLength > 0) {
            Transformation[] transformation = new Transformation[listLength];
            for (int i = 0; i < listLength; i++) {
                transformation[i] = list.get(i);
            }

            glideRequest.transforms(transformation);
        }
    }

    private void addProgressListener(final ImageConfig config) {
        if (!(config.load instanceof String)) {
            return;
        }
        final String url = (String) config.load;
        if (!ImageContact.isHttp(url)) return;
        if (!ImageContact.isGif(url)) return;
        if (checkImgExist(url)) return;

        OnProgressListener internalProgressListener = new OnProgressListener() {
            @Override
            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone) {
                if (totalBytes == 0) return;
                if (!url.equals(imageUrl)) return;

                mainThreadCallback(url, bytesRead, totalBytes, isDone, config.progressListener);

                if (isDone) {
                    ProgressManager.removeProgressListener(this);
                }
            }
        };
        ProgressManager.addProgressListener(internalProgressListener);
    }

    private void mainThreadCallback(final String imageUrl, final long bytesRead, final long totalBytes, final boolean isDone, final OnProgressListener listener) {
        mMainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.onProgress(imageUrl, bytesRead, totalBytes, isDone);
                }
            }
        });
    }

    private boolean checkIsLoad(Object url, ImageView imageView) {
        if (imageView == null) {
            return false;
        }
        boolean isLoad = true;

        if (url instanceof String) {
            String urlStr = (String) url;
            if (!urlStr.equals(imageView.getTag(R.id.image_loader_uri_tag_id))) {
                isLoad = false;
                if (urlStr.startsWith(ImageContact.HTTP))
                    imageView.setTag(R.id.image_loader_uri_tag_id, url);
            }
        } else {
            isLoad = false;
        }

        return isLoad;
    }

    private void saveImg(ImageConfig config) {
        if (config.diskCacheStrategy == ImageMode.DiskCache.NONE) {
            return;
        }
        if (config.load instanceof String) {
            String url = (String) config.load;
            if (ImageContact.isGif(url)) {
                ImageDbManager.getInstance().insertImage(url);
            }
        }
    }

    private void delImg() {
        ImageDbManager.getInstance().deleteImage();
    }

    @Override
    public void resume(Object targetContext) {
        /**
         * 递归重启所有 RequestManager 下的所有 request。在 Glide 中源码中没有用到，暴露给开发者的接口。
         */
        if (targetContext instanceof Activity) {
            Glide.with((Activity) targetContext).resumeRequestsRecursive();
        } else if (targetContext instanceof Fragment) {
            Glide.with((Fragment) targetContext).resumeRequestsRecursive();
        } else {
            Glide.with(GlobalConfig.getContext()).resumeRequestsRecursive();
        }
    }

    @Override
    public void pause(Object targetContext) {
        /**
         * 递归所有 childFragments 的 RequestManager 的pauseRequest方法。
         * 在 Glide 中源码中没有用到，暴露给开发者的接口。
         * childFragments 表示那些依赖当前 Activity 或者 Fragment 的所有 fragments
         * 1、如果当前 Context 是 Activity，那么依附它的所有 fragments 的请求都会中止
         * 2、如果当前 Context 是 Fragment，那么依附它的所有 childFragment 的请求都会中止
         * 3、如果当前的 Context 是 ApplicationContext，或者当前的 Fragment 处于 detached 状态，那么只有当前的 RequestManager 的请求会被中止
         * */
        if (targetContext instanceof Activity) {
            Glide.with((Activity) targetContext).pauseRequestsRecursive();
        } else if (targetContext instanceof Fragment) {
            Glide.with((Fragment) targetContext).pauseRequestsRecursive();
        } else {
            Glide.with(GlobalConfig.getContext()).pauseRequestsRecursive();
        }
    }

    @Override
    public void clear(View view) {
        Glide.with(GlobalConfig.getContext()).clear(view);
    }

    @Override
    public void clearDiskCache() {
        // 清除磁盘缓存,必须运行在子线程
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        realClearDiskCache();
                    }
                }).start();
            } else {
                realClearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void realClearDiskCache() {
        Glide.get(GlobalConfig.getContext()).clearDiskCache();
        delImg();
    }

    @Override
    public void clearMemory() {
        // 清除内存缓存,必须运行在主线程
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(GlobalConfig.getContext()).clearMemory();
            } else {
                mMainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(GlobalConfig.getContext()).clearMemory();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLowMemory() {
        Glide.get(GlobalConfig.getContext()).onLowMemory();
    }

    @Override
    public void trimMemory(int level) {
        Glide.get(GlobalConfig.getContext()).onTrimMemory(level);
    }

    @Override
    public String getDiskCacheFolder() {
        String path;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = ImageContact.getGlideImageCache();
        } else {
            path = GlobalConfig.getContext().getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        }
        return path;
    }

    @Override
    public boolean checkImgExist(String url) {
        return ImageDbManager.getInstance().checkImageExist(url);
    }

}