package com.demo.song.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.song.behavior.BehaviorActivity;
import com.demo.song.demoset.list.BaseListActivity;

public class MyBehaviorListActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"BehaviorActivity","coordinatorlayoutDemo","BehaviorActivity1",
                "BehaviorActivity2","BehaviorActivity3","BehaviorActivity5"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{BehaviorActivity.class,coordinatorlayoutDemo.class,
                BehaviorActivity1.class,BehaviorActivity2.class,BehaviorActivity3.class,
                BehaviorActivity5.class};
    }
}
