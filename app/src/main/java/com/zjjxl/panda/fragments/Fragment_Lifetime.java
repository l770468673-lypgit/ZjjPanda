package com.zjjxl.panda.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.StatusBarUtil;

public class Fragment_Lifetime extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Lifetime() {
        // Required empty public constructor
    }

    public static Fragment_Lifetime newInstance(String param1, String param2) {
        Fragment_Lifetime fragment = new Fragment_Lifetime();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }  StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        return inflater.inflate(R.layout.fragment__lifetime, container, false);
    }
}
