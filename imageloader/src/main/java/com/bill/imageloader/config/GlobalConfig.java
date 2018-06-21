package com.bill.imageloader.config;

import android.content.Context;

import com.bill.imageloader.loader.GlideLoader;
import com.bill.imageloader.loader.ILoader;


/**
 * Created by Bill on 2017/11/3.
 */

public class GlobalConfig {

    private static ILoader loader;
    private static Context context;

    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (GlobalConfig.class) {
                if (loader == null)
                    loader = new GlideLoader();
            }
        }
        return loader;
    }

    public static void setLoader(ILoader loader) {
        GlobalConfig.loader = loader;
    }

    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        GlobalConfig.context = context.getApplicationContext();
        getLoader().init(context);
    }

    public static Context getContext() {
        return context;
    }

}
