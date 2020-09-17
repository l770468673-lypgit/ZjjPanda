package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.BindCardIsBean;
import com.zjjxl.panda.beans.LoginBean;
import com.zjjxl.panda.beans.QueryUserInfo;
import com.zjjxl.panda.beans.SmsCode;
import com.zjjxl.panda.beans.regAppUser;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
//        , TextWatcher
{

    private String TAG = "LoginActivity";
    private TextView mLogin_back;
    private TextView mLogin_to_regist;
    private TextView mChangelogintype;
    private TextView mForgetpass;
    private EditText mLogin_phonenum;
    private EditText mLogin_password;
    private Button mLogin_logindenglu;
    private ImageView mLogin_passvisvilit;
    private RelativeLayout mLogin_rely_pass;
    private RelativeLayout mRely_olduser;
    private Button mBtn_yanzhengma;

    private Retrofit mRetrofitsend;
    private ClientRestAPI mClientRestAPIsend;
    private Retrofit mRetrofitVer;
    private ClientRestAPI mClientRestAPIVer;

    private boolean loginFlag = true;
    private EditText mLoginyanzinput_hengmg;
    private QueryUserInfo.UserInfoBean mUserInfo;

    private String logintype;
    private ImageView mPassvisvilit;
    private boolean editflagView = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDrawable(this, R.drawable.mine_fragment_color);
        StatusBarUtil.setStatusBarLightMode(getWindow());
//        StatusBarUtil.setDrawable(this, R.drawable.fragment_bennefit_boxframe);
        setContentView(R.layout.activity_login);
        initSend();
        initVer();
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
        mLogin_back = findViewById(R.id.login_back);
        mLogin_to_regist = findViewById(R.id.login_to_regist);
        mPassvisvilit = findViewById(R.id.passvisvilit);
        mLogin_phonenum = findViewById(R.id.login_phonenum);
        mLogin_password = findViewById(R.id.login_password);
        mLogin_logindenglu = findViewById(R.id.login_logindenglu);
        mLogin_passvisvilit = findViewById(R.id.login_passvisvilit);
        mChangelogintype = findViewById(R.id.changelogintype);
        mLogin_rely_pass = findViewById(R.id.login_rely_pass);
        mForgetpass = findViewById(R.id.forgetpass);
        mRely_olduser = findViewById(R.id.rely_olduser);
        mBtn_yanzhengma = findViewById(R.id.btn_yanzhengma);
        mLoginyanzinput_hengmg = findViewById(R.id.loginyanzinput_hengmg);

        logintype = "1";
        mPassvisvilit.setOnClickListener(this);
        mBtn_yanzhengma.setOnClickListener(this);
        mChangelogintype.setOnClickListener(this);
        mForgetpass.setOnClickListener(this);
        mLogin_back.setOnClickListener(this);
        mLogin_to_regist.setOnClickListener(this);
        mLogin_passvisvilit.setOnClickListener(this);
        mLogin_logindenglu.setOnClickListener(this);

//        mLogin_phonenum.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.passvisvilit:
                if (!editflagView) {
                    mLogin_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mLogin_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                editflagView = !editflagView;
                mLogin_password.postInvalidate();
                CharSequence text = mLogin_password.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
                break;
            case R.id.login_logindenglu:
                if (loginFlag) {
                    LUtils.d(TAG, "账号 ");
                    loginAppForPhone();
                } else {
                    LUtils.d(TAG, "验证码 ");
                    if (mLoginyanzinput_hengmg.getText().toString().trim().length() == 6) {
                        vaLidateSmsCode(mLoginyanzinput_hengmg.getText().toString().trim());
                    }
                }

                break;
            case R.id.login_passvisvilit:
                mLogin_phonenum.setText("");
                break;
            case R.id.btn_yanzhengma:
                if (mLogin_phonenum.getText().toString().trim().startsWith("1")
                        && mLogin_phonenum.getText().toString().trim().length() == 11) {
                    TimerUtils.TimerStart();
                    getSmsCode(mLogin_phonenum.getText().toString().trim()); //  发送验证码
                } else {
                    ToastUtils.showToast(this, "请检查手机号码是否正确");
                }


                break;
            case R.id.changelogintype:
                if (logintype == "1") {
                    loginFlag = false;
                    mChangelogintype.setText("密码登录");
                    mLogin_rely_pass.setVisibility(View.GONE);
                    mRely_olduser.setVisibility(View.VISIBLE);
                    logintype = "2";
                } else {
                    loginFlag = true;
                    mChangelogintype.setText("验证码登录");
                    mLogin_rely_pass.setVisibility(View.VISIBLE);
                    mRely_olduser.setVisibility(View.GONE);
                    logintype = "1";
                }


                break;
            case R.id.forgetpass:
                Intent inf = new Intent(this, ForgetPassActivity.class);
                inf.putExtra("ForgetPassActivity", 2);
                startActivity(inf);
                finish();
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.login_to_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TimerUtils.initTimer(this, mBtn_yanzhengma, 60000, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TimerUtils.TimerStop("发送验证码");
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

    private void vaLidateSmsCode(String trim) {

        mClientRestAPIVer.validate(trim).enqueue(new Callback<SmsCode>() {
            @Override
            public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {

                if (response.body() != null) {

                    boolean data = response.body().isData();
                    if (data) {
                        LUtils.d(TAG, "data==" + data + "== response.body()=" + response.body().getResult());
                        queryUserInfoByUserMobile(mLogin_phonenum.getText().toString().trim());

                    } else {
                        ToastUtils.showToast(LoginActivity.this, "验证码错误");
                    }
                }

            }

            @Override
            public void onFailure(Call<SmsCode> call, Throwable t) {

            }
        });

    }

    private void loginAppForPhone() {
        if (mLogin_phonenum.getText().toString().length() == 11
                && mLogin_phonenum.getText().toString().trim().startsWith("1") &&
                mLogin_password.getText().toString() != null) {
            Call<LoginBean> regAppUserCall = HttpManager.getInstance().getHttpClient3()
                    .loginAppUser(mLogin_phonenum.getText().toString(), mLogin_password.getText().toString());
            regAppUserCall.enqueue(new Callback<LoginBean>() {
                @Override
                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            ShareUtil.putString(Contants.LOGIN_USER_PHONE, response.body().getUserInfo().getMobile());
                            ShareUtil.putString(Contants.LOGIN_USER_NAME, response.body().getUserInfo().getUserName());
                            ShareUtil.putString(Contants.LOGIN_USER_HEAD, response.body().getUserInfo().getPhoto());
                            ShareUtil.putString(Contants.LOGIN_ORGMEMBERID, response.body().getUserInfo().getOrgMemberId());
                            ShareUtil.putString(Contants.LOGIN_USERMEMBERID, response.body().getUserInfo().getUserMemberId());
                            finish();
                        } else {
                            ToastUtils.showToast(LoginActivity.this, response.body().getMsg());
                        }
                    } else {
                        ToastUtils.showToast(LoginActivity.this, response.body().getMsg());
                    }

                }

                @Override
                public void onFailure(Call<LoginBean> call, Throwable t) {

                }
            });
        } else {
            ToastUtils.showToast(this, R.string.login_wx_editokphone);
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
                        String openId = response.body().getUserInfo().getOpenId();
                        if (openId != null) {
                            mLogin_rely_pass.setVisibility(View.GONE);
                            mRely_olduser.setVisibility(View.VISIBLE);
                            loginFlag = false;
                            ShareUtil.putString(Contants.LOGIN_USER_PHONE, mUserInfo.getMobile());
                            ShareUtil.putString(Contants.LOGIN_USER_NAME, mUserInfo.getUserName());
                            ShareUtil.putString(Contants.LOGIN_USER_HEAD, mUserInfo.getPhoto());
                            ShareUtil.putString(Contants.LOGIN_ORGMEMBERID, mUserInfo.getOrgMemberId());
                            ShareUtil.putString(Contants.LOGIN_USERMEMBERID, mUserInfo.getUserMemberId());

                            LUtils.d(TAG, "mUserInfo.getMobile()=" + mUserInfo.getMobile());
                            LUtils.d(TAG, "mUserInfo.getMobile()=" + mUserInfo.getUserName());
                            LUtils.d(TAG, "mUserInfo.getMobile()=" + mUserInfo.getPhoto());
                            LUtils.d(TAG, "mUserInfo.getMobile()=" + mUserInfo.getOrgMemberId());
                            LUtils.d(TAG, "mUserInfo.getMobile()=" + mUserInfo.getUserMemberId());
                            finish();
                        }
                    } else {
                        Intent in = new Intent(LoginActivity.this, RegistActivity.class);
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
