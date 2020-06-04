package com.zjjxl.panda.uis;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.zjjxl.panda.utils.ShareUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();

        initFragmentDate();
        initGroup();

//        getSessionID();
    }

    private void getSessionID() {

        Call<SessioIdBean> sessionId = HttpManager.getInstance().getHttpClient3().getSessionId();
        sessionId.enqueue(new Callback<SessioIdBean>() {
            @Override
            public void onResponse(Call<SessioIdBean> call, Response<SessioIdBean> response) {
                if (response.body() != null) {
                    String sessionId1 = response.body().getSessionId();
                    ShareUtil.putString(Contants.APP_SESSIONIS, sessionId1);
                }
            }

            @Override
            public void onFailure(Call<SessioIdBean> call, Throwable t) {

            }
        });
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
