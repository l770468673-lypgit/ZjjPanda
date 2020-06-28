package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.apps.XLBaseActivity;
import com.zjjxl.panda.interfaces.ParamConst;
import com.zjjxl.panda.utils.StatusBarUtil;
import com.zjjxl.panda.utils.ToastUtils;

public class eIDActivity extends
        XLBaseActivity implements View.OnClickListener {
    //    private ImageView mChongzhinfc_imageview_back;
    //    private TextView mChongzhinfc_textView;
    private Button mEid_add;
    private Button mEid_nowbegin;
    private RelativeLayout mActivity_eid_lly2;
    private LinearLayout mLly_eid_lly1;
    private CheckBox mChecboxs;
    private TextView mJihuo_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDrawable(this, R.drawable.trip_fragmentsearch_color);
        StatusBarUtil.setStatusBarLightMode(this.getWindow());
        setContentView(R.layout.activity_eid);
        //
        initView();
    }

    private void initView() {
        //        mChongzhinfc_imageview_back = findViewById(R.id.chongzhinfc_imageview_back);
        //        mChongzhinfc_textView = findViewById(R.id.chongzhinfc_textView);
        mJihuo_back = findViewById(R.id.jihuo_back);
        mEid_add = findViewById(R.id.eid_add);
        mEid_nowbegin = findViewById(R.id.eid_nowbegin);
        mActivity_eid_lly2 = findViewById(R.id.activity_eid_lly2);
        mLly_eid_lly1 = findViewById(R.id.lly_eid_lly1);
        mChecboxs = findViewById(R.id.checboxs);


        mEid_nowbegin.setOnClickListener(this);
        mEid_add.setOnClickListener(this);
        mJihuo_back.setOnClickListener(this);
        //        mChongzhinfc_imageview_back.setOnClickListener(this);
        //        mChongzhinfc_textView.setText(R.string.eidactivity_eidreg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eid_add:
                //                                mLly_eid_lly1.setVisibility(View.GONE);
                //                                mActivity_eid_lly2.setVisibility(View.VISIBLE);
                Intent in = new Intent(this, CardActiviting.class);
                //                startActivityForResult(in, ParamConst.READ_CARD_INFO_CODE);
                startActivity(in);
                break;
            case R.id.jihuo_back:
                finish();
                break;
            case R.id.eid_nowbegin:
                if (mChecboxs.isChecked()) {
                    //                    Intent in = new Intent(this, CardActiviting.class);
                    //                    startActivityForResult(in, ParamConst.READ_CARD_INFO_CODE);
                } else {
                    ToastUtils.showToast(this, "请同意《eID用户协议》");
                }

                break;
        }
    }
}
