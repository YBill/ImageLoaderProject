package com.bill.imageloader.config;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bill.imageloader.listener.BitmapListener;
import com.bill.imageloader.listener.LoaderListener;
import com.bill.imageloader.listener.OnProgressListener;


/**
 * Created by Bill on 2017/11/3.
 */

public class ImageConfig {

    public Object requestContext;
    public ImageView imageView;
    public Object load;
    public ImageMode.DiskCache diskCacheStrategy;
    public ImageMode.LoadPriority priority;
    public int placeholder;
    public int error;
    public Drawable placeholderDrawable;
    public Drawable errorDrawable;
    public boolean skip;
    public boolean preload;
    public boolean checkUrl;
    public ImageMode.ScaleMode scaleMode;
    public LoaderListener loaderListener;
    public OnProgressListener progressListener;
    public BitmapListener bitmapListener;
    public boolean forceDisplay;
    public int width;
    public int height;
    public int size;

    // transform
    public ImageMode.FilterType[] filterType;
    public ImageMode.CropMode cropMode;
    public int cropWidth, cropHeight;
    public int rectRoundRadius;
    public ImageMode.CornerMode cornerMode;
    public boolean needBlur;
    public int blurRadius;
    public float brightness;
    public int colorFilter;
    public float swirlRadius, swirlAngle, swirlX, swirlY;
    public float toonThreshold, toonQuantizationLevels;
    public float intensity;
    public float contrast;
    public float pixelationFilter;
    public float vignetteX, vignetteY, vignetteColor1, vignetteColor2, vignetteColor3, vignetteStart, vignetteEnd;
    public int kuwaharaRadius;

    public ImageConfig(ConfigBuilder builder) {
        this.requestContext = builder.requestContext;
        this.imageView = builder.imageView;
        this.load = builder.load;
        this.diskCacheStrategy = builder.diskCacheStrategy;
        this.priority = builder.priority;
        this.placeholder = builder.placeholder;
        this.error = builder.error;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.errorDrawable = builder.errorDrawable;
        this.skip = builder.skip;
        this.preload = builder.preload;
        this.checkUrl = builder.checkUrl;
        this.rectRoundRadius = builder.rectRoundRadius;
        this.cornerMode = builder.cornerMode;
        this.scaleMode = builder.scaleMode;
        this.loaderListener = builder.loaderListener;
        this.progressListener = builder.progressListener;
        this.bitmapListener = builder.bitmapListener;
        this.forceDisplay = builder.forceDisplay;
        this.width = builder.width;
        this.height = builder.height;
        this.size = builder.size;
        this.needBlur = builder.needBlur;
        this.blurRadius = builder.blurRadius;
    }

    private void show() {
        GlobalConfig.getLoader().show(this);
    }

    private void test() {
        GlobalConfig.getLoader().test(this);
    }

    /**
     * 下面用来内部用
     */
    private boolean isLoad = false; // Gif是否已经加载

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

    public static class ConfigBuilder {

        private Object requestContext;
        private ImageView imageView;
        private Object load;
        private ImageMode.DiskCache diskCacheStrategy = ImageMode.DiskCache.DATA;
        private ImageMode.LoadPriority priority = ImageMode.LoadPriority.LOW;
        private int placeholder = -1;
        private int error = -1;
        private Drawable placeholderDrawable;
        private Drawable errorDrawable;
        private boolean skip = true;
        private boolean preload = false;
        private boolean checkUrl = false;
        private int rectRoundRadius;
        private ImageMode.CornerMode cornerMode = ImageMode.CornerMode.ALL;
        private ImageMode.ScaleMode scaleMode;
        private LoaderListener loaderListener;
        private OnProgressListener progressListener;
        private BitmapListener bitmapListener;
        private boolean forceDisplay;
        private int width = -1;
        private int height = -1;
        private int size = -1;
        private boolean needBlur;
        private int blurRadius;

        /**
         * Activity or android.support.v4.app.Fragment or Context or any
         *
         * @param requestContext
         */
        public ConfigBuilder(Object requestContext) {
            this.requestContext = requestContext;
        }

        /**
         * 加载资源
         *
         * @param load
         * @return
         */
        public ConfigBuilder load(Object load) {
            this.load = load;
            return this;
        }

        /**
         * 磁盘缓存，默认DATA
         *
         * @param diskCacheStrategy
         * @return
         */
        public ConfigBuilder diskCacheStrategy(ImageMode.DiskCache diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        /**
         * 优先策略，默认LOW
         *
         * @param priority
         * @return
         */
        public ConfigBuilder priority(ImageMode.LoadPriority priority) {
            this.priority = priority;
            return this;
        }

        /**
         * load占位图
         *
         * @param placeholder
         * @return
         */
        public ConfigBuilder placeholder(@DrawableRes int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        /**
         * 失败占位图
         *
         * @param error
         * @return
         */
        public ConfigBuilder error(@DrawableRes int error) {
            this.error = error;
            return this;
        }

        /**
         * load占位图
         *
         * @param placeholder
         * @return
         */
        public ConfigBuilder placeholder(Drawable placeholder) {
            this.placeholderDrawable = placeholder;
            return this;
        }

        /**
         * 失败占位图
         *
         * @param error
         * @return
         */
        public ConfigBuilder error(Drawable error) {
            this.errorDrawable = error;
            return this;
        }

        /**
         * 跳过内存缓存,默认true
         *
         * @param skip
         * @return
         */
        public ConfigBuilder skipMemoryCache(boolean skip) {
            this.skip = skip;
            return this;
        }

        /**
         * 开启检查是否已经加载过该图片,默认false
         *
         * @param checkUrl
         * @return
         */
        public ConfigBuilder checkUrl(boolean checkUrl) {
            this.checkUrl = checkUrl;
            return this;
        }

        /**
         * 设置圆角（px）
         *
         * @param rectRoundRadius
         * @return
         */
        public ConfigBuilder rectRoundRadius(int rectRoundRadius) {
            this.rectRoundRadius = rectRoundRadius;
            return this;
        }

        /**
         * 设置圆角的模式，默认ImageMode.CornerMode.ALL(四个角)
         *
         * @param cornerMode
         * @return
         */
        public ConfigBuilder cornerMode(ImageMode.CornerMode cornerMode) {
            this.cornerMode = cornerMode;
            return this;
        }

        /**
         * 设置scaleType
         *
         * @param scaleMode
         * @return
         */
        public ConfigBuilder scaleType(ImageMode.ScaleMode scaleMode) {
            this.scaleMode = scaleMode;
            return this;
        }

        /**
         * 加载图片回调
         *
         * @param loaderListener
         * @return
         */
        public ConfigBuilder listener(LoaderListener loaderListener) {
            this.loaderListener = loaderListener;
            return this;
        }

        /**
         * 网络Gif图加载进度回调
         *
         * @param progressListener
         * @return
         */
        public ConfigBuilder progress(OnProgressListener progressListener) {
            this.progressListener = progressListener;
            return this;
        }

        /**
         * 强制加载图片
         *
         * @param forceDisplay
         * @return
         */
        public ConfigBuilder forceDisplay(boolean forceDisplay) {
            this.forceDisplay = forceDisplay;
            return this;
        }

        /**
         * 设置大小
         *
         * @param width
         * @param height
         * @return
         */
        public ConfigBuilder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        /**
         * 设置大小
         *
         * @param size
         * @return
         */
        public ConfigBuilder size(int size) {
            this.size = size;
            return this;
        }

        /**
         * 是否需要模糊，默认false
         *
         * @param needBlur
         * @return
         */
        public ConfigBuilder blur(boolean needBlur) {
            this.needBlur = needBlur;
            return this;
        }

        /**
         * 模糊半径
         *
         * @param blurRadius
         * @return
         */
        public ConfigBuilder blurRadius(int blurRadius) {
            this.blurRadius = blurRadius;
            return this;
        }

        /**
         * 设置后会执行into(listener)，不需要imageView,会将Bitmap回调回去
         *
         * @param bitmapListener
         * @return
         */
        public void intoListener(BitmapListener bitmapListener) {
            this.bitmapListener = bitmapListener;
            new ImageConfig(this).show();
        }

        /**
         * 待显示ImageView
         *
         * @param imageView
         */
        public void into(ImageView imageView) {
            this.imageView = imageView;
            new ImageConfig(this).show();
        }

        @Deprecated
        public void test(ImageView imageView) {
            this.imageView = imageView;
            new ImageConfig(this).test();
        }

        /**
         * 预加载or加载，默认false
         *
         * @return
         */
        public void preload() {
            this.preload = true;
            new ImageConfig(this).show();
        }

    }

}
