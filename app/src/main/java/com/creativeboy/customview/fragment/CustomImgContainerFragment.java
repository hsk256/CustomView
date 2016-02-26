package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeboy.customview.R;

/**
 * Created by heshaokang on 2016/2/26.
 */
public class CustomImgContainerFragment extends Fragment{
    public static final int INDEX = 5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_img_container,container,false);
    }
}
