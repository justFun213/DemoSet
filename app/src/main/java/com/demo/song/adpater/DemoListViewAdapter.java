package com.demo.song.adpater;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/23.
 */
public class DemoListViewAdapter extends BaseAdapter {

    private String[] data;

    private DemoListViewAdapter(String[] data){
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if(view==null){
            textView=new TextView(viewGroup.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20f);
        }else{
            textView= (TextView) view;
        }
        return textView;
    }

    public static void mockData(ListView listView){
        DemoListViewAdapter adapter=new DemoListViewAdapter(new String[]{"A","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"});
        listView.setAdapter(adapter);
    }
}
