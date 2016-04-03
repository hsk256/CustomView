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
public class ViewDragHelperFragment extends Fragment{

    public static final int INDEX = 6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewdrag,container,false);
        return view;
    }
}
