package com.demo.song.behavior.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/11.
 */
public class Behavior5 extends CoordinatorLayout.Behavior<View> {

    private float maxRvDistance;

    public Behavior5() {}

    public Behavior5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes&ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if(!(target instanceof RecyclerView)&& !(child instanceof TextView))
            return;
        TextView textView= (TextView) child;
        if(target.canScrollVertically(-1))
            textView.setText("滑到内容了~");
        else
            textView.setText("我是标题！");
    }
}
