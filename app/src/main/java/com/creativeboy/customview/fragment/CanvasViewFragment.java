package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeboy.customview.R;

/**
 * Created by heshaokang on 2016/2/14.
 */
public class CanvasViewFragment extends Fragment{

    public static final int INDEX = 7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canvas_view,container,false);
        return view;
    }
}
