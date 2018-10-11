package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
/**
 * 功能描述：
 * Created by Administrator on 2018/10/11.
 */
public class Behavior1 extends CoordinatorLayout.Behavior<View> {

    private float trDistance;

    public Behavior1() {}

    public Behavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if(trDistance-0.0001<0){
            trDistance=dependency.getY()-child.getHeight();
        }
        float scrollY=dependency.getY()-child.getHeight();
        float translateY=-scrollY/trDistance*child.getHeight();
        translateY=Math.min(0,Math.max(-child.getHeight(),translateY));
        child.setTranslationY(translateY);
        return true;
    }
}
