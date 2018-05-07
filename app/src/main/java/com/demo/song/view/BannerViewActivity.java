package com.demo.song.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.song.demoset.R;
import com.demo.song.view.view.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 在activity退出时一定要调用stop方法，不然会内存泄漏
 */
public class BannerViewActivity extends AppCompatActivity {
    private BannerView mBannerView;
    private List<Integer> mList=new ArrayList();
    private BannerView.BannerAdpater mAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);
        initData();
        initBannerView();
    }

    private void initData() {
        mList.add(R.drawable.lunbo_01);
        mList.add(R.drawable.lunbo_02);
        mList.add(R.drawable.lunbo_03);

        mAdpater=new BannerView.BannerAdpater() {

            @Override
            public int getDataCount() {
                return mList.size();
            }

            @Override
            public ImageView getImageView(int position) {
                Log.d("position:",position+"");
                ImageView imageView=new ImageView(BannerViewActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(mList.get(position));
                return imageView;
            }

        };
    }

    private void initBannerView() {
        mBannerView= (BannerView) findViewById(R.id.banner_view);
        mBannerView.setAdpater(mAdpater);
        mBannerView.setOnClickItemListener(new BannerView.OnClickItemListener() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(BannerViewActivity.this, "当前点击的是第" + position + "个页面", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addPage(View view){
        mList.add(R.drawable.lunbo_03);
        mList.add(R.drawable.lunbo_02);
        mList.add(R.drawable.lunbo_01);
        mList.add(R.drawable.lunbo_02);
        mAdpater.notifyDataSetChanged(mBannerView);
    }
}
