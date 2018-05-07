package com.demo.song.view.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by song on 2017/5/23.
 */
public class TestTextView extends TextView {
    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        Log.d("TestTextView widthMode:",getMeasureSpecString(widthMode)+"");
        Log.d("TestTextView widthSize:",widthSize+"");
        Log.d("TestTextView heightMode:",getMeasureSpecString(heightMode)+"");
        Log.d("TestTextView heightSize:",heightSize+"");
        Log.d("/******************************************************************/", "");
        int newHeight=MeasureSpec.makeMeasureSpec(0,heightMode);
        super.onMeasure(widthMeasureSpec, 0);
    }

    private String getMeasureSpecString(int specMode){
        String specModeString=null;
        switch(specMode){
            case MeasureSpec.EXACTLY:
                specModeString="MeasureSpec.EXACTLY";
                break;
            case MeasureSpec.AT_MOST:
                specModeString="MeasureSpec.AT_MOST";
                break;
            case MeasureSpec.UNSPECIFIED:
                specModeString="MeasureSpec.UNSPECIFIED";
                break;
            default:
                specModeString="不识别";
                break;

        }
        return specModeString;
    }

}
