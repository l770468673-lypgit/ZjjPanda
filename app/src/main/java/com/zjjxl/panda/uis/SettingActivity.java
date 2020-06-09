package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.RatioImageView;
import com.zjjxl.panda.utils.ShareUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mLife_toptitle;
    private RatioImageView mSetting_heanimg;
    private TextView mSetting_name;
    private TextView mSetting_phone;
    private TextView mSetting_private;
    private TextView mSetting_server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        mLife_toptitle = findViewById(R.id.life_toptitle);
        mSetting_heanimg = findViewById(R.id.setting_heanimg);
        mSetting_name = findViewById(R.id.setting_name);
        mSetting_phone = findViewById(R.id.setting_phone);
        mSetting_private = findViewById(R.id.setting_private);
        mSetting_server = findViewById(R.id.setting_server);

        mLife_toptitle.setOnClickListener(this);
        mSetting_server.setOnClickListener(this);
        mSetting_private.setOnClickListener(this);


        String stringph = ShareUtil.getString(Contants.LOGIN_USER_PHONE);
        if (stringph != null) {
            mSetting_phone.setText(stringph);
        }
        String sname = ShareUtil.getString(Contants.LOGIN_USER_NAME);
        if (sname != null) {
            mSetting_name.setText(sname);
        }

        String snamehead = ShareUtil.getString(Contants.LOGIN_USER_HEAD);
        if (snamehead != null) {
            Glide.with(this).load(snamehead.toString())
                    .placeholder(R.mipmap.mine_headpic)
                    .dontAnimate().skipMemoryCache(true).
                    diskCacheStrategy(DiskCacheStrategy.NONE).
                    into(mSetting_heanimg);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.life_toptitle:
                finish();
                break;
            case R.id.setting_private:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("lodaweb",1);
                startActivity(intent);
                break;
            case R.id.setting_server:
                Intent intent2 = new Intent(this, WebActivity.class);
                intent2.putExtra("lodaweb",2);
                startActivity(intent2);
                break;
        }
    }
}
