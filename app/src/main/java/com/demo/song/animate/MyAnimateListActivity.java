package com.demo.song.animate;

import com.demo.song.demoset.list.BaseListActivity;

public class MyAnimateListActivity extends BaseListActivity {


    @Override
    public String[] getData() {
        return new String[]{"AnimateActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{AnimateActivity.class};
    }
}
