package com.demo.song.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.demo.song.activitydemo.AllActivityListActivity;
import com.demo.song.adpater.MainMenuAdpater.Menu;
import com.demo.song.animate.MyAnimateListActivity;
import com.demo.song.demoset.R;
import com.demo.song.behavior.MyBehaviorListActivity;
import com.demo.song.dialog.MyDialogListActivity;
import com.demo.song.drawable.MyDrawableListActivity;
import com.demo.song.fragment.MyFragmentListActivity;
import com.demo.song.frame.MyFrameListActivity;
import com.demo.song.service.MyServiceListActivity;
import com.demo.song.theme.ThemeDemoActivity;
import com.demo.song.view.MyViewListActivity;
import com.demo.song.webview.MyWebViewListActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2017/5/17.
 */
public class MainMenuAdpater extends RecyclerView.Adapter<Menu> {

    private static final String TAG = "com.demo.song.adpater.MainMenuAdpater";
    private List<String> mList=new ArrayList<String>();

    public MainMenuAdpater(){
        mList.add("自定义View");
        mList.add("自定义behavior");
        mList.add("AllActivityListActivity");
        mList.add("ThemeDemoActivity");
        mList.add("MyDialogListActivity");
        mList.add("MyFragmentListActivity");
        mList.add("MyDrawableListActivity");
        mList.add("MyServiceListActivity");
        mList.add("MyAnimateListActivity");
        //无聊测试一下 哈哈  你是不是傻
        mList.add("MyFrameListActivity");
        mList.add("MyWebViewListActivity");
    }

    @Override
    public Menu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
        return new Menu(view);
    }

    @Override
    public void onBindViewHolder(Menu holder, final int position) {
        final TextView temp=holder.titleView;
        holder.titleView.setText(mList.get(position));
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, temp == v ? "true" : "false");
                startActivity(position, v.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class Menu extends RecyclerView.ViewHolder{

        TextView titleView;

        public Menu(View itemView) {
            super(itemView);
            titleView= (TextView) itemView.findViewById(R.id.title);
        }
    }

    public static void startActivity(int position,Context context){
        Intent intent=null;
        switch(position){
            case 0:
                intent=new Intent(context, MyViewListActivity.class);
                break;
            case 1:
                intent=new Intent(context, MyBehaviorListActivity.class);
                break;
            case 2:
                intent=new Intent(context, AllActivityListActivity.class);
                break;
            case 3:
                intent=new Intent(context, ThemeDemoActivity.class);
                break;
            case 4:
                intent=new Intent(context, MyDialogListActivity.class);
                break;
            case 5:
                intent=new Intent(context, MyFragmentListActivity.class);
                break;
            case 6:
                intent=new Intent(context, MyDrawableListActivity.class);
                break;
            case 7:
                intent=new Intent(context, MyServiceListActivity.class);
                break;
            case 8:
                intent=new Intent(context, MyAnimateListActivity.class);
                break;
            case 9:
                intent=new Intent(context, MyFrameListActivity.class);
                break;
            case 10:
                intent=new Intent(context, MyWebViewListActivity.class);
                break;
            default:
        }
        context.startActivity(intent);
    }

}
