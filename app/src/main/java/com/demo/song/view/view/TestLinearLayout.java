package com.demo.song.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by song on 2017/5/23.
 */
public class TestLinearLayout extends LinearLayout {
    public TestLinearLayout(Context context) {
        super(context);
    }

    public TestLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        Log.d("TestLinearLayout onMeasure widthMode:",getMeasureSpecString(widthMode)+"");
        Log.d("TestLinearLayout onMeasure widthSize:",widthSize+"");
        Log.d("TestLinearLayout onMeasure heightMode:",getMeasureSpecString(heightMode)+"");
        Log.d("TestLinearLayout onMeasure heightSize:",heightSize+"");
        Log.d("/******************************************************************/", "");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {

        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        int parentHeightMode=MeasureSpec.getMode(parentHeightMeasureSpec);
        int parentHeightSize=MeasureSpec.getSize(parentHeightMeasureSpec);


        Log.d("TestLinearLayout parentHeightMode:",getMeasureSpecString(parentHeightMode)+"");
        Log.d("TestLinearLayout parentHeightSize:",parentHeightSize+"");

        final int targetSdkVersion = getContext().getApplicationInfo().targetSdkVersion;

        Log.d("TestLinearLayout targetSdkVersion:",targetSdkVersion+"");

        Log.d("TestLinearLayout childHeight:",lp.height + "");

        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                lp.topMargin + lp.bottomMargin
                        + heightUsed, lp.height);

        int heightMode=MeasureSpec.getMode(childHeightMeasureSpec);
        int heightSize=MeasureSpec.getSize(childHeightMeasureSpec);

        Log.d("TestLinearLayout childHeightMode:",getMeasureSpecString(heightMode)+"");
        Log.d("TestLinearLayout childHeightSize:",heightSize+"");


        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    public  int getChildMeasureSpecTest(int spec, int padding, int childDimension) {
        int specMode = MeasureSpec.getMode(spec);
        int specSize = MeasureSpec.getSize(spec);

        int size = Math.max(0, specSize - padding);

        int resultSize = 0;
        int resultMode = 0;

        switch (specMode) {
            // Parent has imposed an exact size on us
            case MeasureSpec.EXACTLY:
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    // Child wants to be our size. So be it.
                    resultSize = size;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    // Child wants to determine its own size. It can't be
                    // bigger than us.
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;

            // Parent has imposed a maximum size on us
            case MeasureSpec.AT_MOST:
                if (childDimension >= 0) {
                    // Child wants a specific size... so be it
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    // Child wants to be our size, but our size is not fixed.
                    // Constrain child to not be bigger than us.
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    // Child wants to determine its own size. It can't be
                    // bigger than us.
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;

            // Parent asked to see how big we want to be
            case MeasureSpec.UNSPECIFIED:
                if (childDimension >= 0) {
                    // Child wants a specific size... let him have it
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    // Child wants to be our size... find out how big it should
                    // be
                    resultSize =0;
                    resultMode = MeasureSpec.UNSPECIFIED;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    // Child wants to determine its own size.... find out how
                    // big it should be
                    resultSize =0;
                    resultMode = MeasureSpec.UNSPECIFIED;
                }
                break;
        }
        //noinspection ResourceType
        return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
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
