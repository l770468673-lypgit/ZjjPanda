package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.LoginBean;
import com.zjjxl.panda.beans.SmsCode;
import com.zjjxl.panda.beans.regAppUser;
import com.zjjxl.panda.https.ClientRestAPI;
import com.zjjxl.panda.https.HttpCallback;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.interceptor.AddCookiesInterceptor;
import com.zjjxl.panda.interceptor.ReceivedCookiesInterceptor;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.TimerUtils;
import com.zjjxl.panda.utils.ToastUtils;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_yanzhengma;
    private EditText mOkregPhonenum;
    private static String sVcode;
    private Button mRegist_login_next;
    private String TAG = "RegistActivity";
    private EditText mImgregpasswordyanzhengmg;
    private TextView mRegist_back;
    private EditText mReal_regist_password;
    private TextView mReal_regist_phonenum;
    private Button mRegist_commit;
    private Dialog mDialog;
    private LinearLayout mRegist_lly_01;
    private LinearLayout mRegist_lly_02;
    private String mSessionis;
    private Retrofit mRetrofitsend;
    private ClientRestAPI mClientRestAPIsend;
    private Retrofit mRetrofitVer;
    private ClientRestAPI mClientRestAPIVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initSend();
        initVer();
        initView();


    }

    private void initView() {
        mSessionis = ShareUtil.getString(Contants.APP_SESSIONIS);
        mBtn_yanzhengma = findViewById(R.id.btn_yanzhengma);
        mRegist_back = findViewById(R.id.regist_back);
        mImgregpasswordyanzhengmg = findViewById(R.id.imgregpasswordyanzhengmg);
        mOkregPhonenum = findViewById(R.id.regist_phonenum);
        mRegist_login_next = findViewById(R.id.regist_nextsext);

        mRegist_commit = findViewById(R.id.regist_commit);
        mReal_regist_password = findViewById(R.id.real_regist_password);
        mReal_regist_phonenum = findViewById(R.id.real_regist_phonenum);
        mRegist_lly_01 = findViewById(R.id.regist_lly_01);
        mRegist_lly_02 = findViewById(R.id.regist_lly_02);


        mRegist_commit.setOnClickListener(this);
        mBtn_yanzhengma.setOnClickListener(this);
        mRegist_back.setOnClickListener(this);
        mRegist_login_next.setOnClickListener(this);
        TimerUtils.initTimer(this, mBtn_yanzhengma, 60000, 1000);
        showDialog();


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yanzhengma:
                if (mOkregPhonenum.getText().toString().trim().length() == 11
                        && mOkregPhonenum.getText().toString().trim().startsWith("1")) {
                    TimerUtils.TimerStart();
                    sVcode = null;
                    getSmsCode(mOkregPhonenum.getText().toString().trim());
                } else {
                    ToastUtils.showToast(this, R.string.login_wx_editokphone);
                }

                break;
            case R.id.regist_nextsext:
                if (mOkregPhonenum.getText().toString().trim().length() == 11 &&
                        mImgregpasswordyanzhengmg.getText().toString().trim().length() == 6) {
                    vaLidateSmsCode(mImgregpasswordyanzhengmg.getText().toString().trim());
                } else {
                    ToastUtils.showToast(this, R.string.login_wx_editokphone);
                }
                break;
            case R.id.regist_commit:

                if (mReal_regist_phonenum.getText().toString().trim().length() == 11
                        && mReal_regist_password.getText().toString() != null) {
                    toRegist(mReal_regist_phonenum.getText().toString(),
                            mReal_regist_password.getText().toString());

                } else {
                    ToastUtils.showToast(this, R.string.login_wx_editokphone);
                }
                break;
            case R.id.regist_back:
                finish();
                break;
        }
    }

    private void toRegist(String phone, String pass) {
        Call<regAppUser> regAppUserCall =
                HttpManager.getInstance().getHttpClient3().registerAppUser(phone, pass);
        regAppUserCall.enqueue(new Callback<regAppUser>() {
            @Override
            public void onResponse(Call<regAppUser> call, Response<regAppUser> response) {
                if (response.body() != null) {
                    String msg = response.body().getMsg();
                    if (msg.contains("用户存在")) {
                        // 已注册过
                        ToastUtils.showToast(RegistActivity.this, "用户存在,注册失败");
                    } else {
                        // 未注册过 ，注册成功
                        mDialog.show();
                    }
                }


            }

            @Override
            public void onFailure(Call<regAppUser> call, Throwable t) {

            }
        });
    }

    private void showDialog() {
        mDialog = new Dialog(RegistActivity.this, R.style.edit_AlertDialog_style);
        mDialog.setContentView(R.layout.regist_showdialog);
        ImageView imageView = (ImageView) mDialog.findViewById(R.id.regist_doalog_img);
//        imageView.setBackgroundResource(R.mipmap.iv_android);
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        mDialog.setCanceledOnTouchOutside(true); // Sets whether this dialog is
//        Window w = mDialog.getWindow();
//        WindowManager.LayoutParams lp = w.getAttributes();
//        lp.x = 0;
//        lp.y = 40;
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        Window window = mDialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.4); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 0.85); // 宽度设置为屏幕的0.65
//        p.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.65

        mDialog.onWindowAttributesChanged(p);
        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                        tologin();
                    }
                });

    }

    private void tologin() {
        if (mReal_regist_phonenum.getText().toString().trim().length() == 11
                && mReal_regist_password.getText().toString() != null) {
            Call<LoginBean> regAppUserCall = HttpManager.getInstance().getHttpClient3()
                    .loginAppUser(mReal_regist_phonenum.getText().toString(), mReal_regist_password.getText().toString());
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
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginBean> call, Throwable t) {

                }
            });
        }
    }

    private void vaLidateSmsCode(String trim) {

        mClientRestAPIVer.validate(trim).enqueue(new Callback<SmsCode>() {
            @Override
            public void onResponse(Call<SmsCode> call, Response<SmsCode> response) {

                if (response.body() != null) {

                    boolean data = response.body().isData();
                    if (data) {
                        LUtils.d(TAG, "data==" + data + "== response.body()=" + response.body().getResult());
                        mRegist_lly_01.setVisibility(View.GONE);
                        mRegist_lly_02.setVisibility(View.VISIBLE);
                        mReal_regist_phonenum.setText(mOkregPhonenum.getText().toString());
                    } else {
                        ToastUtils.showToast(RegistActivity.this, "验证码错误");
                    }
//                    mRegist_lly_01.setVisibility(View.GONE);
//                    mRegist_lly_02.setVisibility(View.VISIBLE);
//                    mReal_regist_phonenum.setText(mOkregPhonenum.getText().toString());
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
                    ToastUtils.showToast(RegistActivity.this, "data==" + data);

                    LUtils.d(TAG, "onResponse== data==" + data);
                }
            }

            @Override
            public void onFailure(Call<SmsCode> call, Throwable t) {
                LUtils.d(TAG, "onFailure==" + t);
            }
        });

    }


}
