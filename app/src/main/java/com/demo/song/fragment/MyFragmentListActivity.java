package com.demo.song.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.song.demoset.R;
import com.demo.song.demoset.list.BaseListActivity;

public class MyFragmentListActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"FragmentDemoActivity","FragmentChangeActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{FragmentDemoActivity.class,FragmentChangeActivity.class};

    }
}
