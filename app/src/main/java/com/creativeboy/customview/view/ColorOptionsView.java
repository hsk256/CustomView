package com.creativeboy.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativeboy.customview.R;

/**
 * Created by heshaokang on 2015/4/27.
 */
public class ColorOptionsView extends LinearLayout{
    private View mValue;
    private ImageView mImage;
    public ColorOptionsView(Context context) {
        super(context);
    }

    public ColorOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.Options,0,0);
        int count = attrs.getAttributeCount();
       for(int i=0;i<count;i++) {
           String name = attrs.getAttributeName(i);
           String value = attrs.getAttributeValue(i);
           System.out.println(name+"---"+value);
       }

        String titleText = a.getString(R.styleable.Options_titleText);
        int valueColor = a.getInt(R.styleable.Options_valueColor,android.R.color.holo_blue_bright);

        a.recycle();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_color_options,this,true);

        TextView title = (TextView) getChildAt(0);
        title.setText(titleText);

        mValue = getChildAt(1);
        mValue.setBackgroundColor(valueColor);
        mImage = (ImageView) getChildAt(2);

    }

    public ColorOptionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageVisible(boolean visible) {
        mImage.setVisibility(visible?View.VISIBLE:View.GONE);
    }
    public void setmValueColor(int color) {
        mValue.setBackgroundColor(color);
    }

}
