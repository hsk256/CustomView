package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeboy.customview.R;


public class FloatActionMenuFragment extends Fragment {

    public static final int INDEX = 2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_float_action_menu, container, false);
    }



}
