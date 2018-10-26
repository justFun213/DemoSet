package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/11.
 */
public class Behavior5_2 extends CoordinatorLayout.Behavior<View> {

    private float maxRvDistance;

    public Behavior5_2() {}

    public Behavior5_2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(dependency.getBottom());
        return true;
    }
}
