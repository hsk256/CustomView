package com.creativeboy.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creativeboy.customview.R;
import com.creativeboy.customview.view.FloatActionMenu;


public class FloatActionMenuFragment extends Fragment {

    public static final int INDEX = 2;
    private FloatActionMenu floatActionMenu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_float_action_menu, container, false);
        floatActionMenu = (FloatActionMenu) view.findViewById(R.id.float_menu);
        floatActionMenu.setOnMenuItemClickListener(new FloatActionMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(getActivity(),pos+"--",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }



}
