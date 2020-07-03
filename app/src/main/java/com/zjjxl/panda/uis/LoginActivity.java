package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.LoginBean;
import com.zjjxl.panda.beans.regAppUser;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mLogin_back;
    private TextView mLogin_to_regist;
    private EditText mLogin_phonenum;
    private EditText mLogin_password;
    private Button mLogin_logindenglu;
    private ImageView mLogin_passvisvilit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setDrawable(this, R.drawable.mine_fragment_color);
        StatusBarUtil.setStatusBarLightMode(getWindow());
        StatusBarUtil.setDrawable(this, R.drawable.fragment_bennefit_boxframe);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mLogin_back = findViewById(R.id.login_back);
        mLogin_to_regist = findViewById(R.id.login_to_regist);
        mLogin_phonenum = findViewById(R.id.login_phonenum);
        mLogin_password = findViewById(R.id.login_password);
        mLogin_logindenglu = findViewById(R.id.login_logindenglu);
        mLogin_passvisvilit = findViewById(R.id.login_passvisvilit);
        mLogin_back.setOnClickListener(this);
        mLogin_to_regist.setOnClickListener(this);
        mLogin_passvisvilit.setOnClickListener(this);
        mLogin_logindenglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_logindenglu:
                if (mLogin_phonenum.getText().toString().length() == 11 &&
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
                break;
            case R.id.login_passvisvilit:
                mLogin_phonenum.setText("");
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.login_to_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
        }
    }
}
