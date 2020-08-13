package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.StatusBarUtil;

public class ForgetPassActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mForget_text;
    private TextView mForget_back;
    private TextView mReset_login_phonenum;
    private TextView mReset_login_password;
    private TextView mChange_login_phonenum;
    private TextView mChange_login_password;
    private TextView mCange_login_newpassword;
    private Button mFor_change_logindenglu;
    private int mForgetPassActivities;
    private LinearLayout mReset_username_layout;
    private LinearLayout mChange_username_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarLightMode(getWindow());
        StatusBarUtil.setDrawable(this, R.drawable.fragment_bennefit_boxframe);
        setContentView(R.layout.activity_forget_pass);
        mForgetPassActivities = getIntent().getIntExtra("ForgetPassActivity", 0);


        initView();

    }

    private void initView() {
        mForget_text = findViewById(R.id.forget_text);
        mForget_back = findViewById(R.id.forget_back);

        mFor_change_logindenglu = findViewById(R.id.for_change_logindenglu);
        mReset_username_layout = findViewById(R.id.reset_username_layout);
        mChange_username_layout = findViewById(R.id.change_username_layout);

        mFor_change_logindenglu.setOnClickListener(this);
        mForget_back.setOnClickListener(this);

        if (mForgetPassActivities == 1) {
            mForget_text.setText("修改密码");
            mChange_username_layout.setVisibility(View.VISIBLE);
            mReset_username_layout.setVisibility(View.GONE);
        } else if (mForgetPassActivities == 2) {
            mForget_text.setText("重置密码");
            mReset_username_layout.setVisibility(View.VISIBLE);
            mChange_username_layout.setVisibility(View.GONE);
        }
        forget();
        changeNewPass();
    }

    private void changeNewPass() {
        mChange_login_phonenum = findViewById(R.id.change_login_phonenum);
        mChange_login_password = findViewById(R.id.change_login_password);
        mCange_login_newpassword = findViewById(R.id.change_login_newpassword);

    }

    private void forget() {
        mReset_login_phonenum = findViewById(R.id.reset_login_phonenum);
        mReset_login_password = findViewById(R.id.reset_login_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.for_change_logindenglu:

                if (mForgetPassActivities==1){


                    changepassw();
                }else {
                    mReset_login_phonenum.getText();
                    mReset_login_password.getText();

                }
                break;
            case R.id.forget_back:
                finish();
                break;
        }
    }

    private void changepassw() {
        mChange_login_phonenum.getText();
        mChange_login_password.getText();
        mCange_login_newpassword.getText();

//        HttpManager.getInstance().getHttpClient3().updateUserPassword();
    }
}
