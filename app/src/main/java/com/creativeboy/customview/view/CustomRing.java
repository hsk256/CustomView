package com.creativeboy.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by heshaokang on 2015/4/30.
 */
public class CustomRing extends View {
    //圆环坐标
    private int cx;
    private int cy;
    private int radius;
    private Paint paint;
    private int strokeWidth; //线条宽度


    public CustomRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomRing(Context context) {
        super(context);
    }
    public void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE); //采用线条式
        paint.setAntiAlias(true); //抗锯齿 解决边缘出现锯齿问题
        paint.setAlpha(255);
        paint.setStrokeWidth(strokeWidth);

        radius = 0;
        strokeWidth = 0;
    }
    private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flushState();
            if(paint.getAlpha()!=0) {
                sendEmptyMessageDelayed(0,100);
            }
        }
    };
    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx,cy,radius,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cx = (int) event.getX();
                cy = (int) event.getY();
                init(); //初始化画笔
                handler.sendEmptyMessage(0);
                break;
        }

        return true;
    }
    public void flushState() {
        this.radius+=10;
        this.strokeWidth = radius/4;
        int nextAlpha = paint.getAlpha()-20;
        if(nextAlpha<=20) {
            nextAlpha = 0;
        }
        paint.setStrokeWidth(strokeWidth);
        paint.setAlpha(nextAlpha);
        invalidate();
    }

}
