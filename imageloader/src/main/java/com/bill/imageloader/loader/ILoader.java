package com.bill.imageloader.loader;

import android.content.Context;
import android.view.View;

import com.bill.imageloader.config.ImageConfig;


/**
 * Created by Bill on 2017/11/3.
 */

public interface ILoader {

    void init(Context context);

    void show(ImageConfig config);

    void test(ImageConfig config);

    void resume(Object targetContext);

    void pause(Object targetContext);

    void clear(View view);

    void clearDiskCache();

    void clearMemory();

    void onLowMemory();

    void trimMemory(int level);

    String getDiskCacheFolder();

    boolean checkImgExist(String url);

}
