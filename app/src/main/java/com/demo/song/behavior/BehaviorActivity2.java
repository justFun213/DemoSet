package com.demo.song.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.demo.song.adpater.DemoAdapter;
import com.demo.song.demoset.R;

public class BehaviorActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior2);
        RecyclerView recyclerView=findViewById(R.id.my_list);
        DemoAdapter.mockData(recyclerView);
    }
}
