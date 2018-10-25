package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demo.song.utils.Utils;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/12.
 */
public class Behavior2Top extends CoordinatorLayout.Behavior<View> {

    private boolean upReach=false;
    private boolean downReach=false;
    private volatile int lastPos;

    public Behavior2Top() {
    }

    public Behavior2Top(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            upReach=false;
            downReach=false;
            lastPos=-1;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes&ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
    /*    if(target instanceof RecyclerView){
            RecyclerView recyclerView= (RecyclerView) target;
            int firstPos=((LinearLayoutManager)recyclerView.getLayoutManager())
                                                         .findFirstCompletelyVisibleItemPosition();
            if(firstPos>0)
                return;
        }
        int translateY= (int) child.getTranslationY();
        translateY-=dy;
        consumed[1]=dy;
        if(translateY>0){
            translateY=0;
            consumed[1]=0;
        }else if(translateY<-child.getHeight()){
            translateY=-child.getHeight();
            consumed[1]=0;
        }
        child.setTranslationY(translateY);*/
        if(!(target instanceof RecyclerView))
            return;
        int translateY= (int) child.getTranslationY();
        int firstPos=((LinearLayoutManager)((RecyclerView) target).getLayoutManager())
                .findFirstCompletelyVisibleItemPosition();
        //判断recyclerView本身可以滑动,则不消耗直接给recyclerview
        if(firstPos>0){
            lastPos=firstPos;
            return;
        }
        translateY-=dy;
        //向下拉到达TextView临界点（全部隐藏）
        if(lastPos!=-1&&translateY>=-child.getHeight()&&firstPos==0&&firstPos<lastPos&&dy<0){
            downReach=true;
        }
        //向上滑动到达TextView临界点（全部隐藏）
        if(lastPos!=-1&&translateY<=-child.getHeight()&&firstPos==0&&firstPos==lastPos&&dy>0){
            upReach=true;
        }
        lastPos=firstPos;
        consumed[1]=dy; //默认消耗事件,即textview可以滑动
        if(upReach||downReach){ //到达临界点，消耗事件却不做任何事
            translateY=-child.getHeight();
            consumed[1]=dy;
        }else if(translateY>0){//已经到达临界点后再次点击向下滑动，RecyclerView可以向下滑动（然后这种情况recyclerView也到达顶部了，即实际效果是停止不动）
            translateY=0;
            consumed[1]=0;
        }else if(translateY<-child.getHeight()){//已经到达临界点后再次点击向上滑动，RecyclerView可以向上滑动
            translateY=-child.getHeight();
            consumed[1]=0;
        }
        child.setTranslationY(translateY);
    }
}
