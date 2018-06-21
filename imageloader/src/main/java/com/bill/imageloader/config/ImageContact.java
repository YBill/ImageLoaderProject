package com.bill.imageloader.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by Bill on 2017/11/3.
 */

public class ImageContact {

    public static final String HTTP = "http";
    public static final String GIF = ".gif";
    public static boolean netVerify = false; // 网络验证

    public static int dip2px(float dpValue) {
        final float scale = GlobalConfig.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Glide磁盘缓存
     *
     * @return
     */
    public static String getGlideImageCache() {
        String filePath = Environment.getExternalStorageDirectory() + "/ImageLoader/imgCache";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = null;
        return filePath;
    }

    public static boolean isGif(String url) {
        return (url != null && url.endsWith(GIF));
    }

    public static boolean isHttp(String url) {
        return (url != null && url.startsWith(HTTP));
    }
}
