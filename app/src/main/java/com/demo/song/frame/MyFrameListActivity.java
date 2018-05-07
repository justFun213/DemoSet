package com.demo.song.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.song.demoset.R;
import com.demo.song.demoset.list.BaseListActivity;

public class MyFrameListActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"RetrofitActivity","ButterKnifeActivity",
        "OkHttpActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{RetrofitActivity.class,ButterKnifeActivity.class,OkHttpActivity.class};
    }

}
