package com.demo.song.view;

import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.demo.song.demoset.list.BaseListActivity;
import com.demo.song.view.HorizontalScrollActivity;
import com.demo.song.view.ListViewTestAcctivity;

public class MyViewListActivity extends BaseListActivity {

    @Override
    public String[] getData() {
        return new String[]{"HorizontalScrollView","ListViewTestAcctivity",
                "BannerViewActivity","PercentCircleViewActivity",
                "BarChartActivity","TimeClockActivity"};
    }

    @Override
    public Class[] getClasses() {
        new TextView(this).setText("");
        return new Class[]{HorizontalScrollActivity.class,ListViewTestAcctivity.class,
                BannerViewActivity.class,PercentCircleViewActivity.class,
                BarChartActivity.class,TimeClockActivity.class};
    }
}
