package com.creativeboy.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by heshaokang on 2016/2/14.
 */
public class ScrollerLayout extends ViewGroup{
    private static final String TAG = "ScrollerLayout";
    private Scroller mScroller;
    //判定是拖动的最小移动像素数
    private int mTouchSlop;
    //手指按下时的坐标
    private float mXDown;
    //手指移动后所处的坐标
    private float mXMove;
    //界面可滚动的左边界
    private int leftBorder;
    //界面可滚动的右边界
    private int rightBorder;

    private float mXLastMove;
    public ScrollerLayout(Context context) {
        super(context);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //创建Scroller实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }


    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++) {
            View childView = getChildAt(i);
            //为其子view测量大小
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed) {
            int childCount = getChildCount();
            for(int i=0;i<childCount;i++) {
                View childView = getChildAt(i);
                childView.layout(i*childView.getMeasuredWidth(),0,(i+1)*childView.getMeasuredWidth()
                ,childView.getMeasuredHeight());
            }

            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(childCount-1).getRight();
            Log.d(TAG,"leftBorder--"+leftBorder);
            Log.d(TAG,"rightBorder--"+rightBorder);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float dirX = Math.abs(mXDown-mXMove);
                mXLastMove = mXMove;
//                Log.d(TAG,"mTouchSlop--"+mTouchSlop);
//                Log.d(TAG,"mXLastMove--"+mXLastMove);
                if(dirX>mTouchSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove-mXMove);
                Log.d(TAG,"scrolledX--"+scrolledX);
                Log.d(TAG,"getScrollX()--"+getScrollX());
                Log.d(TAG,"getWidth--"+getWidth());
                if(getScrollX()+scrolledX<leftBorder) {
                    scrollTo(leftBorder,0);
                    return true;
                }else if(getScrollX()+getWidth()+scrolledX>rightBorder) {
                    scrollTo(rightBorder-getWidth(),0);
                    return true;
                }
                scrollBy(scrolledX,0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                int targetIndex = (getScrollX()+getWidth()/2)/getWidth();
                int dx = targetIndex*getWidth()-getScrollX();
                mScroller.startScroll(getScrollX(),0,dx,0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {

        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            Log.d(TAG,"getCurrX()--"+mScroller.getCurrX());
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }
}
