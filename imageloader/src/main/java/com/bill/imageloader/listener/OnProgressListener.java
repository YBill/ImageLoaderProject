package com.bill.imageloader.listener;

/**
 * Created by Bill on 2017/11/3.
 */

public interface OnProgressListener {
    void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone);
}
