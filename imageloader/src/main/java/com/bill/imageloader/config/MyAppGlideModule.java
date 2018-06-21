package com.bill.imageloader.config;

import android.content.Context;
import android.os.Environment;

import com.bill.imageloader.progress.ProgressManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by Bill on 2017/11/2.
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    private int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存

    /**
     * 通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     *
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int diskSize = 250 * 1024 * 1024; // Glide 默认大小
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String downloadDirectoryPath = ImageContact.getGlideImageCache();
            builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, diskSize));
        }

        //重新设置内存限制
//        builder.setMemoryCache(new LruResourceCache(memorySize));
//        builder.setBitmapPool(new LruBitmapPool(memorySize));

    }

    /**
     * 将加载器替换为o3拦截网络进度
     *
     * @param context
     * @param registry
     */
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(ProgressManager.getOkHttpClient()));
    }

    /**
     * 清单解析的开启
     * <p>
     * 这里不开启，避免添加相同的modules两次
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
