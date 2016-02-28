package com.creativeboy.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.creativeboy.customview.R;

/**
 * Created by heshaokang on 2016/2/28.
 */
public class CustomProgressView extends View {

    private int mFirstColor; //第一个颜色
    private int mSecondColor; //第二颜色
    private int mWidth; //宽度
    private int speed; //速度

    private Paint mPaint;
    private int progress=0;
    private boolean isNext=false;

    public CustomProgressView(Context context) {
        this(context, null);
    }

    public CustomProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressView,defStyleAttr,0);
        int count = a.getIndexCount();
        for(int i=0;i<count;i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.CustomProgressView_firstColor:
                    mFirstColor = a.getColor(attr, Color.YELLOW);
                    break;
                case R.styleable.CustomProgressView_secondColor:
                    mSecondColor = a.getColor(attr,Color.BLUE);
                    break;
                case R.styleable.CustomProgressView_width:
                    mWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX,20,getResources().getDisplayMetrics()
                    ));
                    break;
                case R.styleable.CustomProgressView_speed:
                    speed = a.getInt(attr,20);
                    break;
            }
        }
        a.recycle();

        mPaint = new Paint();
        //绘制
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    progress++;
                    if(progress==360) {
                        progress=0;
                        if(!isNext) {
                            isNext = true;
                        }else {
                            isNext = false;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth()/2;
        int radius = centre-mWidth/2;
        mPaint.setStrokeWidth(mWidth);
        mPaint.setAntiAlias(true); //清除锯齿
        mPaint.setStyle(Paint.Style.STROKE); //设置空心
        RectF oval = new RectF(centre-radius,centre-radius,centre+radius,centre+radius);

        if(!isNext) {
            mPaint.setColor(mFirstColor); //设置第一圈颜色
            canvas.drawCircle(centre, centre, radius, mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(oval,-90,progress,false,mPaint);
        }else {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, progress, false, mPaint); // 根据进度画圆弧
        }

    }
}
