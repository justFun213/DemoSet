package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/12.
 */
public class Behavior2Top extends CoordinatorLayout.Behavior<View> {

    public Behavior2Top() {
    }

    public Behavior2Top(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setBottom((int) dependency.getY());
        return true;
    }
}
