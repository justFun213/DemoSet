package com.demo.song.activitydemo;

import com.demo.song.activitydemo.activitys.BlankActivity;
import com.demo.song.activitydemo.activitys.EmptyActivity;
import com.demo.song.activitydemo.activitys.FullScreenActivity;
import com.demo.song.activitydemo.activitys.LoginActivity;
import com.demo.song.activitydemo.activitys.NavigationDrawerActivity;
import com.demo.song.activitydemo.activitys.ScrollingActivity;
import com.demo.song.activitydemo.activitys.SettingsActivity;
import com.demo.song.activitydemo.activitys.TabberActivity;
import com.demo.song.demoset.list.BaseListActivity;

public class AllActivityListActivity extends BaseListActivity {


    @Override
    public String[] getData() {
        return new String[]{"BlankActivity","EmptyActivity","FullScreenActivity",
                "LoginActivity","NavigationDrawerActivity","ScrollingActivity",
                "SettingsActivity","TabberActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{BlankActivity.class,EmptyActivity.class,FullScreenActivity.class,
                LoginActivity.class,NavigationDrawerActivity.class,ScrollingActivity.class,
                SettingsActivity.class,TabberActivity.class};
    }

}
