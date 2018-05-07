package com.demo.song.view.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by song on 2017/7/8.
 * 美团刷新列表view
 */
public class MeiTuanRefreshView extends RecyclerView {


    public MeiTuanRefreshView(Context context) {
        super(context);
    }

    public MeiTuanRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeiTuanRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }

    public static abstract class MeiTuanRefreshAdpater extends RecyclerView.Adapter{
        public abstract ViewHolder onCreateMeiTuanViewHolder(ViewGroup parent, int viewType);
        public abstract void onBindMeiTuanViewHolder(ViewHolder holder, int position);
        public abstract int getMeiTuanItemCount();

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return onCreateMeiTuanViewHolder(parent,viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            onBindMeiTuanViewHolder(holder,position);
        }

        @Override
        public int getItemCount() {
            return getMeiTuanItemCount()>0? 2+getMeiTuanItemCount():0;
        }
    }
}
