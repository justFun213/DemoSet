package com.demo.song.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.song.demoset.R;
import com.demo.song.demoset.list.BaseListActivity;

public class MyServiceListActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"ServiceActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{ServiceActivity.class};
    }
}
