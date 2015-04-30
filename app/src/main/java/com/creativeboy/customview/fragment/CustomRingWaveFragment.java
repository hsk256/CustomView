package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeboy.customview.R;

/**
 * Created by heshaokang on 2015/4/30.
 */
public class CustomRingWaveFragment extends Fragment{
    public static final int INDEX = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ring_wave,container,false);
        return view;

    }
}
