package com.demo.song.adpater;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 功能描述：
 * Created by Administrator on 2018/10/11.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private String[] data;

    DemoAdapter(String[] datas){
        data=datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView=new TextView(parent.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20f);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.length;
    }

     static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

         ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView;
        }
    }

    public static void mockData(RecyclerView recyclerView){
        LinearLayoutManager manager=new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new DemoAdapter(new String[]{"A","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"
                ,"B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E","B","C","D","E"}));
    }
}
