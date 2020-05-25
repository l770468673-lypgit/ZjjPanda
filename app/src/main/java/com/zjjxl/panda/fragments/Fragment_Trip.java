package com.zjjxl.panda.fragments;

import android.app.Dialog;
import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.ToastUtils;


public class Fragment_Trip extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView mTrip_tv_more;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment__trip, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        mTrip_tv_more = inflate.findViewById(R.id.trip_tv_more);
        mTrip_tv_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trip_tv_more:

                setDialog();
                break;
            case R.id.btn_chongzhijilu:
                ToastUtils.showToast(getActivity(), "btn_chongzhijilu");
                break;
            case R.id.btn_chegnchejilu:
                ToastUtils.showToast(getActivity(), "btn_chegnchejilu");
                break;
            case R.id.btn_jingqujilu:
                ToastUtils.showToast(getActivity(), "btn_jingqujilu");
                break;
            case R.id.btn_xiaofeijilu:
                ToastUtils.showToast(getActivity(), "btn_xiaofeijilu");
                break;
            case R.id.btn_shiyongshuoming:
                ToastUtils.showToast(getActivity(), "btn_shiyongshuoming");
                break;
            case R.id.btn_kefuzhongxi:
                ToastUtils.showToast(getActivity(), "btn_kefuzhongxi");
                break;
            case R.id.btn_calcle:
                ToastUtils.showToast(getActivity(), "btn_calcle");
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

}
