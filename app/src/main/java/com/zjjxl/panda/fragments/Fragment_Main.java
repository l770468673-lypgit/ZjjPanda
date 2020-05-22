package com.zjjxl.panda.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.uis.NFCPandaActivity;


public class Fragment_Main extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private Button mBtncard_savemoney;

    public Fragment_Main() {
        // Required empty public constructor
    }


    public static Fragment_Main newInstance(String param1, String param2) {
        Fragment_Main fragment = new Fragment_Main();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment__main, container, false);

        initView(inflate);


        return inflate;
    }

    private void initView(View inflate) {


        mBtncard_savemoney = inflate.findViewById(R.id.btncard_savemoney);

        mBtncard_savemoney.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btncard_savemoney:
                Intent intent = new Intent(getActivity(), NFCPandaActivity.class);
                startActivity(intent);
                break;

        }
    }

//    public static Bitmap capture(Activity activity) {
//        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
//        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
//        return bmp;
//    }
}
