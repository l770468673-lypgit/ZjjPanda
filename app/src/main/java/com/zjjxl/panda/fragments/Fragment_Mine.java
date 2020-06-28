package com.zjjxl.panda.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjjxl.panda.R;
import com.zjjxl.panda.uis.LoginActivity;
import com.zjjxl.panda.uis.SettingActivity;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.RatioImageView;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;

public class Fragment_Mine extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout mRely_mine_tologin;
    private RatioImageView mMine_minehead;
    private TextView mMin_login_nunm;
    private TextView mMin_login_name;
    private Button mFragment_mine_kabao;

    public Fragment_Mine() {
    }

    public static Fragment_Mine newInstance(String param1, String param2) {
        Fragment_Mine fragment = new Fragment_Mine();
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
//
        View inflate = inflater.inflate(R.layout.fragment__mine, container, false);

        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mRely_mine_tologin = inflate.findViewById(R.id.rely_mine_tologin);
        mMine_minehead = inflate.findViewById(R.id.mine_minehead);
        mMin_login_nunm = inflate.findViewById(R.id.min_login_nunm);
        mMin_login_name = inflate.findViewById(R.id.min_login_name);
        mFragment_mine_kabao = inflate.findViewById(R.id.fragment_mine_kabao);




        mMine_minehead.setOnClickListener(this);
        mRely_mine_tologin.setOnClickListener(this);
        mFragment_mine_kabao.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String stringph = ShareUtil.getString(Contants.LOGIN_USER_PHONE);
        if (stringph != null) {
            mMin_login_nunm.setText(stringph);
        }
        String sname = ShareUtil.getString(Contants.LOGIN_USER_NAME);
        if (sname != null) {
            mMin_login_name.setText(sname);
        }
        String snamehead = ShareUtil.getString(Contants.LOGIN_USER_HEAD);
        if (snamehead != null) {
            Glide.with(getActivity()).load(snamehead.toString())
                    .placeholder(R.mipmap.mine_headpic)
                    .dontAnimate().skipMemoryCache(true).
                    diskCacheStrategy(DiskCacheStrategy.NONE).
                    into(mMine_minehead);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rely_mine_tologin:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_minehead:
                Intent intents = new Intent(getActivity(), SettingActivity.class);
                startActivity(intents);
                break;
                case R.id.fragment_mine_kabao:

                break;
        }
    }
}
