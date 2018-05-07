package com.demo.song.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.demo.song.demoset.R;
import com.demo.song.utils.Utils;

import java.util.ArrayList;

/**
 * Created by song on 2017/7/6.
 *      低内聚高耦合   哈哈哈哈
 * 不可以滚动     设置完成一定要调用build()
 */
public class BarChartView extends View {
    private final String TAG="com.demo.song.view.view.BarChartView";
    private String[] columnArray;   //多少条竖线分割线  目前只支持平均
    private float maxValue=-1; //柱形图最大值
    private String unit;     //柱形图单位
    private ArrayList<BarChartElement> mData;
    private Paint textPaint;    //单位画笔
    private Paint linePaint;    //线画笔
    private Paint barPaint;     //柱图画笔
    private float startPercent=0.02f;    //开始的百分比
    private float unitBottom;   //单位(40亿元)的bottom  已经包含paddingTop了
    private BarCharModule barCharModule=new BarCharModule(getContext());  //柱形图实体
    private final int textMargin=5;   //文字与矩形之间的距离
    private boolean isBuild=false;

    public void build(){   //计算wrap_content的高度
        isBuild=true;
        if(!isReady()){
            return;
        }
        String maxString="";
        for (int i=0;i<mData.size();i++) {
            if(mData.get(i).name.length()>maxString.length()){
                maxString=mData.get(i).name;
            }
        }
        barCharModule.maxString=maxString;
        barCharModule.totalHeight=barCharModule.defaultElementHeight *mData.size();
        barCharModule.maxTitleWidth=barCharModule.namePaint.measureText(maxString,0,maxString.length())+Utils.dip2px(getContext(),textMargin);
        Log.e(TAG, "setmData: " + maxString + barCharModule.maxTitleWidth);
        startPercent=0.02f;
        requestLayout();
    }

    /**
     * 是否已经准备好数据了
     * @return
     */
    private boolean isReady(){
        if(columnArray==null||columnArray.length==0||maxValue==-1||
                unit==null||mData==null||mData.size()==0||!isBuild){
            return false;
        }
        return true;
    }

    public BarChartView(Context context) {
        super(context);
        init();
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint =new Paint();
        barPaint=new Paint();
        linePaint=new Paint();
        linePaint.setColor(ContextCompat.getColor(getContext(), R.color.bar_gray));
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.bar_gray));
        barPaint.setColor(ContextCompat.getColor(getContext(), R.color.sky_bule));
        barPaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeWidth(Utils.dip2px(getContext(), 0.5f));   //线宽度为1
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(!isReady()){
            setMeasuredDimension(0,0);
            return;
        }
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int wrapHeight=getWrapContentHeight(width);     //得到默认的高度
        Log.e(TAG, "onMeasure: " + "外面" + height + "totalHeight:" + wrapHeight);
        if(heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(width, wrapHeight<height? wrapHeight :height);
            Log.e(TAG, "onMeasure: "+"里面"+height );
        }
    }

    private int getWrapContentHeight(int width) {   //暂时的模拟初始化
        int tempWidth= (int) (barCharModule.totalHeight+getPaddingBottom()+getUnitBottom(width));
        return tempWidth;
    }

    private int getUnitBottom(int width){    //得到“单位”所在位置的bottom
        if(columnArray==null)
            return 0;
        int tempWidth= (int) (width-getPaddingLeft()-getPaddingRight()-barCharModule.maxTitleWidth);
        textPaint.setTextSize(0.14f * (tempWidth / columnArray.length));
        return (int) (getPaddingTop()+Utils.getTextHeight(textPaint));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(!isReady()){
            return;
        }
        readyDraw();    //不知道用户最终设置了什么宽高  只能再算一次
        Log.e(TAG, "onLayout: " );
    }

    private void readyDraw() {
        unitBottom=getUnitBottom(getWidth());
        int height= (int) (getHeight()-getPaddingBottom()-unitBottom);
        barCharModule.elementHeight =height/mData.size();
        barCharModule.totalHeight=height;
        barCharModule.rectMargin =barCharModule.elementHeight *0.2f;
        barCharModule.rectHeight =barCharModule.elementHeight *0.8f;
        barCharModule.namePaint.setTextSize(barCharModule.rectHeight * 0.69f);
        barCharModule.maxTitleWidth=
                Utils.getTextWidth(barCharModule.maxString,barCharModule.namePaint)+Utils.dip2px(getContext(),textMargin);
        Log.e(TAG, "readyDraw1: " + "   getHeight():" + getMeasuredHeight() + "   getPaddingTop():" +
                getPaddingTop() + "   getPaddingBottom():" + getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isReady()){
            return;
        }
        int width= (int) (getWidth()-getPaddingLeft()-getPaddingRight()-barCharModule.maxTitleWidth);
        int height=getHeight()-getPaddingTop()-getPaddingBottom();
        float distancePer=1f/columnArray.length;   //单位宽度
        int currentDistance=0;     //当前竖线的x坐标
        for(int i=0;i<columnArray.length;i++){   //画单位和垂直的线
            currentDistance= (int) (getPaddingLeft()+barCharModule.maxTitleWidth+distancePer*width*(i+1));
            float start=currentDistance-Utils.getTextWidth(columnArray[i] + unit,textPaint);
            float baseLine=getPaddingTop()-Utils.getTextTop(textPaint);

            canvas.drawText(columnArray[i]+ unit,start,baseLine, textPaint);

            float startX=currentDistance;
            float startY=getPaddingTop()+Utils.getTextHeight(textPaint);
            float endX=currentDistance;
            float endY=getPaddingTop()+startPercent*height;

            canvas.drawLine(startX,startY,endX, endY, linePaint);
        }
        //画横线
        canvas.drawLine(getPaddingLeft() + barCharModule.maxTitleWidth + width, unitBottom,
                getPaddingLeft() + barCharModule.maxTitleWidth + width - startPercent * width,
                unitBottom, linePaint);

        Paint.FontMetrics fontMetrics=barCharModule.namePaint.getFontMetrics();
        float offsetX=getPaddingLeft()+barCharModule.maxTitleWidth;
        float offsetY=unitBottom+barCharModule.rectMargin;
        for(int j=0;j<mData.size();j++){   //画名称和柱形图
            float start=getPaddingLeft();
            float baseLine=unitBottom+
                    barCharModule.rectMargin +j*barCharModule.elementHeight +
                    (barCharModule.rectHeight +fontMetrics.top-fontMetrics.bottom)/2-fontMetrics.top;
            canvas.drawText(mData.get(j).name, start, baseLine, barCharModule.namePaint);
            float left=offsetX;
            float top=offsetY+j*barCharModule.elementHeight;
            float right=offsetX+startPercent*(width*(mData.get(j).size/maxValue));
            float bottom=offsetY+j*barCharModule.elementHeight
                    +barCharModule.rectHeight;
            canvas.drawRect(left,top,right,bottom,barPaint);
            Log.e(TAG, "onDraw: "+left+":"+top+":"+right+":"    +bottom);
        }

        if(startPercent<1){       //等同于插值器
            startPercent+=0.02f;
            if(startPercent>1){
                startPercent=1f;
            }
            postInvalidateDelayed(10);
        }
    }

    public BarChartView setColumnSize(String[] columnSize) {
        if(columnSize==null||columnSize.length==0   ){
            return this;
        }
        this.columnArray = columnSize;
        return this;
    }

    public BarChartView setMaxValue(float maxValue) {
        if(maxValue<=0){
            return this;
        }
        this.maxValue = maxValue;
        return this;
    }

    public BarChartView setUnit(String unit) {
        if(unit==null){
            return this;
        }
        this.unit = unit;
        return this;
    }

    public BarChartView setmData(ArrayList<BarChartElement> mData) {   //默认值
        if(mData==null){
            return this;
        }
        this.mData = mData;
        return this;
    }

    /**
     * 柱形图配置实体  相关变量在一起方便看
     *      *代表测量前要初始化的
     */
    static class BarCharModule{
        private Paint namePaint;    //公司名画笔
        float totalHeight;          //柱形总高度                                    *
        float maxTitleWidth;        //最大公司名的宽度                              *
        float rectHeight;        //一个柱形的高度
        float rectMargin;        //柱形图之间的距离
        float elementHeight;         //默认每个柱形图高度是60                       *
        float defaultElementHeight;
        String maxString;

        public BarCharModule(Context context){
            namePaint=new Paint();
            namePaint.setColor(ContextCompat.getColor(context, R.color.bar_gray));
            defaultElementHeight =Utils.dip2px(context,20f);
        }
    }

    public static class BarChartElement{
        public String name;
        public float size;

        public BarChartElement(String name, float size) {
            this.name = name;
            this.size = size;
        }
    }
}
