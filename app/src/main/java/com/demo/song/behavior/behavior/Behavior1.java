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

    private float maxRvDistance;

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
        float translationY=dependency.getY()-child.getHeight();
        if(maxRvDistance==0)
            maxRvDistance=translationY;
        translationY=translationY<0 ? 0:translationY;

//        translationY=-child.getHeight()*translationY/maxRvDistance;
//        child.setTranslationY(translationY);

        translationY=1-translationY/maxRvDistance;
        child.setAlpha(translationY);
        return true;
    }
}
