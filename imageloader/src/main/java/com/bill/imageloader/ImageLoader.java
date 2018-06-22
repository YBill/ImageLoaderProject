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

    /**
     * 更换图片加载器
     *
     * @param loader
     */
    public static void setLoader(ILoader loader) {
        GlobalConfig.setLoader(loader);
    }

    public static void resume(Object targetContext) {
        getActualLoader().resume(targetContext);
    }

    /**
     * 中断图片加载
     *
     * @param targetContext 1、如果当前 targetContext 是 Activity，那么依附它的所有 fragments 的请求都会中止
     *                      2、如果当前 targetContext 是 Fragment，那么依附它的所有 childFragment 的请求都会中止
     *                      3、如果当前 targetContext 是 ApplicationContext，或者当前的 Fragment 处于 detached 状态，那么只有当前的 RequestManager 的请求会被中止
     */
    public static void pause(Object targetContext) {
        getActualLoader().pause(targetContext);
    }

    /**
     * 清除磁盘缓存
     */
    public static void clearDiskCache() {
        getActualLoader().clearDiskCache();
    }

    /**
     * 清除内存缓存
     */
    public static void clearMemory() {
        getActualLoader().clearMemory();
    }

    public static void onLowMemory() {
        getActualLoader().onLowMemory();
    }

    public static void trimMemory(int level) {
        getActualLoader().trimMemory(level);
    }

    public static void clear(View view) {
        getActualLoader().clear(view);
    }

}
