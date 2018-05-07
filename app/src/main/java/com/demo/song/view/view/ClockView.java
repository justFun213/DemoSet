package com.demo.song.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.song.utils.Utils;

import java.util.Calendar;

/**
 * Created by song on 2017/7/13.
 */
public class ClockView extends View {
    private final String TAG="com.demo.song.view.view.ClockView";
    private Paint blackPain;
    private Paint redPain;
    private float x;   //圆心x
    private float y;   //圆心y
    private float radius=-1;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        blackPain=new Paint();
        blackPain.setColor(Color.BLACK);
        blackPain.setAntiAlias(true);
        redPain=new Paint();
        redPain.setColor(Color.RED);
        redPain.setAntiAlias(true);
        redPain.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  //默认50dp
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            int temp= (int) Math.min(Utils.dip2px(getContext(),100f),Math.min(width, height));  //100dp和宽高最小的
            setMeasuredDimension(temp, temp);
        }else if(widthMode==MeasureSpec.AT_MOST){
            int temp= (int) Math.min(Utils.dip2px(getContext(),100f),Math.min(width, height));
            setMeasuredDimension(temp, height);
        }else if(heightMode==MeasureSpec.AT_MOST){
            int temp= (int) Math.min(Utils.dip2px(getContext(),100f),Math.min(width, height));
            setMeasuredDimension(width, temp);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {//仅做计算
        super.onLayout(changed, left, top, right, bottom);
        int tempx=(getWidth()-getPaddingLeft()-getPaddingRight())/2;
        int tempy=(getHeight()-getPaddingTop()-getPaddingBottom())/2;
        x=tempx+getPaddingLeft();
        y=tempy+getPaddingTop();
        radius=Math.min(tempy,tempx)-Utils.dip2px(getContext(), 0.5f);  //为什么会有误差？？难道是画笔粗细？？
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(radius<=0){
            return;
        }
        drawDecor(canvas);
        drawClockLine(canvas);
        Log.e(TAG, "onDraw:  index ClockView");
        postInvalidateDelayed(1000);
    }

    private void drawDecor(Canvas canvas) {
        blackPain.setStrokeWidth(Utils.dip2px(getContext(), 0.5f));
        blackPain.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, radius, blackPain);
        canvas.save();
        blackPain.setStyle(Paint.Style.FILL);
        blackPain.setTextSize(0.17f*radius);
        float hourLeft= x-0.02f*radius*0.5f;
        float hourRight=x+0.02f*radius*0.5f;
        float hourTop=y-radius;
        float hourBottom=hourTop+0.14f*radius;
        float minLeft=(float) (x-(0.01f*radius*0.5));
        float miinRight=(float) (x+(0.01f*radius*0.5));
        float minTop=y-radius;
        float minBottom=y-radius+0.07f*radius;
        for(int i=0,j=12;i<60;i++){
            if(i%5==0){  //时钟刻度   时钟数字
                canvas.drawRect(hourLeft, hourTop, hourRight, hourBottom, blackPain);
                float start=x-blackPain.measureText(String.valueOf(j)) * 0.5f;
                float end= hourBottom+Utils.dip2px(getContext(),0.5f)-Utils.getTextTop(blackPain);
                canvas.drawText(String.valueOf(j),start,end,blackPain);
                j--;
            }else{      //分钟刻度
                canvas.drawRect(minLeft,minTop,miinRight,minBottom,blackPain);
            }
            canvas.rotate(-6, x, y);  //转动画布
        }
        canvas.restore();
    }


    private void drawClockLine(Canvas canvas) {
        Calendar now=Calendar.getInstance();
        int hour=now.get(Calendar.HOUR_OF_DAY);
        int min=now.get(Calendar.MINUTE);
        int second=now.get(Calendar.SECOND);
        float hourHeight=0.5f*radius;  //时针高度
        float minHeight=0.6f*radius;
        float secondHeight=0.9f*radius;
        float hourWidth=0.03f*radius;  //时针宽度
        float minWidth=0.5f*hourWidth;
        float secondWidth=0.5f*minWidth;
        float degress;

        degress=second * 6;
        drawTimeLine(canvas,secondWidth,secondHeight,degress,redPain);  //画秒针

        degress=min * 6+6*(second/60f);
        drawTimeLine(canvas,minWidth,minHeight,degress,blackPain);  //画分针

        degress=hour * 30+30*(min/60f);
        drawTimeLine(canvas,hourWidth,hourHeight,degress,blackPain);  //画时针

        blackPain.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, radius * 0.05f, blackPain); //内圈圆
    }

    private void drawTimeLine(Canvas canvas,float width,float height,float degress,Paint paint){
        float tempLeft=x-width/2;
        float tempTop=y-height;
        float tempRight=tempLeft+width;
        float tempBottom=y+height*0.15f;
        canvas.save();
        canvas.rotate(degress, x, y);
        canvas.drawRect(tempLeft,tempTop,tempRight,tempBottom,paint);  //画时分秒针
        canvas.restore();
    }
}
