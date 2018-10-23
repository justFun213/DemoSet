package com.demo.song.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by song on 2017/7/3.
 */
public class Utils {

    public static float dip2px(Context context,float dp){
        float scale=context.getResources().getDisplayMetrics().density;
        float px=scale*dp+0.5f;
        return px;
    }

    /**
     * 得到一个字符串的宽度
     * @param str
     * @param paint
     * @return
     */
    public static float getTextWidth(String str,Paint paint){
        return paint.measureText(str);
    }


    /**
     * 得到一个字符串的高度
     * @param paint
     * @return
     */
    public static float getTextHeight(Paint paint){
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        return fontMetrics.bottom-fontMetrics.top;
    }

    /**
     * 这个值是负数    把baseline搞懂再用
     * @return
     */
    public static float getTextTop(Paint paint){
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        return fontMetrics.top;
    }

    public static void logPrint(Object object,String str){
        Log.e(object.getClass().getName(),str);
    }

}
