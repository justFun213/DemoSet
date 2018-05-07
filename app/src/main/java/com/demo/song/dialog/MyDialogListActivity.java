package com.demo.song.dialog;

import com.demo.song.demoset.list.BaseListActivity;

/**
 * Created by song on 2017/6/19.
 */
public class MyDialogListActivity extends BaseListActivity {
    @Override
    public String[] getData() {
        return new String[]{"AlertDialogActivity","DialogFragmentActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{AlertDialogActivity.class,DialogFragmentActivity.class};
    }
}
