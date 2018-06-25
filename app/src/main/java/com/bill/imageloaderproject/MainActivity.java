package com.bill.imageloaderproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bill.imageloader.ImageLoader;
import com.bill.imageloader.config.ImageMode;
import com.bill.imageloader.listener.LoaderListener;
import com.bill.imageloader.listener.OnProgressListener;


public class MainActivity extends AppCompatActivity {

    private final static int COUNT = 5;
    private LinearLayout layout;
    private TextView progress;
    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;
    private ImageView imageView13;
    private ImageView imageView14;
    private ImageView imageView15;
    private ImageView imageView16;
    private ImageView imageView17;
    private ImageView imageView18;
    private ImageView imageView19;
    private ImageView imageView20;

    private void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                loadImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadImage();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                finish();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoader.init(this);

        layout = findViewById(R.id.ll_group);
        progress = findViewById(R.id.tv_progress);
        imageView0 = findViewById(R.id.iv_0);
        imageView1 = findViewById(R.id.iv_1);
        imageView2 = findViewById(R.id.iv_2);
        imageView3 = findViewById(R.id.iv_3);
        imageView4 = findViewById(R.id.iv_4);
        imageView5 = findViewById(R.id.iv_5);
        imageView6 = findViewById(R.id.iv_6);
        imageView7 = findViewById(R.id.iv_7);
        imageView8 = findViewById(R.id.iv_8);
        imageView9 = findViewById(R.id.iv_9);
        imageView10 = findViewById(R.id.iv_10);
        imageView11 = findViewById(R.id.iv_11);
        imageView12 = findViewById(R.id.iv_12);
        imageView13 = findViewById(R.id.iv_13);
        imageView14 = findViewById(R.id.iv_14);
        imageView15 = findViewById(R.id.iv_15);
        imageView16 = findViewById(R.id.iv_16);
        imageView17 = findViewById(R.id.iv_17);
        imageView18 = findViewById(R.id.iv_18);
        imageView19 = findViewById(R.id.iv_19);
        imageView20 = findViewById(R.id.iv_20);

        verifyStoragePermissions(this);

    }

    private void loadImage() {
        ImageLoader.with(this)
                .load("http://rmrbtest-image.peopleapp.com/upload/image/201706/rmrb_23791498015584.gif")
                .diskCacheStrategy(ImageMode.DiskCache.ALL)
                .skipMemoryCache(false)
                .scaleType(ImageMode.ScaleMode.CENTER_CROP)
                .progress(new OnProgressListener() {
                    @Override
                    public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone) {
                        Log.e("Bill", totalBytes + "/" + bytesRead);
                        progress.setText(totalBytes + "/" + bytesRead);
                    }
                })
                .listener(new LoaderListener() {
                    @Override
                    public void onSuccess() {
                        Log.e("Bill", "success");
                    }

                    @Override
                    public void onFail() {
                        Log.e("Bill", "fail");
                    }
                })
                .into(imageView0);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.RoundedCorners)
                .rectRoundRadius(5)
                .into(imageView1);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.CropCircle)
                .into(imageView2);

        ImageLoader.with(this)
                .load(R.mipmap.pic)
                .filterType(ImageMode.FilterType.CropSquare)
                .into(imageView3);

        ImageLoader.with(this)
                .load(R.mipmap.pic)
                .filterType(ImageMode.FilterType.Crop)
                .cropSize(300, 100)
                .cropMode(ImageMode.CropMode.TOP)
                .into(imageView4);

        ImageLoader.with(this)
                .load(R.mipmap.pic)
                .filterType(ImageMode.FilterType.Crop)
                .cropSize(300, 100)
                .into(imageView5);

        ImageLoader.with(this)
                .load(R.mipmap.pic)
                .filterType(ImageMode.FilterType.Crop)
                .cropSize(300, 100)
                .cropMode(ImageMode.CropMode.BOTTOM)
                .into(imageView6);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Blur)
                .blurRadius(30)
                .into(imageView7);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.ColorFilter)
                .colorFilter(R.color.colorPrimary)
                .into(imageView8);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Grayscale)
                .into(imageView9);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Sepia)
                .intensity(5f)
                .into(imageView10);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Toon)
                .toon(0.5f, 20.0f)
                .into(imageView11);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Contrast)
                .contrast(2.0f)
                .into(imageView12);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Brightness)
                .brightness(-0.5f)
                .into(imageView13);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Sketch)
                .into(imageView14);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Pixelation)
                .pixelation(10.0f)
                .into(imageView15);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Invert)
                .into(imageView16);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Swirl)
                .swirl(0.5f, 1.0f, 0.5f, 0.5f)
                .into(imageView17);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Vignette)
                .vignette(0.5f, 0.5f, 0f, 1f, 1f, 0.1f, 0.5f)
                .into(imageView18);

        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Mask)
                .maskId(R.mipmap.mask)
                .into(imageView19);

        // 组合效果
        ImageLoader.with(this)
                .load(R.mipmap.timg)
                .filterType(ImageMode.FilterType.Grayscale, ImageMode.FilterType.Pixelation)
                .into(imageView20);


    }

    private void addView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(500, 500);
        params.topMargin = 20;
        layout.addView(imageView, params);
    }

}