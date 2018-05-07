package com.demo.song.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by song on 2017/5/19.
 */
public class MyHorizontalScrollView extends ViewGroup {

    private Scroller scroller;
    private VelocityTracker velocityTracker;
    private float mLastX;
    private float mLastY;


    public MyHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        scroller=new Scroller(context);
        velocityTracker=VelocityTracker.obtain();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
           View child=getChildAt(i);
         //   if(child.getLayoutParams() instanceof MarginLayoutParams){
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
       //     }else{
       //         measureChild(child, widthMeasureSpec,heightMeasureSpec);
       //     }
        }
        //measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("父布局l", l + "");
        Log.e("父布局t",t+"");
        Log.e("父布局r",r+"");
        Log.e("父布局b", b + "");
        int childCount=getChildCount();
        Log.e("childCount", childCount + "");
        int left=0;
        for(int i=0;i<childCount;i++){
            left=i*getMeasuredWidth();
            View child=getChildAt(i);
            int width=child.getMeasuredWidth();
            int height=child.getMeasuredHeight();
            Log.d("childWidth",width+"");
            Log.d("childHeight",height+"");
            setChildFrame(child, left, 0, width, height);
        }
    }

    public void setChildFrame(View child,int left,int top,int width,int height){
        child.layout(left, top, left + width, top + height);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean isIntercep=false;
        Log.d("onInterceptTouchEvent", "onInterceptTouchEvent");
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                    isIntercep=true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(ev.getRawX()-mLastX)>Math.abs(ev.getRawY()-mLastY))
                {
                    isIntercep=true;
                }else{
                    isIntercep=false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercep=false;
                break;
            default:
                break;
        }
        mLastX=ev.getRawX();
        mLastY=ev.getRawY();
        return isIntercep;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("onTouchEvent", "onTouchEvent");
        velocityTracker.addMovement(event);
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(scroller.isFinished()){
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int delaX= (int) (event.getRawX()-mLastX);
                int delaYY= (int) (event.getRawY()-mLastY);
                Log.d("delaX",delaX+"");
                scrollBy(-delaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(1000);
                int speedX= (int) velocityTracker.getXVelocity();
                int tempIndex=0;
                if(Math.abs(speedX)>50){   //达到滑页速度
                    tempIndex=speedX<0? getScrollX()/getMeasuredWidth()+1:getScrollX()/getMeasuredWidth()-1;
                }else{                    //根据滑动范围计算页标
                    int childCount=getChildCount();
                    tempIndex=(getScrollX()+getMeasuredWidth()/2)/getMeasuredWidth();
                }
                int index=Math.max(0,Math.min(tempIndex,getChildCount()-1));
                int scrollX=index*getMeasuredWidth()-getScrollX();
                smoothScrollTo(scrollX,0);
                velocityTracker.clear();
                break;
            default:
                break;
        }
        mLastX=event.getRawX();
        mLastY=event.getRawY();
        return true;
    }

    public void smoothScrollTo(int delaX,int delaY){
        scroller.startScroll(getScrollX(),getScrollY(),delaX,0,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        velocityTracker.recycle();
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static class LayoutParams extends MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

}
