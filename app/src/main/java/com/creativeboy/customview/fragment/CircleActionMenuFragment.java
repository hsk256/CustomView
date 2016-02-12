package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creativeboy.customview.R;
import com.creativeboy.customview.view.CircleActionMenu;

/**
 * Created by heshaokang on 2015/5/4.
 */
public class CircleActionMenuFragment extends Fragment{
    public static final int INDEX = 3;
    private CircleActionMenu circleActionMenu;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.circle_menu,container,false);

        circleActionMenu = (CircleActionMenu) view.findViewById(R.id.circle_menu);
        circleActionMenu.setOnMenuItemClickListener(new CircleActionMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(getActivity(),pos+":"+view.getTag(),Toast.LENGTH_SHORT);
            }
        });
        return view;
    }
}
