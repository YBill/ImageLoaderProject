package com.bill.imageloader.config;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Bill on 2017/11/3.
 */

public class ImageMode {

    /**
     * 硬盘缓存策略
     */
    public enum DiskCache {
        ALL(DiskCacheStrategy.ALL),//使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE缓存本地数据
        NONE(DiskCacheStrategy.NONE),//不保存数据到缓存
        DATA(DiskCacheStrategy.DATA),//仅在将数据解码之前，将检索到的数据直接写入磁盘缓存
        RESOURCE(DiskCacheStrategy.RESOURCE),//资源被解码后将资源写入磁盘
        AUTOMATIC(DiskCacheStrategy.AUTOMATIC);//智能选择缓存

        private DiskCacheStrategy strategy;

        DiskCache(DiskCacheStrategy strategy) {
            this.strategy = strategy;
        }

        public DiskCacheStrategy getStrategy() {
            return strategy;
        }
    }

    /**
     * 加载优先级策略
     */
    public enum LoadPriority {
        LOW(Priority.LOW),
        NORMAL(Priority.NORMAL),
        HIGH(Priority.HIGH),
        IMMEDIATE(Priority.IMMEDIATE);

        private Priority priority;

        LoadPriority(Priority priority) {
            this.priority = priority;
        }

        public Priority getPriority() {
            return priority;
        }
    }

    public enum ScaleMode {
        CENTER_CROP, // ImageView会填满，图片可能会被裁剪
        FIT_CENTER, // 图片会显示完整，可能不会填满ImageView,图片大小=ImageView大小
        CENTER_INSIDE, // 图片大小<=ImageView大小&&图片大小<=原始图片大小
        CIRCLE_CROP // 圆
    }

    /**
     * 圆角类型
     */
    public enum CornerMode {
        ALL(RoundedCornersTransformation.CornerType.ALL),
        TOP_LEFT(RoundedCornersTransformation.CornerType.TOP_LEFT),
        TOP_RIGHT(RoundedCornersTransformation.CornerType.TOP_RIGHT),
        BOTTOM_LEFT(RoundedCornersTransformation.CornerType.BOTTOM_LEFT),
        BOTTOM_RIGHT(RoundedCornersTransformation.CornerType.BOTTOM_RIGHT),
        TOP(RoundedCornersTransformation.CornerType.TOP),
        BOTTOM(RoundedCornersTransformation.CornerType.BOTTOM),
        LEFT(RoundedCornersTransformation.CornerType.LEFT),
        RIGHT(RoundedCornersTransformation.CornerType.RIGHT),
        OTHER_TOP_LEFT(RoundedCornersTransformation.CornerType.OTHER_TOP_LEFT),
        OTHER_TOP_RIGHT(RoundedCornersTransformation.CornerType.OTHER_TOP_RIGHT),
        OTHER_BOTTOM_LEFT(RoundedCornersTransformation.CornerType.OTHER_BOTTOM_LEFT),
        OTHER_BOTTOM_RIGHT(RoundedCornersTransformation.CornerType.OTHER_BOTTOM_RIGHT),
        DIAGONAL_FROM_TOP_LEFT(RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT),
        DIAGONAL_FROM_TOP_RIGHT(RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_RIGHT);

        private RoundedCornersTransformation.CornerType cornerType;

        CornerMode(RoundedCornersTransformation.CornerType cornerType) {
            this.cornerType = cornerType;
        }

        public RoundedCornersTransformation.CornerType getCorner() {
            return cornerType;
        }

    }

    /**
     * 滤镜类型
     */
    public enum FilterType {
        RoundedCorners,
        CropCircle,
        CropSquare,
        Crop,
        Blur,
        ColorFilter,
        Grayscale,
        Sepia,
        Toon,
        Contrast,
        Brightness,
        Sketch,
        Pixelation,
        Invert,
        Swirl,
        Vignette,
        Mask,
    }

    /**
     * Crop方式
     */
    public enum CropMode {
        TOP(CropTransformation.CropType.TOP),
        CENTER(CropTransformation.CropType.CENTER),
        BOTTOM(CropTransformation.CropType.BOTTOM);

        private CropTransformation.CropType cropType;

        CropMode(CropTransformation.CropType cropType) {
            this.cropType = cropType;
        }

        public CropTransformation.CropType getCrop() {
            return cropType;
        }

    }

}
