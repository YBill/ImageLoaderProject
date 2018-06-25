package com.bill.imageloader.config;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
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
    public int rectRoundRadius;
    public ImageMode.CornerMode cornerMode;
    public ImageMode.CropMode cropMode;
    public int cropWidth, cropHeight;
    public int blurRadius;
    public int colorFilter;
    public float intensity;
    public float toonThreshold, toonQuantizationLevels;
    public float contrast;
    public float brightness;
    public float pixelation;
    public float swirlRadius, swirlAngle, swirlX, swirlY;
    public float vignetteX, vignetteY, vignetteColorRed, vignetteColorGreen, vignetteColorBlue, vignetteStart, vignetteEnd;
    public int maskId;

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
        this.scaleMode = builder.scaleMode;
        this.loaderListener = builder.loaderListener;
        this.progressListener = builder.progressListener;
        this.bitmapListener = builder.bitmapListener;
        this.forceDisplay = builder.forceDisplay;
        this.width = builder.width;
        this.height = builder.height;
        this.size = builder.size;

        this.filterType = builder.filterType;
        this.rectRoundRadius = builder.rectRoundRadius;
        this.cornerMode = builder.cornerMode;
        this.cropWidth = builder.cropWidth;
        this.cropHeight = builder.cropHeight;
        this.cropMode = builder.cropMode;
        this.blurRadius = builder.blurRadius;
        this.colorFilter = builder.colorFilter;
        this.intensity = builder.intensity;
        this.toonThreshold = builder.toonThreshold;
        this.toonQuantizationLevels = builder.toonQuantizationLevels;
        this.contrast = builder.contrast;
        this.brightness = builder.brightness;
        this.pixelation = builder.pixelation;
        this.swirlRadius = builder.swirlRadius;
        this.swirlAngle = builder.swirlAngle;
        this.swirlX = builder.swirlX;
        this.swirlY = builder.swirlY;
        this.vignetteX = builder.vignetteX;
        this.vignetteY = builder.vignetteY;
        this.vignetteColorRed = builder.vignetteColorRed;
        this.vignetteColorGreen = builder.vignetteColorGreen;
        this.vignetteColorBlue = builder.vignetteColorBlue;
        this.vignetteStart = builder.vignetteStart;
        this.vignetteEnd = builder.vignetteEnd;
        this.maskId = builder.maskId;
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
        private ImageMode.DiskCache diskCacheStrategy = ImageMode.DiskCache.AUTOMATIC;
        private ImageMode.LoadPriority priority = ImageMode.LoadPriority.LOW;
        private int placeholder = -1;
        private int error = -1;
        private Drawable placeholderDrawable;
        private Drawable errorDrawable;
        private boolean skip = false;
        private boolean preload = false;
        private boolean checkUrl = false;
        private ImageMode.ScaleMode scaleMode;
        private LoaderListener loaderListener;
        private OnProgressListener progressListener;
        private BitmapListener bitmapListener;
        private boolean forceDisplay;
        private int width = -1;
        private int height = -1;
        private int size = -1;

        private ImageMode.FilterType[] filterType;
        private int rectRoundRadius;
        private ImageMode.CornerMode cornerMode = ImageMode.CornerMode.ALL;
        private ImageMode.CropMode cropMode = ImageMode.CropMode.CENTER;
        private int cropWidth = 0, cropHeight = 0;
        private int blurRadius = 25;
        private int colorFilter;
        private float intensity = 1.0f;
        private float toonThreshold = .2f, toonQuantizationLevels = 10.0f;
        private float contrast = 1.0f;
        private float brightness = 0.0f;
        private float pixelation = 10.0f;
        private float swirlRadius = .5f, swirlAngle = 1.0f, swirlX = 0.5f, swirlY = 0.5f;
        private float vignetteX = 0.5f, vignetteY = 0.5f, vignetteColorRed = 0.0f, vignetteColorGreen = 0.0f, vignetteColorBlue = 0.0f,
                vignetteStart = 0.0f, vignetteEnd = 0.75f;
        private int maskId;

        /**
         * Activity or android.support.v4.app.Fragment or Context or any
         *
         * @param requestContext
         */
        public ConfigBuilder(Object requestContext) {
            this.requestContext = requestContext;
        }

        ///////////////////////transform start////////////////////////

        /**
         * 变换，滤镜  必填
         *
         * @param filterType
         * @return
         */
        public ConfigBuilder filterType(ImageMode.FilterType... filterType) {
            this.filterType = filterType;
            return this;
        }

        /**
         * 设置圆角（px）,必填
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
         * crop 大小（px） 必填
         *
         * @param cropWidth
         * @param cropHeight
         * @return
         */
        public ConfigBuilder cropSize(int cropWidth, int cropHeight) {
            this.cropWidth = cropWidth;
            this.cropHeight = cropHeight;
            return this;
        }

        /**
         * 裁剪部位，默认ImageMode.CropMode.CENTER
         *
         * @param cropMode
         * @return
         */
        public ConfigBuilder cropMode(ImageMode.CropMode cropMode) {
            this.cropMode = cropMode;
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
         * 滤镜颜色 必填
         *
         * @param colorFilter
         * @return
         */
        public ConfigBuilder colorFilter(@ColorRes int colorFilter) {
            this.colorFilter = colorFilter;
            return this;
        }

        /**
         * 水墨强度
         *
         * @param intensity def 1.0f
         * @return
         */
        public ConfigBuilder intensity(float intensity) {
            this.intensity = intensity;
            return this;
        }

        /**
         * 油画
         *
         * @param toonThreshold          def:.2f
         * @param toonQuantizationLevels def:10.0f
         * @return
         */
        public ConfigBuilder toon(float toonThreshold, float toonQuantizationLevels) {
            this.toonThreshold = toonThreshold;
            this.toonQuantizationLevels = toonQuantizationLevels;
            return this;
        }

        /**
         * 锐化对比度
         *
         * @param contrast def：1.0f（正常情况）   取值：0.0-4.0
         * @return
         */
        public ConfigBuilder contrast(float contrast) {
            this.contrast = contrast;
            return this;
        }

        /**
         * 亮度值
         *
         * @param brightness def：0.0f（正常情况）   取值：-1.0-1.0
         * @return
         */
        public ConfigBuilder brightness(float brightness) {
            this.brightness = brightness;
            return this;
        }

        /**
         * 像素密度
         *
         * @param pixelation def：10.0f
         * @return
         */
        public ConfigBuilder pixelation(float pixelation) {
            this.pixelation = pixelation;
            return this;
        }

        /**
         * 旋涡
         *
         * @param swirlRadius 半径 0.0 to 1.0, default 0.5
         * @param swirlAngle  角度  minimum 0.0, default 1.0
         * @param swirlX      中心点坐标x     default 0.5
         * @param swirlY      中心点坐标y    default 0.5
         * @return
         */
        public ConfigBuilder swirl(float swirlRadius, float swirlAngle, float swirlX, float swirlY) {
            this.swirlRadius = swirlRadius;
            this.swirlAngle = swirlAngle;
            this.swirlX = swirlX;
            this.swirlY = swirlY;
            return this;
        }

        /**
         * 映晕
         *
         * @param vignetteX          中心点坐标x     default 0.5
         * @param vignetteY          中心点坐标y    default 0.5
         * @param vignetteColorRed   红  default 0.0
         * @param vignetteColorGreen 绿 default 0.0
         * @param vignetteColorBlue  蓝  default 0.0
         * @param vignetteStart      default 0.0
         * @param vignetteEnd        default 0.75
         * @return
         */
        public ConfigBuilder vignette(float vignetteX, float vignetteY, float vignetteColorRed,
                                      float vignetteColorGreen, float vignetteColorBlue, float vignetteStart, float vignetteEnd) {
            this.vignetteX = vignetteX;
            this.vignetteY = vignetteY;
            this.vignetteColorRed = vignetteColorRed;
            this.vignetteColorGreen = vignetteColorGreen;
            this.vignetteColorBlue = vignetteColorBlue;
            this.vignetteStart = vignetteStart;
            this.vignetteEnd = vignetteEnd;
            return this;
        }

        /**
         * 必填 可以是图片和.9图资源
         *
         * @param maskId
         * @return
         */
        public ConfigBuilder maskId(@DrawableRes int maskId) {
            this.maskId = maskId;
            return this;
        }

        ////////////////////////transform end///////////////////////

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
         * 磁盘缓存，默认AUTOMATIC
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

        /**
         * 预加载or加载，默认false
         *
         * @return
         */
        public void preload() {
            this.preload = true;
            new ImageConfig(this).show();
        }

        @Deprecated
        public void test(ImageView imageView) {
            this.imageView = imageView;
            new ImageConfig(this).test();
        }

    }

}
