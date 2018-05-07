package com.demo.song.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.song.demoset.R;
import com.demo.song.utils.Utils;

/**
 * Created by song on 2017/7/3.
 */
public class PercentCircleView extends View {

    private static final String TAG="com.demo.song.view.view.PercentCircleView";
    private Paint mRingPaint=new Paint();
    private Paint mCirclePaint=new Paint();
    private Paint mTextPaint=new Paint();
    private int radius;
    private int tempDegress=0;
    private boolean isFirstDraw=true;
    private float percent=0.5f;   //默认50%
    private String percentString="50.0%";  //默认

    public PercentCircleView(Context context) {
        this(context,null,0);
    }

    public PercentCircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PercentCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "PercentCircle: mTextPaint" + "PercentCircleView");
        mRingPaint.setAntiAlias(true);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
//        mTextPaint.setTextSize(Utils.dip2px(getContext(),18));
        mTextPaint.setStyle(Paint.Style.FILL);
        if(attrs!=null){
            TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.PercentCircleView);
            radius= (int) a.getDimension(R.styleable.PercentCircleView_radius,-1);  //-1则取宽高最小值
            int circleColor=a.getColor(R.styleable.PercentCircleView_circle_color, Color.BLUE);
            int ringColor=a.getColor(R.styleable.PercentCircleView_ring_color,Color.RED);
            int textColor=a.getInt(R.styleable.PercentCircleView_text_color,Color.WHITE);
            a.recycle();
            mRingPaint.setColor(ringColor);
            mCirclePaint.setColor(circleColor);
            mTextPaint.setColor(textColor);
            Log.d(TAG, "PercentCircle: radius" + radius);
            Log.d(TAG, "PercentCircle: mRingPaint"+ringColor);
            Log.d(TAG, "PercentCircle: mCirclePaint"+circleColor);
            Log.d(TAG, "PercentCircle: mTextPaint" + textColor);
        }else{//默认设置
            radius=-1;
            mRingPaint.setColor(Color.RED);
            mCirclePaint.setColor(Color.BLUE);
            mTextPaint.setColor(Color.WHITE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){//宽高都是wrap_content
            setMeasuredDimension((int) (radius*2.1), (int) (radius*2.1));
        }else if(widthMode==MeasureSpec.AT_MOST){  //宽度是wrap_content
            setMeasuredDimension(radius*2,height);
        }else if(heightMode==MeasureSpec.AT_MOST){  //高度是wrap_content
            setMeasuredDimension(width,radius*2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth()-getPaddingLeft()-getPaddingRight();
        int height=getHeight()-getPaddingBottom()-getPaddingTop();
        Log.e(TAG, "onDraw: "+"  getWidth():"+getWidth()+"  getHeight():"+getHeight() );
        if(isFirstDraw){         //第一次绘制时候调用
            //判断radius==-1？
            if(radius<0){
                radius=Math.min(width,height)/2;
            }
            //宁愿缩小半径   也要padding正确
            radius=Math.max(0,Math.min(radius,Math.min(width,height)/2));
            mRingPaint.setStrokeWidth((float) (0.05 * radius));
            radius= (int) (0.95*radius);
            mTextPaint.setTextSize(radius / 2);
            isFirstDraw=false;
        }
        if(radius>0){   //有内容绘制
            canvas.drawCircle(getPaddingLeft() + width/2, getPaddingTop() + height/2, radius, mCirclePaint);
            Rect rectText=new Rect();
            mTextPaint.getTextBounds(percentString, 0, percentString.length(), rectText);
            Paint.FontMetrics fontMetrics=mTextPaint.getFontMetrics();
            int textBottoom= (int) (getPaddingTop()+height/2-((-fontMetrics.top+fontMetrics.bottom)/2)-fontMetrics.top);
            canvas.drawText(percentString, getPaddingLeft()+width/2-rectText.width()/2,textBottoom, mTextPaint);
            RectF rect=new RectF(getPaddingLeft() + width/2-radius,getPaddingTop() + height/2-radius,getPaddingLeft() + width/2+radius,
                    getPaddingTop() + height/2+radius);
            int targetDegress= (int) (360*percent);
            if(tempDegress<=targetDegress){
                canvas.drawArc(rect,-90,tempDegress,false,mRingPaint);
                tempDegress+=4;
                postInvalidateDelayed(10);
            }else{
                tempDegress=targetDegress;
                canvas.drawArc(rect,-90,tempDegress,false,mRingPaint);
            }
        }
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        if(percent<0f){
            Log.d(TAG, "setPercent: percent要大于0");
            return;
        }
        this.percent = percent;
        String tempString=percent*100+"";
        int pointIndex=tempString.indexOf(".");
        if(tempString.length()-1==pointIndex){   //小数点后面没有数字    最终目的，只保留一位小数
            tempString=tempString+"0";
        }
        percentString=tempString.substring(0,tempString.length()>pointIndex+2?pointIndex+2:tempString.length())+"%";
        tempDegress=0;
        postInvalidate();
    }
}
