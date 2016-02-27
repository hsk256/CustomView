package com.creativeboy.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.creativeboy.customview.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by heshaokang on 2016/2/27.
 * 自定义可以点击变换随机数的view
 */
public class CustomTitleView extends View {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CustomTitleView,defStyleAttr,0);
        int n = a.getIndexCount();
        for(int i=0;i<n;i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleTextCustom:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleColor:
                    //默认蓝色
                    mTitleTextColor = a.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomTitleView_titleSize:
                    //默认16sp
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()
                    ));
                    break;
            }
        }

        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        //mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        if(widthMode==MeasureSpec.EXACTLY) {
            width = widthSize;
        }else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textWidth = mBound.width();
            int disired = (int) (getPaddingLeft()+textWidth+getPaddingRight());
            width = disired;

        }

        if(heightMode==MeasureSpec.EXACTLY) {
            height = heightSize;
        }else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textHeight = mBound.height();
            int disired = (int) (getPaddingBottom()+textHeight+getPaddingTop());
            height = disired;
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2-mBound.height()/2,mPaint);
    }

    private String randomText() {

        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(set.size()<4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }

        StringBuilder str = new StringBuilder();
        for(Integer i:set) {
            str.append(i+"");
        }
        return str.toString();
    }
}
