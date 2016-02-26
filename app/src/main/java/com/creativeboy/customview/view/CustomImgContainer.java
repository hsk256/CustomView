package com.creativeboy.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by heshaokang on 2016/2/26.
 * 自定义ViewGroup 使其子view在左上 右上 左下 右下
 */
public class CustomImgContainer extends ViewGroup{
    private static final String TAG="CustomImgContainer";
    public CustomImgContainer(Context context) {
        super(context);
    }

    public CustomImgContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImgContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams params = null;
        for(int i=0;i<count;i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            params = (MarginLayoutParams) childView.getLayoutParams();

            int ct=0,cr=0,cl=0,cb=0;
            switch (i) {
                case 0:
                    cl = params.leftMargin;
                    ct = params.topMargin;
                    break;
                case 1:
                    cl = getWidth()-cWidth-params.rightMargin;
                    ct = params.topMargin;
                    break;
                case 2:
                    cl = params.leftMargin;
                    ct = getHeight()-cHeight-params.bottomMargin;
                    break;
                case 3:
                    cl = getWidth()-cWidth-params.rightMargin;
                    ct = getHeight()-cHeight-params.bottomMargin;
                    break;
            }
            cr = cWidth+cl;
            cb = cHeight+ct;
            childView.layout(cl,ct,cr,cb);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //获取当前viewGroup上层容器为其指定的宽和高 以及测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        //计算出所有的ChildView的宽和高
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        /***************当子view宽高为wrap_content时的计算****************************/
        //当前容器的宽和高
        int width = 0;
        int height = 0;
        //获取子view的数量
        int count = getChildCount();
        //子view的宽和高
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        int lHeight=0;
        int rHeight=0;
        int tWidth = 0;
        int bWidth = 0;

        //根据childview 计算出的宽和高 以及设置的margin计算容器的宽和高
        for(int i=0;i<count;i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            if(i==0||i==1) {
                tWidth += cWidth+cParams.leftMargin+cParams.rightMargin;
            }else if(i==0||i==2) {
                lHeight += cHeight+cParams.topMargin+cParams.bottomMargin;
            }else if(i==1||i==3) {
                rHeight +=cHeight+cParams.leftMargin+cParams.bottomMargin;
            }else if(i==2||i==3) {
                bWidth +=cWidth+cParams.leftMargin+cParams.rightMargin;
            }
        }
        width = Math.max(tWidth,bWidth);
        height = Math.max(lHeight,rHeight);
        setMeasuredDimension((widthMode==MeasureSpec.EXACTLY)?sizeWidth:width,
                (heightMode==MeasureSpec.EXACTLY)?sizeHeight:height);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        Log.d(TAG, "generateDefaultLayoutParams");
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        Log.d(TAG, "generateDefaultLayoutParams");
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }
}
