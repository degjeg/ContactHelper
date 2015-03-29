package com.witness.utils.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.witness.utils.LogUtil;


public class BitmapUtils {


    private static final String TAG = "BitmapUtils";

    private static final boolean logOn = false;

    /*public static Bitmap getBitmap(Context context, Bitmap srcBitmap, float min, float max) {
        float newWidth = 0;
        float newHeight = 0;
        float bigger = 0;
        float smaller = 0;
        float deMax = 0;
        float deMin = 0;
        int stateCode = 0;
        float dem = 0;

        float imageWidth = srcBitmap.getWidth();
        float imageHeight = srcBitmap.getHeight();

        LogUtil.d(TAG, "最大为:" + max + "最小为" + min + "边长为:");
        bigger = imageWidth >= imageHeight ? imageWidth : imageHeight;
        smaller = imageWidth <= imageHeight ? imageWidth : imageHeight;
        deMax = DensityUtil.dip2px(context, max) / bigger;
        deMin = DensityUtil.dip2px(context, min) / smaller;
        System.err.println("最小比:" + deMin + "最大比" + deMax);
        newWidth = imageWidth * deMax;
        newHeight = imageHeight * deMax;
        LogUtil.d(TAG, "宽度:" + imageWidth + "高度" + imageHeight);
        LogUtil.d(TAG, "新的宽度:" + newWidth + "新的高度" + newHeight);
        if (newHeight < DensityUtil.dip2px(context, min)
                || newWidth < DensityUtil.dip2px(context, min)) {
            newHeight = imageHeight * deMin;
            newWidth = imageWidth * deMin;
            stateCode = 1;
        }
        LogUtil.d(TAG, "处理过一次后的新的宽度:" + newWidth + "新的高度" + newHeight);

        Matrix matrix = new Matrix();
        dem = stateCode == 1 ? deMin : deMax;
        System.err.println("压缩比是" + dem);
        matrix.postScale(dem, dem);
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, (int) imageWidth, (int) imageHeight, matrix, true);
        LogUtil.d(TAG, "处理后第二次的图片宽为:" + bitmap.getWidth() + "高为:" + bitmap.getHeight());

        if (stateCode == 1) {
            LogUtil.d(TAG, "特殊处理");
            int cuth = (int) (newHeight > DensityUtil.dip2px(context, max) ? (newHeight - DensityUtil.dip2px(context, max)) / 2 : 0);
            int cutw = (int) (newWidth > DensityUtil.dip2px(context, max) ? (newWidth - DensityUtil.dip2px(context, max)) / 2 : 0);
            newHeight = newHeight < DensityUtil.dip2px(context, max) ? newHeight : DensityUtil.dip2px(context, max);
            newWidth = newWidth < DensityUtil.dip2px(context, max) ? newWidth : DensityUtil.dip2px(context, max);
            LogUtil.d(TAG, "处理后宽为" + newWidth + "高为:" + newHeight);

            bitmap = Bitmap.createBitmap(bitmap, cutw, cuth, (int) newWidth, (int) newHeight);
        }
        LogUtil.d(TAG, "最终处理后的图片宽为:" + bitmap.getWidth() + "高为:" + bitmap.getHeight());

        return bitmap;
    }*/

    /**
     * float reft:新图片的边长
     */
    public static Bitmap getBitmap2(Context context, Bitmap srcBitmap, float reft) {
        float smaller = 0;
        float deMin = 0;
        float newWidth = 0;
        float newHeight = 0;

        float imageWidth = srcBitmap.getWidth();
        float imageHeight = srcBitmap.getHeight();

        smaller = imageWidth <= imageHeight ? imageWidth : imageHeight;

        deMin = reft / smaller;
        if (logOn) {
            LogUtil.d(TAG, "压缩比例:" + reft + "/" + smaller + "=" + deMin);
        }

        if (smaller == 0 || reft == 0) {
            if (logOn) {
                LogUtil.e(TAG, "Invalid parameter");
            }
            return null;
        }
        newWidth = imageWidth * deMin;
        newHeight = imageHeight * deMin;
        if (logOn) {
            LogUtil.d(TAG, "新的宽度:" + newWidth + "新的高度" + newHeight);
        }
        Matrix matrix = new Matrix();
        matrix.postScale(deMin, deMin);

        int xOffset = 0;
        int yOffset = 0;
        if (imageWidth >= imageHeight) {
            xOffset = (int) ((imageWidth - smaller) / 2);
        } else {
            yOffset = (int) ((imageHeight - smaller) / 2);
        }

        Bitmap bitmap = Bitmap.createBitmap(srcBitmap, xOffset, yOffset, (int) smaller, (int) smaller, matrix, true);


//        LogUtil.d(TAG, "第一次处理后的图片宽为:" + bitmap.getWidth() + "高为:" + bitmap.getHeight());
//        int cuth = (int) (newHeight > DensityUtil.dip2px(context, reft) ? (newHeight - DensityUtil.dip2px(context, reft)) / 2 : 0);
//        int cutw = (int) (newWidth > DensityUtil.dip2px(context, reft) ? (newWidth - DensityUtil.dip2px(context, reft)) / 2 : 0);
//
//        newHeight = newHeight < DensityUtil.dip2px(context, reft) ? newHeight : DensityUtil.dip2px(context, reft);
//        newWidth = newWidth < DensityUtil.dip2px(context, reft) ? newWidth : DensityUtil.dip2px(context, reft);
//
//        bitmap = Bitmap.createBitmap(bitmap, cutw, cuth, (int) newWidth, (int) newHeight);
//        LogUtil.d(TAG, "最终处理后的图片宽为:" + bitmap.getWidth() + "高为:" + bitmap.getHeight());
        return bitmap;

    }

    /**
     * float reft:新图片的边长
     */
    public static Bitmap getBitmap(Bitmap srcBitmap, float width, float height) {
        float proOri = ((float) srcBitmap.getHeight()) / srcBitmap.getWidth();
        float proDst = ((float) height) / width;
        float dstW, dstH;
        float x = 0;
        float y = 0;

        if (logOn) {
            LogUtil.d(TAG, "src:" + srcBitmap.getWidth() + "," + srcBitmap.getHeight());
            LogUtil.d(TAG, "dst:" + width + "," + height);
        }

        if (proOri > proDst) {
            dstW = srcBitmap.getWidth();
            dstH = height / (width / srcBitmap.getWidth());
            y = (srcBitmap.getHeight() - dstH) / 2;
        } else {
            dstW = width / (height / srcBitmap.getHeight());
            dstH = srcBitmap.getHeight();
            x = (srcBitmap.getWidth() - dstW) / 2;
        }


        if (logOn) {
            LogUtil.d(TAG, "dst:(" + x + "," + y + ")(" + dstW + "," + dstH);
        }

        Matrix matrix = new Matrix();
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap, (int) x, (int) y, (int) dstW, (int) dstH);
        return bitmap;
    }

}
