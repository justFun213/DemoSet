package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/12.
 */
public class Behavior2Bottom extends CoordinatorLayout.Behavior {

    public Behavior2Bottom() {
    }

    public Behavior2Bottom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float dy=dependency.getHeight()+dependency.getTranslationY();
        dy=dy<0? 0:dy;
        child.setY(dy);
        return true;
    }
}
