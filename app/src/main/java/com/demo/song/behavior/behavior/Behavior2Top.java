package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.song.utils.Utils;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/12.
 */
public class Behavior2Top extends CoordinatorLayout.Behavior<View> {

    private boolean upReach=false;
    private boolean downReach=false;
    private int lastPos;

    public Behavior2Top() {
    }

    public Behavior2Top(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        upReach=false;
        downReach=false;
        lastPos=-1;
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
        int translateY= (int) child.getTranslationY();
        int firstPos=((LinearLayoutManager)((RecyclerView) target).getLayoutManager())
                .findFirstCompletelyVisibleItemPosition();
        if(translateY==-child.getHeight()&&firstPos==0&&firstPos<lastPos)
            downReach=true;
        Utils.logPrint(this,"translateY"+translateY);
        Utils.logPrint(this,"-child.getHeight()"+-child.getHeight());
        if(translateY==-child.getHeight()&&firstPos==0&&firstPos==lastPos)
            upReach=true;
        lastPos=firstPos;
        if(upReach||downReach){
            consumed[1]=dy;
            return;
        }
        if(firstPos>0)
            return;
        translateY-=dy;
        consumed[1]=dy;
        if(translateY>0){
            translateY=0;
            consumed[1]=0;
        }else if(translateY<-child.getHeight()){
            translateY=-child.getHeight();
            consumed[1]=0;
        }
        child.setTranslationY(translateY);

    }
}
