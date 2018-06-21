package com.bill.imageloader.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.bill.imageloader.config.GlobalConfig;

/**
 * Created by Bill on 2017/11/22.
 */

public class ImageDbManager {

    private static ImageDbManager imageDbManager;
    private ImageDbHelper imageDbHelper;

    private ImageDbManager(Context context) {
        if (imageDbHelper == null)
            imageDbHelper = ImageDbHelper.getInstance(context);
    }

    private SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase database = imageDbHelper.getReadableDatabase();
        return database;
    }

    private SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase database = imageDbHelper.getWritableDatabase();
        return database;
    }

    public static ImageDbManager getInstance() {
        if (imageDbManager == null) {
            synchronized (ImageDbManager.class) {
                if (imageDbManager == null)
                    imageDbManager = new ImageDbManager(GlobalConfig.getContext());
            }
        }
        return imageDbManager;
    }

    public boolean checkImageExist(String url) {
        SQLiteDatabase db = imageDbManager.getReadableDatabase();
        String sql = "SELECT * FROM " + ImageDbHelper.TABLE_NAME_URL
                + " WHERE " + ImageDbHelper.URL + " =?";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, new String[]{url});
            String selectUrl = "";
            if (cursor.moveToFirst()) {
                selectUrl = cursor.getString(cursor.getColumnIndex(ImageDbHelper.URL));
            }
            if (!TextUtils.isEmpty(selectUrl)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }

    synchronized public boolean insertImage(String url) {
        SQLiteDatabase db = imageDbManager.getWritableDatabase();
        String sql = "replace into " + ImageDbHelper.TABLE_NAME_URL +
                "(" + ImageDbHelper.URL + ") VALUES(?)";
        try {
            db.execSQL(sql, new Object[]{url});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    synchronized public boolean deleteImageByUrl(String url) {
        SQLiteDatabase db = imageDbManager.getWritableDatabase();
        try {
            String whereClause = ImageDbHelper.URL + "=?";//条件
            String[] whereArgs = {url};//条件的参数
            int result = db.delete(ImageDbHelper.TABLE_NAME_URL, whereClause, whereArgs);
            if (result != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized public boolean deleteImage() {
        SQLiteDatabase db = imageDbManager.getWritableDatabase();
        try {
            int result = db.delete(ImageDbHelper.TABLE_NAME_URL, null, null);
            if (result != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized public boolean removeImageTable() {
        SQLiteDatabase db = imageDbManager.getWritableDatabase();
        try {
            String sql = "DROP TABLE IF EXISTS " + ImageDbHelper.TABLE_NAME_URL;
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

}