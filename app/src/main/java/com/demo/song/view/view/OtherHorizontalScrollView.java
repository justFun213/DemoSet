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
 * Created by song on 2017/5/28.
 */
public class OtherHorizontalScrollView extends ViewGroup {

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int lastX;
    private int lastY;
    private int childCount;
    private int currentIndex;

    public OtherHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public OtherHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OtherHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        if(mScroller==null){
            mScroller=new Scroller(context);
        }
        mVelocityTracker=VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean isIntercept=false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished())
                {
                    mScroller.abortAnimation();
                    isIntercept=true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX= (int) ev.getRawX();
                int currentY= (int) ev.getRawY();
                if(Math.abs(currentX-lastX)>Math.abs(currentY-lastY))
                {
                    isIntercept=true;
                }else{
                    isIntercept=false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercept=false;
                break;
        }
        lastX= (int) ev.getRawX();
        lastY= (int) ev.getRawY();
        return isIntercept;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childCount=getChildCount();
        int left=0;
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            left=i*child.getMeasuredWidth();
            child.layout(left,0,left+child.getMeasuredWidth(),child.getMeasuredHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mVelocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished())
                {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dx= (int) (event.getRawX()-lastX);
                scrollBy(-dx,0);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int speedX= (int) mVelocityTracker.getXVelocity();
                if(Math.abs(speedX)>50){
                    currentIndex=speedX>0?currentIndex-1:currentIndex+1;
                }else{
                    currentIndex=(getScrollX()+getWidth()/2)/getWidth();
                }
                currentIndex=Math.max(0,Math.min(childCount-1,currentIndex));
                mScroller.startScroll(getScrollX(),0,currentIndex*getWidth()-getScrollX(),0);
                invalidate();
                break;
        }
        lastX= (int) event.getRawX();
        lastY= (int) event.getRawY();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    public static class LayoutParams extends MarginLayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }
    }
}
