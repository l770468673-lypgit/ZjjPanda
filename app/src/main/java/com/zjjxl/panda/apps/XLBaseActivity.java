package com.zjjxl.panda.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.zjjxl.panda.R;
import com.zjjxl.panda.utils.StatusBarUtil;
// 获取sha1 命令
//D:\Android\jdk18Install\jdk\bin\keytool -v -list -keystore C:\Users\Administrator\Desktop\ZjjPanda\zjjpanda.jks

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
        StatusBarUtil.MIUISetStatusBarLightMode(getWindow(), true);
    }
}
