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

import java.util.ArrayList;

/**
 * Created by heshaokang on 2015/4/30.
 */
public class CustomRingWave extends View {

    private static final int DIS_SLOP=13;
    private boolean isRunning = false;
    private ArrayList<Wave> waveList;
    private int[] colors = new int[]{Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW,Color.GRAY};
    public CustomRingWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        waveList = new ArrayList<Wave>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<waveList.size();i++) {
            Wave wave = waveList.get(i);
            canvas.drawCircle(wave.cx,wave.cy,wave.r,wave.paint);
        }
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flushState();
            invalidate();
            if(isRunning) {
                sendEmptyMessageDelayed(0,50);
            }
        }
    };
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                addPoint(x,y);
                break;
        }
       return true;
    }

    //添加新的波浪的中心点
    private void addPoint(int x, int y) {
        if(waveList.size()==0) {
            addPointToList(x, y);
            isRunning = true;
            handler.sendEmptyMessage(0);
        }else {
            //得到上一个波浪的对象
            Wave w = waveList.get(waveList.size()-1);
            if(Math.abs(w.cx-x)>DIS_SLOP || Math.abs(w.cy-y)>DIS_SLOP) {
                addPointToList(x,y);
            }
        }
    }

    //添加新的波浪
    private void addPointToList(int x, int y) {
        Wave w = new Wave();
        w.cx = x;
        w.cy = y;
        Paint paint = new Paint();
        paint.setColor(colors[((int) (Math.random() * 5))]);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        w.paint = paint;
        waveList.add(w);

    }

    public void init() {

    }
    private void flushState() {
        for(int i=0;i<waveList.size();i++) {
            Wave w = waveList.get(i);
            int alpha = w.paint.getAlpha();
            if(alpha==0) {
                waveList.remove(w);
            }
            //透明度降低
            alpha-=5;
            if(alpha<5) {
                alpha = 0;
            }
            w.paint.setAlpha(alpha);
            w.r = w.r+3;
            w.paint.setStrokeWidth(w.r/3);


        }
        //集合为空时 停止刷新动画
        if(waveList.size()==0) {
            isRunning = false;
        }
    }

    //波浪对象
    class Wave {
        private Paint paint;
        private int cx;
        private int cy;
        private int r;
    }
}
