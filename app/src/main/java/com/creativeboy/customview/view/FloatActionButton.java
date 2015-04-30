package com.creativeboy.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.creativeboy.customview.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by heshaokang on 2015/4/27.
 */
public class FloatActionButton extends ImageButton{
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_MINI = 0;
    //定义注解
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_NORMAL,TYPE_MINI})
    public @interface FAB_TYPE {}

    private int mColorNormal;
    private int mColorPressed;
    private int mColorDisabled;
    private String mTitle;
    @DrawableRes
    private int mIcon;
    private Drawable mIconDrawable;
    private int mSize;

    private float mCircleSize;
    private float mShadowRadius;
    private float mShadowOffset;

    private int mDrawableSize;

    private boolean mStrokeVisible;

    public FloatActionButton(Context context) {
        super(context);
    }

    public FloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    public FloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
    }


    /**
     * 初始化属性
     */
    public void initAttributes(Context context,AttributeSet attributeSet) {
        TypedArray a = context.obtainStyledAttributes(attributeSet,R.styleable.FloatActionButton,0,0);
        if(a!=null) {
            try {
                mColorNormal = a.getColor(R.styleable.FloatActionButton_fab_colorNormal,
                        getColor(android.R.color.holo_red_dark));
                mColorPressed = a.getColor(R.styleable.FloatActionButton_fab_colorPressed,
                        getColor(android.R.color.holo_blue_light));
                mColorDisabled = a.getColor(R.styleable.FloatActionButton_fab_colorDisabled,
                        getColor(android.R.color.darker_gray));

                mSize = a.getInt(R.styleable.FloatActionButton_fab_size,
                        TYPE_NORMAL);
                mIcon = a.getResourceId(R.styleable.FloatActionButton_fab_icon,0);
                mTitle = a.getString(R.styleable.FloatActionButton_fab_title);
                mStrokeVisible = a.getBoolean(R.styleable.FloatActionButton_fab_stroke_visible,true);
            }finally {
                a.recycle();
            }
        }
    }

    public void init() {

    }

    /**
     * 设置背景的兼容方法 当版本大于4.1时采用setBackground 低于采用SetBackgroundDrawable
     * @param drawable
     */
    public void setBackgroundCompat(Drawable drawable) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        }else {
            setBackgroundDrawable(drawable);
        }
    }

    public void updateBackground() {


    }

    /**
     *  创建圆形图片
     * @param color
     * @param strokeWidth
     * @return
     */
//    public Drawable createCircleDrawable(int color,float strokeWidth) {
//        //透明度
//        int alpha = Color.alpha(color);
//        //不透明度
//        int opaqueColor = getOpaque(color);
//        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//
//        Paint paint = shapeDrawable.getPaint();
//        paint.setColor(opaqueColor);
//        Drawable[] layers = {
//                shapeDrawable,
//
//        };
//
//    }

    /**
     * 得到一个整型颜色值
     * @param argb
     * @return
     */
    private int getOpaque(int argb) {
        return Color.rgb(Color.red(argb),Color.green(argb),Color.blue(argb));
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private int getColor(int id) {
        return getResources().getColor(id);
    }
}
