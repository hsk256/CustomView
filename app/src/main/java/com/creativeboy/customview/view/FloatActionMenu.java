package com.creativeboy.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.creativeboy.customview.R;


/**
 * Created by heshaokang on 2015/5/2.
 */
public class FloatActionMenu extends ViewGroup implements View.OnClickListener{
    private static final String TAG = "FloatActionMenu";
    private boolean isExpanded = false;
    private View mMenuView;
    private OnMenuItemClickListener mMenuItemClickListener;

    //view在屏幕中的位置 指定两种
    private static final int POS_LEFT_BOTTOM = 0; //左下角
    private static final int POS_RIGHT_BOTTOM = 1; //右下角
    //view相对于父view的外边距
    private int margin_left ;
    private int margin_right;
    private int margin_bottom;
    //子view间距
    private int ChildMargin= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
    private enum Status {
        CLOSE,OPEN
    }
    //菜单状态
    private Status mCurrentStatus = Status.CLOSE;
    public enum Position {
        LEFT_BOTTOM,RIGHT_BOTTOM
    }
    public Position mPosition = Position.LEFT_BOTTOM;
    public FloatActionMenu(Context context) {
        this(context, null);
    }

    public FloatActionMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    //item view点击事件回调接口
    public interface OnMenuItemClickListener {
        void onClcik(View view,int pos);
    }



    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mMenuItemClickListener = listener;
    }

    public void init(Context context,AttributeSet attributeSet) {
        TypedArray attr = null;
        try {
            attr = context.obtainStyledAttributes(attributeSet, R.styleable.float_menu,0,0);
            int pos = attr.getInt(R.styleable.float_menu_position,POS_LEFT_BOTTOM);
            switch (pos) {
                case POS_LEFT_BOTTOM:
                    mPosition = Position.LEFT_BOTTOM;
                    break;
                case POS_RIGHT_BOTTOM:
                    mPosition = Position.RIGHT_BOTTOM;
                    break;
            }
            margin_left = attr.getInt(R.styleable.float_menu_margin_left,20);
            margin_right = attr.getInt(R.styleable.float_menu_margin_right,20);
            margin_bottom = attr.getInt(R.styleable.float_menu_margin_bottom,20);
        }finally {
            attr.recycle();
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     *  用来定位view
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuViewLayout();
        int count = getChildCount();
        for(int i=0;i<count-1;i++) {
            View child = getChildAt(i+1);
            //默认子view不显示
            child.setVisibility(View.GONE);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int cl = 0;
            int ct = 0;
            if(mPosition==Position.LEFT_BOTTOM){
                ct = getMeasuredHeight()-(height+ChildMargin)*(i+2);
                cl = margin_left;
            }else if(mPosition==Position.RIGHT_BOTTOM) {
                ct = getMeasuredHeight()-(height+ChildMargin)*(i+2);
                cl = getMeasuredWidth()-width-margin_right;
            }

            child.layout(cl,ct,cl+width,ct+height);
        }

    }

    @Override
    public void onClick(View v) {

        rotateMenu(v,0f,405f,300);
        toggleMenu(1000);
    }


    /**
     * 定位主按钮 即菜单按钮
     */
    public void menuViewLayout() {
        mMenuView = getChildAt(0);
        mMenuView.setOnClickListener(this);
        int width = mMenuView.getMeasuredWidth();
        int height = mMenuView.getMeasuredHeight();
        int l = 0;
        int t = 0;
        switch (mPosition) {
            case LEFT_BOTTOM:
                 t = getMeasuredHeight()-height-margin_bottom;
                 l = margin_left;
                break;
            case RIGHT_BOTTOM:
                t = getMeasuredHeight()-height-margin_bottom;
                l = getMeasuredWidth()-width-margin_right;
                break;
        }

        mMenuView.layout(l,t,l+width,t+height);
    }



    //menu按钮旋转动画
    public void rotateMenu(View view,float start,float end,int duration) {
        RotateAnimation anim = new RotateAnimation(start,end, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        view.startAnimation(anim);
    }

    //切换菜单
    public void toggleMenu(int duration) {
        int count = getChildCount();
        for(int i=0;i<count-1;i++) {
            final View childView = getChildAt(i+1);
            childView.setVisibility(View.VISIBLE);

            int height = childView.getMeasuredHeight();
            int ct = (height+ChildMargin)*(i+1);

            //平移动画
            Animation tranAnim = null;
            //open
            if(mCurrentStatus==Status.CLOSE) {
                tranAnim = new TranslateAnimation(0,0,ct,0);
                childView.setClickable(true);
                childView.setFocusable(true);
            }else {
                tranAnim = new TranslateAnimation(0,0,0,ct);
                childView.setClickable(true);
                childView.setFocusable(true);
            }
            tranAnim.setDuration(duration);
            tranAnim.setFillAfter(true);
            tranAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if(mCurrentStatus==Status.CLOSE) {
                        childView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            childView.startAnimation(tranAnim);
        }
        //切换菜单状态
        mCurrentStatus = (mCurrentStatus==Status.CLOSE?Status.OPEN:Status.CLOSE);
    }


}
