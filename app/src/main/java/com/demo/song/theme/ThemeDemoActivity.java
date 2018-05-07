package com.demo.song.theme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.song.demoset.list.BaseListActivity;

public class ThemeDemoActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"StatusBarActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{StatusBarActivity.class};
    }
}
