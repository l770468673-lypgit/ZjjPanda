package com.zjjxl.panda.uis;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.zjjxl.panda.R;
import com.zjjxl.panda.apps.XLBaseActivity;
import com.zjjxl.panda.beans.SessioIdBean;
import com.zjjxl.panda.beans.SmsCode;
import com.zjjxl.panda.fragments.Fragment_Benefit;
import com.zjjxl.panda.fragments.Fragment_Lifetime;
import com.zjjxl.panda.fragments.Fragment_Main;
import com.zjjxl.panda.fragments.Fragment_Mine;
import com.zjjxl.panda.fragments.Fragment_Trip;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends XLBaseActivity implements View.OnTouchListener {
    private String TAG = "MainActivity";
    private RelativeLayout mMain_container;
    private Fragment[] mFragmentslist;

    private int mIndex;
    private RadioGroup mMain_rgroup;
    private RadioButton mMain_frag_home;
    private RadioButton mMain_frag_benefit;
    private RadioButton mMain_frag_trip;
    private RadioButton mMain_frag_life;
    private RadioButton mMain_frag_mine;
    private Button mBtntobus;
    private boolean istraipclick = false;

    AlertDialog mPermissionDialog;
    private int PERMISSION_CODE = 1000;
    List<String> mPermissionList = new ArrayList<>();
    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            //            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initPermission();
        initView();

        initFragmentDate();
        initGroup();

//        getSessionID();
    }

    private void initPermission() {
        mPermissionList.clear();
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_CODE);
        } else {
            //权限已经都通过了，可以将程序继续打开了
//            initFid();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean hasPermissionDismiss = false;//有权限没有通过
        if (PERMISSION_CODE == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    LUtils.d(TAG, "grantResults[i]==" + i);
                    hasPermissionDismiss = true;
                    break;
                }
            }
        }
        if (hasPermissionDismiss) {//如果有没有被允许的权限
            showPermissionDialog();

        } else {
            //权限已经都通过了，可以将程序继续打开了
//            initFid();
        }


    }

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + "com.estone.bank.estone_appsmartlock");
                            Intent intent = new Intent(Settings.
                                    ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            MainActivity.this.finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();

    }
//    private void getSessionID() {
//
//        Call<SessioIdBean> sessionId = HttpManager.getInstance().getHttpClient3().getSessionId();
//        sessionId.enqueue(new Callback<SessioIdBean>() {
//            @Override
//            public void onResponse(Call<SessioIdBean> call, Response<SessioIdBean> response) {
//                if (response.body() != null) {
//                    String sessionId1 = response.body().getSessionId();
//                    ShareUtil.putString(Contants.APP_SESSIONIS, sessionId1);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SessioIdBean> call, Throwable t) {
//
//            }
//        });
//    }

    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    private void initGroup() {

        mMain_rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.main_frag_home:
                        setIndexSelected(0);


                        break;
                    case R.id.main_frag_benefit:
                        setIndexSelected(1);
                        break;

                    case R.id.main_frag_trip:
                        setIndexSelected(2);
                        break;

                    case R.id.main_frag_life:
                        setIndexSelected(3);
                        break;
                    case R.id.main_frag_mine:
                        setIndexSelected(4);
                        break;
                    default:
                        break;
                }


            }
        });
    }

    private void initFragmentDate() {
        Fragment_Main main = Fragment_Main.newInstance("", "");
        Fragment_Benefit f_Find = Fragment_Benefit.newInstance("", "");
        Fragment_Trip f_FindZone = Fragment_Trip.newInstance("", "");
        Fragment_Lifetime f_mine = Fragment_Lifetime.newInstance("", "");
        Fragment_Mine travel = Fragment_Mine.newInstance("", "");

        mFragmentslist = new Fragment[]{main, f_Find, f_FindZone, f_mine, travel};
        //开启事务
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //添加首页
        ft.add(R.id.main_container, main).commit();
        //默认设置为第0个
        setIndexSelected(0);
        mMain_rgroup.getChildAt(0).performClick();//模拟点击第一个RB
    }


    private void initView() {

        mMain_container = findViewById(R.id.main_container);
        mMain_rgroup = findViewById(R.id.main_rgroup);
        mBtntobus = findViewById(R.id.btntobus);
        mMain_rgroup.setOnTouchListener(this);

        if (istraipclick) {
            mBtntobus.setTextColor(getResources().getColor(R.color.b3886FB));
        } else {
            mBtntobus.setTextColor(getResources().getColor(R.color.b8C8C8C));
        }
        mMain_frag_home = findViewById(R.id.main_frag_home);
        mMain_frag_benefit = findViewById(R.id.main_frag_benefit);
        mMain_frag_trip = findViewById(R.id.main_frag_trip);
        mMain_frag_life = findViewById(R.id.main_frag_life);
        mMain_frag_mine = findViewById(R.id.main_frag_mine);
        mBtntobus.setClickable(false);

    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        if (index == 2) {
            mBtntobus.setTextColor(getResources().getColor(R.color.b3886FB));
        } else {
            mBtntobus.setTextColor(getResources().getColor(R.color.b8C8C8C));
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        //隐藏
        ft.hide(mFragmentslist[mIndex]);

        //判断是否添加
        if (!mFragmentslist[index].isAdded()) {
            ft.add(R.id.main_container, mFragmentslist[index]).show(mFragmentslist[index]);
        } else {
            ft.show(mFragmentslist[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    //    @Override
    //    public void onClick(View v) {
    //        switch (v.getId()) {
    //            case R.id.btntobus:
    ////                setIndexSelected(2);
    //
    //                break;
    //
    //        }
    //    }
}
