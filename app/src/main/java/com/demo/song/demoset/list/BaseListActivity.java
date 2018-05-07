package com.demo.song.demoset.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.view.HorizontalScrollActivity;
import com.demo.song.view.ListViewTestAcctivity;

import java.util.List;

public abstract class BaseListActivity extends AppCompatActivity {


    private MyAdpater mAdpater;
    private ListView listView;

    public abstract String[] getData();
    public abstract Class[] getClasses();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater.from(this).inflate(R.layout.activity_base_list, (ViewGroup) findViewById(android.R.id.content), true);
        listView= (ListView) findViewById(R.id.view_list);
        initView(getData(),getClasses());
        Log.d("currentActivity:",getLocalClassName());
    }

    private void initView(String[] data,final Class[] classes) {
        mAdpater=new MyAdpater(data);
        listView.setAdapter(mAdpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), classes[position]);
                parent.getContext().startActivity(intent);
            }
        });
    }

    static class MyAdpater extends BaseAdapter{
        private  String[] mData;


        public MyAdpater(String[] data){
            mData=data;
        }

        @Override
        public int getCount() {
            return mData.length;
        }

        @Override
        public String getItem(int position) {
            return mData[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=null;
            if(convertView==null){
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                //   AbsListView.LayoutParams param = new AbsListView.LayoutParams(300,200);
                //   view.setLayoutParams(param);
                view.setTag(view.findViewById(R.id.item_title));
            }else{
                view=convertView;
            }
            ((TextView)view.getTag()).setText(getItem(position));
            return view;
        }
    };

}
