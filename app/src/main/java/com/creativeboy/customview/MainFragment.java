package com.creativeboy.customview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.creativeboy.customview.fragment.CircleActionMenuFragment;
import com.creativeboy.customview.fragment.ColorOptionsFragment;
import com.creativeboy.customview.fragment.CustomRingWaveFragment;
import com.creativeboy.customview.fragment.FloatActionMenuFragment;
import com.creativeboy.customview.utils.Constants;


/**
 * Created by heshaokang on 2015/4/30.
 */
public class MainFragment extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int fragmentIndex = getIntent().getIntExtra(Constants.FRAGMENT_INDEX,0);
        Fragment fr;
        String tag;
        String title;
        switch (fragmentIndex) {
            default:
            case CustomRingWaveFragment.INDEX:
                tag = CustomRingWaveFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if(fr==null) {
                    fr = new CustomRingWaveFragment();

                }
                title = "CustomRingWaveFragment";
                break;
            case ColorOptionsFragment.INDEX:
                tag = ColorOptionsFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if(fr==null) {
                    fr = new ColorOptionsFragment();
                }
                title = "ColorOptionsFragment";
                break;
            case FloatActionMenuFragment.INDEX:
                tag = FloatActionMenuFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if(fr==null) {
                    fr = new FloatActionMenuFragment();
                }
                title = "FloatActionMenuFragment";
                break;
            case CircleActionMenuFragment.INDEX:
                tag = CircleActionMenuFragment.class.getSimpleName();
                fr = getSupportFragmentManager().findFragmentByTag(tag);
                if(fr==null) {
                    fr = new CircleActionMenuFragment();
                }
                title = "CircleActionMenuFragment";
                break;

        }
        setTitle(title);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,fr,tag).commit();

    }
}
