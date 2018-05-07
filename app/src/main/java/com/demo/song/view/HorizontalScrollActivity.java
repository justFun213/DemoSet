package com.demo.song.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.view.view.HorizontalScrollViewEx;
import com.demo.song.view.view.MyHorizontalScrollView;
import com.demo.song.view.view.OtherHorizontalScrollView;

public class HorizontalScrollActivity extends AppCompatActivity {

    private OtherHorizontalScrollView myHorizontalScrollView;

    private String[] mData={"A","B","C","D","E","F","G","H","I","A","B","C","D","E","F","G","H","I","A","B","C","D","E","F","G","H","I"
    ,"A","B","C","D","E","F","G","H","I","A","B","C","D","E","F","G","H","I","A","B","C","D","E","F","G","H","I"
    ,"A","B","C","D","E","F","G","H","I","A","B","C","D","E","F","G","H","I","A","B","C","D","E","F","G","H","I"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main2);
        LayoutInflater.from(this).inflate(R.layout.activity_main2, (ViewGroup) findViewById(android.R.id.content),true);
        myHorizontalScrollView= (OtherHorizontalScrollView) findViewById(R.id.my_horizontal_view);
      //  intitView();
        for(int i=0;i<6;i++){
           ListView listView=new ListView(this);
           LayoutParams layoutParams=new OtherHorizontalScrollView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            //ViewGroup.MarginLayoutParams layoutParams=new ViewGroup.MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            listView.setLayoutParams(layoutParams);
            listView.setAdapter(mAdpater);
            myHorizontalScrollView.addView(listView);

         /*   ListView listView= (ListView) LayoutInflater.from(this).inflate(R.layout.activity_my_view_list,myHorizontalScrollView, true).findViewById(R.id.view_list);
            listView.setAdapter(mAdpater);*/
        }
    }

    private void intitView() {
        for(int i=0;i<4;i++){
            LinearLayout parent=new LinearLayout(this);
            LayoutParams layoutParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            parent.setLayoutParams(layoutParams);
            parent.setOrientation(LinearLayout.VERTICAL);
            addView(parent);
            myHorizontalScrollView.addView(parent);
            Log.d("调试", i + "");
        }
    }

    public void addView(LinearLayout parent){
        for(int i=0;i<10;i++){
            TextView textView=new TextView(this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            textView.setText("name "+i);
            parent.addView(textView);
            Log.d("调试",i+"");
        }
    }

    private BaseAdapter mAdpater=new BaseAdapter(){

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
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                view.setTag(view.findViewById(R.id.item_title));
            }else{
                view=convertView;
            }
            ((TextView)view.getTag()).setText(getItem(position));
            return view;
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
