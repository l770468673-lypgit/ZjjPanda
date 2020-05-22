package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.StatusBarUtil;

public class XLBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null &&
                    intentAction.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        StatusBarUtil.setDrawable(this, R.drawable.mine_title_color);
    }
}
