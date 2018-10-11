package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

import com.demo.song.demoset.R;

/**
 * Created by qibin on 2015/12/13.
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if(child.getId()== R.id.ignore_view){
            if(target.getId()!= R.id.target){
                return;
            }
        }
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        if(child.getId()== R.id.ignore_view){
            if(target.getId()!= R.id.target){
                return false;
            }
        }
        ((NestedScrollView) child).fling((int)velocityY);
        return true;
    }

}