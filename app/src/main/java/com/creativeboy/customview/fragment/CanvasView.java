package com.creativeboy.customview.fragment;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by littlekang on 16/6/11.
 */
public class CanvasView extends View{
    private static final String TAG = CanvasView.class.getSimpleName();
    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private ValueAnimator valueAnimator;
    private float animatedValue;
    private long animatorDurtion = 5000;
    private TimeInterpolator timeInterpolator = new DecelerateInterpolator();
    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAnimator(animatorDurtion);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.drawPoint(0,0,mPaint);
        canvas.drawPoints(new float[]{
                mWidth/2*0.8f,0
                ,0,mHeight/2*0.8f
                ,-mWidth/2*0.8f,0
                ,0,-mHeight/2*0.8f},mPaint);
        mPaint.setStrokeWidth(1);
        canvas.drawLine(-mWidth/2*0.8f,0,mWidth/2*0.8f,0,mPaint);
        canvas.drawLine(0,-mHeight/2*0.8f,0,mHeight/2*0.8f,mPaint);
        mPaint.setStrokeWidth(3);
        //绘制x轴箭头
        canvas.drawLines(new float[]{
                mWidth/2*0.8f*0.95f,-mWidth/2*0.8f*0.05f,
                mWidth/2*0.8f,0,
                mWidth/2*0.8f,0,
                mWidth/2*0.8f*0.95f,
                mWidth/2*0.8f*0.05f
        },mPaint);
        //绘制y轴箭头
        canvas.drawLines(new float[]{
                0,mHeight/2*0.8f,
                mHeight/2*0.8f*0.03f,mHeight/2*0.8f*0.97f,
                0,mHeight/2*0.8f,
                -mHeight/2*0.8f*0.03f,mHeight/2*0.8f*0.97f,
        },mPaint);

//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.GREEN);
//        mPaint.setStrokeWidth(10);
//        float point = Math.min(mWidth,mHeight)*0.1f;
//        float r = (float) (point*Math.sqrt(2));
//        RectF rectF = new RectF(-r,-r,r,r);
//        canvas.drawArc(rectF,0,180,false,mPaint);
//        canvas.drawPoints(new float[]{
//                point,-point
//                ,-point,-point
//        },mPaint);

        doubanAnimator(canvas,mPaint);
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true); //抗锯齿
    }


    private void initAnimator(long durtion) {
        if(valueAnimator!=null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
            valueAnimator.start();
        }else {
            valueAnimator = ValueAnimator.ofFloat(0,855).setDuration(durtion);
            valueAnimator.setInterpolator(timeInterpolator);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animatedValue = (float) animation.getAnimatedValue();
                    //Log.d(TAG,"animatedValue-"+animatedValue);
                    invalidate();
                }
            });
            valueAnimator.start();

        }
    }

    private void doubanAnimator(Canvas canvas, Paint mPaint){
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角笔触
        mPaint.setColor(Color.rgb(97, 195, 109));
        mPaint.setStrokeWidth(15);
        float point = Math.min(mWidth,mHeight)*0.1f;
        float r = point*(float) Math.sqrt(2);
        RectF rectF = new RectF(-r,-r,r,r);
        canvas.save();

        // rotate
        if (animatedValue>=135){
            canvas.rotate(animatedValue-135);
        }

        // draw mouth
        float startAngle=0, sweepAngle=0;
        if (animatedValue<135){
            startAngle = animatedValue +5;
            sweepAngle = 170+animatedValue/3;
        }else if (animatedValue<270){
            startAngle = 135+5;
            sweepAngle = 170+animatedValue/3;
        }else if (animatedValue<630){
            startAngle = 135+5;
            sweepAngle = 260-(animatedValue-270)/5;
        }else if (animatedValue<720){
            startAngle = 135-(animatedValue-630)/2+5;
            sweepAngle = 260-(animatedValue-270)/5;
        }else{
            startAngle = 135-(animatedValue-630)/2-(animatedValue-720)/6+5;
            sweepAngle = 170;
        }
        canvas.drawArc(rectF,startAngle,sweepAngle,false,mPaint);

        // draw eye
        canvas.drawPoints(new float[]{
                -point,-point
                ,point,-point
        },mPaint);

        canvas.restore();
    }


}
