package com.bill.imageloader;

import android.content.Context;
import android.view.View;

import com.bill.imageloader.config.GlobalConfig;
import com.bill.imageloader.config.ImageConfig;
import com.bill.imageloader.config.ImageContact;
import com.bill.imageloader.loader.ILoader;


/**
 * Created by Bill on 2017/11/3.
 */

public class ImageLoader {

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        GlobalConfig.init(context);
    }

    /**
     * 网络验证
     *
     * @param verify true:不加载
     */
    public static void setNetVerify(boolean verify) {
        ImageContact.netVerify = verify;
    }

    public static ImageConfig.ConfigBuilder with(Object requestContext) {
        return new ImageConfig.ConfigBuilder(requestContext);
    }

    private static ILoader getActualLoader() {
        return GlobalConfig.getLoader();
    }

    public void resume(Object targetContext) {
        getActualLoader().resume(targetContext);
    }

    public void pause(Object targetContext) {
        getActualLoader().pause(targetContext);
    }

    public void clear(View view) {
        getActualLoader().clear(view);
    }

    public void clearDiskCache() {
        getActualLoader().clearDiskCache();
    }

    public void clearMemory() {
        getActualLoader().clearMemory();
    }

    public void onLowMemory() {
        getActualLoader().onLowMemory();
    }

    public void trimMemory(int level) {
        getActualLoader().trimMemory(level);
    }

}
