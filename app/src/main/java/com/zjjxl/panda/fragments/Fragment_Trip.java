package com.zjjxl.panda.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.uis.ShowAccessChannelActivity;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.ToastUtils;


public class Fragment_Trip extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView mTrip_tv_more;
    private Button mBtncard_savemoney;
    private TextView mTrip_cardrealmoney;

    public Fragment_Trip() {
        // Required empty public constructor
    }

    public static Fragment_Trip newInstance(String param1, String param2) {
        Fragment_Trip fragment = new Fragment_Trip();
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
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        View inflate = inflater.inflate(R.layout.fragment__trip, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        mTrip_tv_more = inflate.findViewById(R.id.trip_tv_more);
        mBtncard_savemoney = inflate.findViewById(R.id.trip_btncard_savemoney);
        mTrip_cardrealmoney = inflate.findViewById(R.id.trip_cardrealmoney);
        mTrip_tv_more.setOnClickListener(this);
        mBtncard_savemoney.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trip_btncard_savemoney:
                Intent intent = new Intent(getActivity(), ShowAccessChannelActivity.class);
                startActivity(intent);
                break;
            case R.id.trip_tv_more:

                setDialog();
                break;
            case R.id.btn_chongzhijilu:


            case R.id.btn_chegnchejilu:


            case R.id.btn_jingqujilu:


            case R.id.btn_xiaofeijilu:


            case R.id.btn_shiyongshuoming:

                break;
            case R.id.btn_kefuzhongxi:


            case R.id.btn_calcle:
                ToastUtils.showToast(getActivity(), "正在开发中");
                break;

        }
    }

    private void setDialog() {
        Dialog mCameraDialog = new Dialog(getActivity(), R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.trip_trip_time, null);
        //初始化视图
        root.findViewById(R.id.btn_chongzhijilu).setOnClickListener(this);
        root.findViewById(R.id.btn_chegnchejilu).setOnClickListener(this);
        root.findViewById(R.id.btn_jingqujilu).setOnClickListener(this);
        root.findViewById(R.id.btn_xiaofeijilu).setOnClickListener(this);
        root.findViewById(R.id.btn_shiyongshuoming).setOnClickListener(this);
        root.findViewById(R.id.btn_kefuzhongxi).setOnClickListener(this);
        root.findViewById(R.id.btn_calcle).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        String cardmoney = ShareUtil.getString(Contants.CARD_REAL_MONEY);
        if (cardmoney != null) {
            mTrip_cardrealmoney.setText(cardmoney);
        }
    }
}
