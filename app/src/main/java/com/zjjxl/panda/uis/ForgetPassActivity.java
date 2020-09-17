package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.NestedScrollingParent;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.BindCardIsBean;
import com.zjjxl.panda.beans.QueryUserInfo;
import com.zjjxl.panda.beans.SmsCode;
import com.zjjxl.panda.https.ClientRestAPI;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.interceptor.AddCookiesInterceptor;
import com.zjjxl.panda.interceptor.ReceivedCookiesInterceptor;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.TimerUtils;
import com.zjjxl.panda.utils.ToastUtils;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetPassActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private TextView mForget_text;
    private TextView mForget_back;
    private TextView mReset_login_phonenum;
    private TextView mReset_login_password;
    private EditText mChange_login_phonenum;
    private EditText mChange_login_password;
    private EditText mCange_login_newpassword;
    private Button mFor_change_logindenglu;
    private int mForgetPassActivities;
    private LinearLayout mReset_username_layout;
    private LinearLayout mChange_username_layout;
    private EditText mForget_phonenum;
    private EditText mForget_wordyanzhengmg;
    private Button mForget_yanzhengma;
    private LinearLayout mForget_username_layout;
    private String TAG = "ForgetPassActivity";

    private Retrofit mRetrofitsend;
    private ClientRestAPI mClientRestAPIsend;
    private Retrofit mRetrofitVer;
    private ClientRestAPI mClientRestAPIVer;
    private QueryUserInfo.UserInfoBean mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarLightMode(getWindow());
        StatusBarUtil.setDrawable(this, R.drawable.fragment_bennefit_boxframe);
        setContentView(R.layout.activity_forget_pass);
        mForgetPassActivities = getIntent().getIntExtra("ForgetPassActivity", 0);
        initSend();
        initVer();
        //改变密码 -1
        //忘记密码 -2
        initView();

    }

    //待会写
    private void initVer() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .build();

        mRetrofitVer = new Retrofit.Builder()
                .baseUrl("https://panda.stone3a.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mClientRestAPIVer = mRetrofitVer.create(ClientRestAPI.class);


    }

    private void initSend() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookiesInterceptor())
                .build();

        mRetrofitsend = new Retrofit.Builder()
                .baseUrl("https://panda.stone3a.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mClientRestAPIsend = mRetrofitsend.create(ClientRestAPI.class);
    }

    private void initView() {
        mForget_text = findViewById(R.id.forget_text);
        mForget_back = findViewById(R.id.forget_back);
        mFor_change_logindenglu = findViewById(R.id.for_change_logindenglu);
        mForget_back.setOnClickListener(this);
        mFor_change_logindenglu.setOnClickListener(this);


        mChange_username_layout = findViewById(R.id.change_username_layout);
        mForget_username_layout = findViewById(R.id.forget_username_layout);
        mReset_username_layout = findViewById(R.id.reset_username_layout);

        if (mForgetPassActivities == 1) {
            mForget_text.setText("修改密码");
            mFor_change_logindenglu.setText("确认修改");
            mChange_username_layout.setVisibility(View.VISIBLE);
            mReset_username_layout.setVisibility(View.GONE);
        } else if (mForgetPassActivities == 2) {
            mForget_text.setText("重置密码");
            mFor_change_logindenglu.setText("确认验证码");
            mForget_username_layout.setVisibility(View.VISIBLE);
            mChange_username_layout.setVisibility(View.GONE);
        }
        initforget();
        initchangeNewPass();
    }

    private void initchangeNewPass() {
        mChange_login_phonenum = findViewById(R.id.change_login_phonenum);
        mChange_login_password = findViewById(R.id.change_login_password);
        mCange_login_newpassword = findViewById(R.id.change_login_newpassword);


    }

    private void initforget() {
        mForget_phonenum = findViewById(R.id.forget_phonenum);
        mForget_wordyanzhengmg = findViewById(R.id.forget_wordyanzhengmg);
        mForget_yanzhengma = findViewById(R.id.forget_yanzhengma);
        TimerUtils.initTimer(this, mForget_yanzhengma, 60000, 1000);
        mReset_login_phonenum = findViewById(R.id.reset_login_phonenum);
        mReset_login_password = findViewById(R.id.reset_login_password);
        mForget_yanzhengma.setOnClickListener(this);
        mForget_phonenum.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.for_change_logindenglu:
                if (mForgetPassActivities == 1) {
                    changePassw(); //修改密码
                } else if (mForgetPassActivities == 2) {
                    if (mForget_wordyanzhengmg.getText().toString().trim().length() == 6) {
                        vaLidateSmsCode(mForget_wordyanzhengmg.getText().toString().trim());
                    }
                } else if (mForgetPassActivities == 3) {
                    resetPassord();
                }
                break;
            case R.id.forget_back:
                finish();
                break;
            case R.id.forget_yanzhengma:
                if (mForget_phonenum.getText().toString().trim().startsWith("1")
                        && mForget_phonenum.getText().toString().trim().length() == 11) {
                    TimerUtils.TimerStart();
                    getSmsCode(mForget_phonenum.getText().toString().trim()); //  发送验证码
                } else {
                    ToastUtils.showToast(this, "请检查手机号码是否正确");
                }
                break;
        }
    }

    private void resetPassord() {
        String phone = mReset_login_phonenum.getText().toString().trim();
        String newpass = mReset_login_password.getText().toString().trim();

        Call<BindCardIsBean> bindCardIsBeanCall = HttpManager.getInstance().getHttpClient3()
                .resetUserPassword(mUserInfo.getUserMemberId(), newpass);
        bindCardIsBeanCall.enqueue(new Callback<BindCardIsBean>() {
            @Override
            public void onResponse(Call<BindCardIsBean> call, Response<BindCardIsBean> response) {
                if (response.body() != null) {
                    if (response.body().isStatus()) {
                        ShareUtil.putString(Contants.LOGIN_USER_PHONE, mUserInfo.getMobile());
                        ShareUtil.putString(Contants.LOGIN_USER_NAME, mUserInfo.getUserName());
                        ShareUtil.putString(Contants.LOGIN_USER_HEAD, mUserInfo.getPhoto());
                        ShareUtil.putString(Contants.LOGIN_ORGMEMBERID, mUserInfo.getOrgMemberId());
                        ShareUtil.putString(Contants.LOGIN_USERMEMBERID, mUserInfo.getUserMemberId());
                        finish();
                        ToastUtils.showToast(ForgetPassActivity.this, response.body().getMsg());
                    } else {
                        ToastUtils.showToast(ForgetPassActivity.this, response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<BindCardIsBean> call, Throwable t) {

            }
        });
    }

    private void vaLidateSmsCode(String trim) {
        mClientRestAPIVer.validate(trim).enqueue(new Callback<SmsCode>() {
            @Override
            public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {
                if (response.body() != null) {
                    boolean data = response.body().isData();
                    if (data) {
                        LUtils.d(TAG, "data==" + data + "== response.body()=" + response.body().getResult());
                        mReset_username_layout.setVisibility(View.VISIBLE);
                        mForget_username_layout.setVisibility(View.GONE);

                        mFor_change_logindenglu.setText("确认修改");
                        mForgetPassActivities = 3;
                        mReset_login_phonenum.setText(mForget_phonenum.getText().toString().trim());
                    } else {
                        ToastUtils.showToast(ForgetPassActivity.this, "验证码错误");
                    }
                }

            }

            @Override
            public void onFailure(Call<SmsCode> call, Throwable t) {

            }
        });

    }

    private void getSmsCode(String mPhoneNum) {
        LUtils.d(TAG, "mPhoneNum==" + mPhoneNum);
        mClientRestAPIsend.sendVerificationCode(mPhoneNum).enqueue(new Callback<SmsCode>() {
            @Override
            public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {
                if (response.body() != null) {
                    String data = response.body().getResult();
                    LUtils.d(TAG, "onResponse== data==" + data);

                }
            }

            @Override
            public void onFailure(Call<SmsCode> call, Throwable t) {
                LUtils.d(TAG, "onFailure==" + t);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        TimerUtils.TimerStop("发送验证码");
    }

    private void changePassw() {

        if (ShareUtil.getString(Contants.LOGIN_USERMEMBERID) != null) {
            Call<BindCardIsBean> bindCardIsBeanCall = HttpManager.getInstance().getHttpClient3()
                    .updateUserPassword(ShareUtil.getString(Contants.LOGIN_USERMEMBERID),
                            mChange_login_password.getText().toString().trim(),
                            mCange_login_newpassword.getText().toString().trim());
            bindCardIsBeanCall.enqueue(new Callback<BindCardIsBean>() {
                @Override
                public void onResponse(Call<BindCardIsBean> call, Response<BindCardIsBean> response) {
                    if (response.body() != null) {
                        boolean status = response.body().isStatus();
                        if (status) {
                            ToastUtils.showToast(ForgetPassActivity.this, response.body().getMsg() + "");
                            Intent intent = new Intent(ForgetPassActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            ToastUtils.showToast(ForgetPassActivity.this, response.body().getMsg() + "");
                        }
                    }

                }

                @Override
                public void onFailure(Call<BindCardIsBean> call, Throwable t) {

                }
            });

        } else {
            ToastUtils.showToast(this, "请登录后再试");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 11) {
            queryUserInfoByUserMobile(s.toString());
        }
    }


    public void queryUserInfoByUserMobile(String phone) {
        Call<QueryUserInfo> bindCardIsBeanCall =
                HttpManager.getInstance().getHttpClient3().queryUserInfoByUserMobile(phone);
        bindCardIsBeanCall.enqueue(new Callback<QueryUserInfo>() {
            @Override
            public void onResponse(Call<QueryUserInfo> call, Response<QueryUserInfo> response) {
                if (response.body() != null) {
                    boolean status = response.body().isStatus();
                    if (status) {
                        mUserInfo = response.body().getUserInfo();

                    } else {
                        Intent in = new Intent(ForgetPassActivity.this, RegistActivity.class);
                        startActivity(in);
                    }

                }
            }

            @Override
            public void onFailure(Call<QueryUserInfo> call, Throwable t) {

            }
        });

    }
}
