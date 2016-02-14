package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.creativeboy.customview.R;
import com.creativeboy.customview.view.ScrollerLayout;

/**
 * Created by heshaokang on 2016/2/14.
 */
public class ScrollerFragment extends Fragment{
    Button scrollTo,scrollBy;
    ScrollerLayout layout;
    public static final int INDEX = 4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroller,container,false);

        layout = (ScrollerLayout) view.findViewById(R.id.layout);

        return view;
    }
}
