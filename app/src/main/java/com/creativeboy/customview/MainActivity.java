package com.creativeboy.customview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.creativeboy.customview.fragment.CanvasViewFragment;
import com.creativeboy.customview.fragment.CircleActionMenuFragment;
import com.creativeboy.customview.fragment.ColorOptionsFragment;
import com.creativeboy.customview.fragment.CustomImgContainerFragment;
import com.creativeboy.customview.fragment.CustomRingWaveFragment;
import com.creativeboy.customview.fragment.FloatActionMenuFragment;
import com.creativeboy.customview.fragment.ScrollerFragment;
import com.creativeboy.customview.fragment.ViewDragHelperFragment;
import com.creativeboy.customview.utils.Constants;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init() {
        findViewById(R.id.ring_wave).setOnClickListener(this);
        findViewById(R.id.color_options).setOnClickListener(this);
        findViewById(R.id.float_menu).setOnClickListener(this);
        findViewById(R.id.circle_menu).setOnClickListener(this);
        findViewById(R.id.scroller_viewpager).setOnClickListener(this);
        findViewById(R.id.viewGroup1).setOnClickListener(this);
        findViewById(R.id.viewdrag).setOnClickListener(this);
        findViewById(R.id.canvas).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,MainFragment.class);
        switch (v.getId()) {
            case R.id.ring_wave:
                intent.putExtra(Constants.FRAGMENT_INDEX, CustomRingWaveFragment.INDEX);
                break;
            case R.id.color_options:
                intent.putExtra(Constants.FRAGMENT_INDEX, ColorOptionsFragment.INDEX);
                break;
            case R.id.float_menu:
                intent.putExtra(Constants.FRAGMENT_INDEX, FloatActionMenuFragment.INDEX);
                break;
            case R.id.circle_menu:
                intent.putExtra(Constants.FRAGMENT_INDEX, CircleActionMenuFragment.INDEX);
                break;
            case R.id.scroller_viewpager:
                intent.putExtra(Constants.FRAGMENT_INDEX, ScrollerFragment.INDEX);
                break;
            case R.id.viewGroup1:
                intent.putExtra(Constants.FRAGMENT_INDEX, CustomImgContainerFragment.INDEX);
                break;
            case R.id.viewdrag:
                intent.putExtra(Constants.FRAGMENT_INDEX, ViewDragHelperFragment.INDEX);
                break;
            case R.id.canvas:
                intent.putExtra(Constants.FRAGMENT_INDEX, CanvasViewFragment.INDEX);
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
