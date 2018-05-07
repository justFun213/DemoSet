package com.demo.song.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.song.demoset.MainActivity;
import com.demo.song.demoset.R;
import com.demo.song.view.view.TestTextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ListViewTestAcctivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test_acctivity);
        listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(adpater);
        setListViewHeightBasedOnChildren(listView);
        TestTextView testTextView=(com.demo.song.view.view.TestTextView)findViewById(R.id.test);
        testTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final EditText et = new EditText(ListViewTestAcctivity.this);
                 et.setFocusable(true);
                et.setFocusableInTouchMode(true);
                et.requestFocus();
                InputMethodManager inputManager =
                        (InputMethodManager)et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(et, 0);

                AlertDialog mAlert= new AlertDialog.Builder(ListViewTestAcctivity.this).setTitle("搜索")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String input = et.getText().toString();
                                if (input.equals("")) {
                                    Toast.makeText(getApplicationContext(), "搜索内容不能为空！" + input, Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Intent intent = new Intent();
                                    intent.putExtra("content", input);
                                    intent.setClass(ListViewTestAcctivity.this, HorizontalScrollActivity.class);
                                    startActivity(intent);
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                Window window = mAlert.getWindow();
                window.setGravity(Gravity.BOTTOM);   //window.setGravity(Gravity.BOTTOM);

            }
        });//setOnClickListener
    }

    private BaseAdapter adpater=new BaseAdapter() {
        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return LayoutInflater.from(ListViewTestAcctivity.this).inflate(R.layout.lv_banyou_item,parent,false);
        }
    };

    /**
     * 解决scrollview嵌套listview只显示一行的问题
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        Log.d("setListViewHeightBasedOnChildren",listAdapter.getCount()+"");
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
