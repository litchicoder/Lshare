package com.example.lsharelib.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib
 * Date:   2017/11/8
 * Desc: com.example.lsharelib
 */
public class Util {

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo =
                        packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 获取网络address地址对应的图片
     *
     * @param address®
     * @return bitmap的类型
     */
    public static void getImage(String address, ImageLoadCallBack mImageLoadCallBack) throws Exception {
        SaveImageTask task = new SaveImageTask(URLEncoder.encode(address, "UTF-8"), mImageLoadCallBack);
        task.execute(address);
    }

    /**
     * 获取环球捕手APP中图片缓存的文件夹
     *
     * @return
     */
    public static String getShareFile() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lshare/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }

        return path;
    }

    /***
     * 功能：用线程保存图片
     */
    private static class SaveImageTask extends AsyncTask<String, Void, String> {
        private String imageName;
        private ImageLoadCallBack mImageLoadCallBack;

        public SaveImageTask(String imageName, ImageLoadCallBack mImageLoadCallBack) {
            this.imageName = imageName;
            this.mImageLoadCallBack = mImageLoadCallBack;
        }

        /**
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConn = null;
            Bitmap bm = null;
            String path = getShareFile() + imageName;
            try {
                String urlStr = params[0];
                URL url = new URL(urlStr);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setConnectTimeout(10 * 1000);
                urlConn.setRequestMethod("GET");
                InputStream is = null;
                if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    is = urlConn.getInputStream();
                }
                if (is != null) {
                    bm = BitmapFactory.decodeStream(is);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
                    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                return null;
            } finally {
                if (urlConn != null) {
                    urlConn.disconnect();
                }
            }

            return path;
        }

        @Override
        protected void onPostExecute(String path) {
            if (null != path) {
                mImageLoadCallBack.success(path);
            } else {
                mImageLoadCallBack.fail();
            }
        }
    }

    public interface ImageLoadCallBack {
        void success(String path);

        void fail();
    }

}
