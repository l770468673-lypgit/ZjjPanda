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
import com.zjjxl.panda.beans.QueryBindCradbean;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.uis.CardListActivity;
import com.zjjxl.panda.uis.LoginActivity;
import com.zjjxl.panda.uis.OpenCardActivity;
import com.zjjxl.panda.uis.SettingActivity;
import com.zjjxl.panda.uis.WelcomeActivit;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.RatioImageView;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Mine extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = "Fragment_Mine";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout mRely_mine_tologin;
    private Button mButton_cardlist;
    private RatioImageView mMine_minehead;
    private TextView mMin_login_nunm;
    private TextView mMin_login_name;
    private Button mFragment_mine_kabao;
    private String mLogin_phone;
    private TextView mLoginout;
    private Button mMine_rely_btn;
    private TextView mMine_bindcard_text;

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
        LUtils.d(TAG, "onCreateView");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        StatusBarUtil.setDrawable(getActivity(), R.drawable.fragment_bennefit_boxframe);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        StatusBarUtil.setDrawable(getActivity(), R.drawable.fragment_bennefit_boxframe);
        View inflate = inflater.inflate(R.layout.fragment__mine, container, false);
        LUtils.d(TAG, "onCreateView");
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mButton_cardlist = inflate.findViewById(R.id.mine_button_cardlist);
        mRely_mine_tologin = inflate.findViewById(R.id.rely_mine_tologin);
        mMine_minehead = inflate.findViewById(R.id.mine_minehead);
        mMin_login_nunm = inflate.findViewById(R.id.min_login_nunm);
        mMin_login_name = inflate.findViewById(R.id.min_login_name);
        mFragment_mine_kabao = inflate.findViewById(R.id.fragment_mine_kabao);
        mLoginout = inflate.findViewById(R.id.loginout);
        mMine_rely_btn = inflate.findViewById(R.id.mine_rely_btn);
        mMine_bindcard_text = inflate.findViewById(R.id.mine_bindcard_text);


        mMine_minehead.setOnClickListener(this);
        mRely_mine_tologin.setOnClickListener(this);
        mButton_cardlist.setOnClickListener(this);
        mFragment_mine_kabao.setOnClickListener(this);
        mLoginout.setOnClickListener(this);
        mMine_rely_btn.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        LUtils.d(TAG, "onStart");
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        StatusBarUtil.setDrawable(getActivity(), R.drawable.fragment_bennefit_boxframe);
    }


    @Override
    public void onResume() {
        super.onResume();
        LUtils.d(TAG, "onResume");
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        StatusBarUtil.setDrawable(getActivity(), R.drawable.fragment_bennefit_boxframe);
        mLogin_phone = ShareUtil.getString(Contants.LOGIN_USER_PHONE);
        if (mLogin_phone!=null) {
            LoadDate();
        }

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
        if (mLogin_phone != null) {
            switch (v.getId()) {
                case R.id.rely_mine_tologin:
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.mine_minehead:
                    Intent intents = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intents);
                    break;
                case R.id.loginout:
                    ShareUtil.removeAllKey();
                    getActivity().finish();
                    Intent in = new Intent(getActivity(), WelcomeActivit.class);
                    startActivity(in);
                    break;
                case R.id.mine_rely_btn:
                    Intent intentsop = new Intent(getActivity(), OpenCardActivity.class);
                    startActivity(intentsop);
                    break;
                case R.id.fragment_mine_kabao:

                    break;
                case R.id.mine_button_cardlist:
//                    Intent incard = new Intent(getActivity(), CardListActivity.class);
//                    startActivity(incard);
                    break;
            }
        } else {
            ToastUtils.showToast(getActivity(), "请您先登录账号");
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }


    private void LoadDate() {
        Call<QueryBindCradbean> queryCZCityCall = HttpManager.getInstance().getHttpClient3().
                queryUserInfoByUserMid(ShareUtil.getString(Contants.LOGIN_USERMEMBERID));
        queryCZCityCall.enqueue(new Callback<QueryBindCradbean>() {
            @Override
            public void onResponse(Call<QueryBindCradbean> call, Response<QueryBindCradbean> response) {
                if (response.body() != null) {
                    if(response.body().isStatus()){

                        QueryBindCradbean.CardInfoBean cardInfo = response.body().getCardInfo();
                        if (cardInfo != null) {
                            mMine_bindcard_text.setText(cardInfo.getCardNum());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<QueryBindCradbean> call, Throwable t) {

            }
        });
    }
}
