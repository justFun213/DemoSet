package com.demo.song.behavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.song.adpater.DemoAdapter;
import com.demo.song.demoset.R;
import com.demo.song.demoset.list.BaseListActivity;

public class BehaviorActivity1 extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior1);
        recyclerView= findViewById(R.id.my_list);
        DemoAdapter.mockData(recyclerView);
    }
}
