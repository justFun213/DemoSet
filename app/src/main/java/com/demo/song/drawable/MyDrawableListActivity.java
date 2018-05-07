package com.demo.song.drawable;

import android.graphics.BitmapShader;

import com.demo.song.demoset.list.BaseListActivity;
import com.demo.song.drawable.shape.LayerListActivity;
import com.demo.song.drawable.shape.ShapeActivity;

public class MyDrawableListActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"ShapeActivity","LayerListActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{ShapeActivity.class,LayerListActivity.class};
    }
}
