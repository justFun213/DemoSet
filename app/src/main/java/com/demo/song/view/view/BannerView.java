package com.demo.song.view.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.song.demoset.R;

import java.util.List;

/**
 * Created by song on 2017/6/17.
 * 有数据才设置adpater
 */
public class BannerView extends RelativeLayout {
    private ViewPager viewPager;
    private LinearLayout mPoints;
    private AutoBannerTask mTask;
    private int dataCount =0;
    private OnClickItemListener onClickItemListener=null;
    private BannerAdpater mAdpater=null;

    public BannerView(Context context) {
        super(context);
        initData(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    private void initData(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        mPoints= (LinearLayout) findViewById(R.id.points);
        initListener();
        mTask=new AutoBannerTask();
    }

    public void start(){
        mTask.start();
    }

    public void stop(){
        mTask.stop();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    public void setAdpater(BannerAdpater adpater){
        if(adpater==null)
        {
            return;
        }
        mAdpater=adpater;
        mTask.stop();
        viewPager.setAdapter(adpater);
        dataCount =adpater.getDataCount();
        if(dataCount==0){
            return;
        }
        intitPoint(0);
        int index=Integer.MAX_VALUE/2;
        index=index-(index% dataCount);         //保证取模后在第一个点
        viewPager.setCurrentItem(index);
        mTask.start();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPoints(position % dataCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOnTouchListener(new OnTouchListener() {
            private boolean isMove = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isMove = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mTask.stop();
                        isMove = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        mTask.start();
                        if (!isMove) {
                            if (onClickItemListener != null) {
                                onClickItemListener.onClickItem(viewPager.getCurrentItem() % dataCount);
                                return true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public int getCurrentItem(){
        return viewPager.getCurrentItem();
    }

    public void setOnClickItemListener(OnClickItemListener listener){
        onClickItemListener=listener;
    }

    private void intitPoint(int target) {
        mPoints.removeAllViews();
        for(int i=0;i< dataCount;i++){
            ImageView child=new ImageView(getContext());
            if(i==target){
                child.setImageResource(R.drawable.shape_indicator_select);
            }else{
                child.setImageResource(R.drawable.shape_indicator_normal);
            }
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin=dip2px(getContext(),4);
            child.setLayoutParams(layoutParams);
            mPoints.addView(child);
        }
    }

    public static int dip2px(Context context,int dp){
        float scale=context.getResources().getDisplayMetrics().density;
        int px= (int) (scale*dp+0.5f);
        return px;
    }

    private void setPoints(int position) {
        for(int i=0;i< dataCount;i++){
            ImageView child= (ImageView) mPoints.getChildAt(i);
            if(i==position){
                child.setImageResource(R.drawable.shape_indicator_select);
            }else{
                child.setImageResource(R.drawable.shape_indicator_normal);
            }
        }
    }

    class AutoBannerTask implements Runnable{

        @Override
        public void run() {
            int index=viewPager.getCurrentItem();
            index++;
            viewPager.setCurrentItem(index);
            postDelayed(this, 2000);
            Log.e("index:", index + "");
        }

        public void start(){
            stop();
            postDelayed(this, 2000);
        }

        public void stop(){
            removeCallbacks(this);
        }
    }

    public  static abstract class BannerAdpater extends PagerAdapter{
        public abstract int getDataCount();
        public abstract ImageView getImageView(int position);

        @Override
        public int getCount() {
            return getDataCount()>0?Integer.MAX_VALUE:0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=getImageView(position%getDataCount());
            container.addView(imageView);
            return imageView;
        }

        /**
         * 数据刷新，请调用这个
         * @param bannerView
         */
        public void notifyDataSetChanged(BannerView bannerView) {
            super.notifyDataSetChanged();
            bannerView.dataCount=getDataCount();
            bannerView.intitPoint(bannerView.getCurrentItem()%bannerView.dataCount);
            bannerView.mTask.start();
        }
    }

    public  interface OnClickItemListener{
        void onClickItem(int position);
    }
}
