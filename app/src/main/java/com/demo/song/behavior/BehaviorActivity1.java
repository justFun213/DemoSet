package com.demo.song.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.demo.song.adpater.DemoRecylerViewAdapter;
import com.demo.song.demoset.R;

public class BehaviorActivity1 extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior1);
        recyclerView= findViewById(R.id.my_list);
        DemoRecylerViewAdapter.mockData(recyclerView);
    }
}
