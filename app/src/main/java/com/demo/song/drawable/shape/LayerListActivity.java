package com.demo.song.drawable.shape;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.song.demoset.R;
import com.demo.song.fragment.fragment.TabFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

public class LayerListActivity extends AppCompatActivity implements TabFragment.OnFragmentInteractionListener {

    @BindViews({R.id.button1,R.id.button2,R.id.button3})
    List<Button> buttonList;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer_list);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TabFragment.newInstance(position + "", "");
            }

            @Override
            public int getCount() {
                return buttonList.size();
            }
        });
        onClick(buttonList.get(0));
    }

    //仅做练习  该方法无用
    @OnPageChange(value = R.id.viewPager,callback = OnPageChange.Callback.PAGE_SCROLLED)
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("测试", "onPageScrolled: "+position+ "   positionOffset:"+positionOffset+"   " +
                "positionOffsetPixels:"+positionOffsetPixels);
    }

    @OnPageChange(value = R.id.viewPager,callback = OnPageChange.Callback.PAGE_SELECTED)
    public void onPageChange(int position){
    /*    for(int i=0;i<buttonList.size();i++){
            if(i==position){
                buttonList.get(i).setSelected(true);
            }else{
                buttonList.get(i).setSelected(false);
            }
        }*/
        ButterKnife.apply(buttonList,ENABLE,false);
        ButterKnife.apply(buttonList.get(position),ABLE);
    }


    @OnClick({R.id.button1,R.id.button2,R.id.button3})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                onPageChange(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.button2:
                onPageChange(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.button3:
                onPageChange(2);
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    static ButterKnife.Action<View> ABLE=new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int i) {
            view.setSelected(true);
        }
    };


    static ButterKnife.Setter<View,Boolean> ENABLE=new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean aBoolean, int i) {
            view.setSelected(aBoolean);
        }
    };
}
