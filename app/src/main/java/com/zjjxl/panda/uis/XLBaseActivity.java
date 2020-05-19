package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.StatusBarUtil;

public class XLBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDrawable(this, R.drawable.mine_title_color);
    }
}
