package com.demo.song.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.song.demoset.R;
import com.demo.song.view.view.PercentCircleView;

public class PercentCircleViewActivity extends AppCompatActivity {

    private PercentCircleView mPercentCircleView1;
    private PercentCircleView mPercentCircleView2;
    private PercentCircleView mPercentCircleView3;
    private PercentCircleView mPercentCircleView4;
    private float count=0.3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_circle_view);
        mPercentCircleView1= (PercentCircleView) findViewById(R.id.percent_circle_view1);
        mPercentCircleView2= (PercentCircleView) findViewById(R.id.percent_circle_view2);
        mPercentCircleView3= (PercentCircleView) findViewById(R.id.percent_circle_view3);
        mPercentCircleView4= (PercentCircleView) findViewById(R.id.percent_circle_view4);
        mPercentCircleView1.setPercent(0.458f);
        mPercentCircleView2.setPercent(0.5864f);
        mPercentCircleView3.setPercent(0.68324f);
        mPercentCircleView4.setPercent(0.758345f);
    }

    public void change(View view){
        mPercentCircleView4.setPercent(count);
        count+=0.1f;
    }
}
