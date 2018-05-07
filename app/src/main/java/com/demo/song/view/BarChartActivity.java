package com.demo.song.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.song.demoset.R;
import com.demo.song.view.view.BarChartView;
import com.demo.song.view.view.BarChartView.BarChartElement;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    private BarChartView barChartView;
    private String[] columnString={"10.0","20.0","30.0","40.0"};
    private ArrayList<BarChartElement> mData=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        barChartView= (BarChartView) findViewById(R.id.bar_char_view);
        initData();
    }

    private void initData() {
        mData.add(new BarChartElement("滴滴",34f));
        mData.add(new BarChartElement("携程",13f));
        mData.add(new BarChartElement("小米",26f));
        mData.add(new BarChartElement("美团",31f));
        mData.add(new BarChartElement("京东",9f));
        mData.add(new BarChartElement("爱奇艺", 13.3f));
        mData.add(new BarChartElement("酷派",17f));
        mData.add(new BarChartElement("乐视",3f));
        mData.add(new BarChartElement("今日头条",10f));

        barChartView.setColumnSize(columnString);
        barChartView.setUnit("亿元");
        barChartView.setMaxValue(40);
        barChartView.setmData(mData);
        barChartView.build();
    }


    public void onChange(View view){
        mData.clear();
        mData.add(new BarChartElement("小李",15f));
        mData.add(new BarChartElement("小陈",13f));
        mData.add(new BarChartElement("张三",39f));
        mData.add(new BarChartElement("王武", 56f));
        mData.add(new BarChartElement("小亮",72f));

        barChartView.setColumnSize(new String[]{"20","40","60","80"});
        barChartView.setUnit("次");
        barChartView.setMaxValue(80);
        barChartView.setmData(mData);
        barChartView.build();
    }

}
