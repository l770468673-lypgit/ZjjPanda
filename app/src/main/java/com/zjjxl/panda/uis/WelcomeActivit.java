package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.AppStatus;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.ShareUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivit extends AppCompatActivity implements View.OnClickListener {

    private ImageView mWelcomeimg;
    private WelcomeHandler mHandler;
    public final Runnable toSplashActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivit.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    private TextView mWelcome_yinsi1;
    private TextView mWelcome_yinsi2;
    private TextView mYinsi_back;
    private TextView mYinsi_ok;
    private LinearLayout mYinsi_lly;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.welcome_yinsi1:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("lodaweb",1);
                startActivity(intent);
                break;
            case R.id.welcome_yinsi2:
                Intent intent2 = new Intent(this, WebActivity.class);
                intent2.putExtra("lodaweb",2);
                startActivity(intent2);
                break;
            case R.id.yinsi_back:
                finish();
                break;
            case R.id.yinsi_ok:
                mHandler.postDelayed(toSplashActivity, 0);
                ShareUtil.putString(Contants.APP_STAUTS_PERMISSION, "app_stauts_permission");
                break;

        }
    }

    class WelcomeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initVIEW();
        mHandler = new WelcomeHandler();
        mWelcomeimg = findViewById(R.id.welcomeimg);
        AppStatus.APP_STATUS = AppStatus.APP_STATUS_NORMAL; // App正常的启动，设置App的启动状态为正常启动
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        String string = ShareUtil.getString(Contants.APP_STAUTS_PERMISSION);
        if (string != null) {
            mYinsi_lly.setVisibility(View.GONE);
            mHandler.postDelayed(toSplashActivity, 3000);
        }else {
            mYinsi_lly.setVisibility(View.VISIBLE);
        }
    }

    private void initVIEW() {
        mWelcome_yinsi1 = findViewById(R.id.welcome_yinsi1);
        mWelcome_yinsi2 = findViewById(R.id.welcome_yinsi2);
        mYinsi_back = findViewById(R.id.yinsi_back);
        mYinsi_ok = findViewById(R.id.yinsi_ok);
        mYinsi_lly = findViewById(R.id.yinsi_lly);

        mWelcome_yinsi1.setOnClickListener(this);
        mWelcome_yinsi2.setOnClickListener(this);
        mYinsi_back.setOnClickListener(this);
        mYinsi_ok.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
