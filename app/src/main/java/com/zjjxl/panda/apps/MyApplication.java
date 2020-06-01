package com.zjjxl.panda.apps;

import android.app.Application;

import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.ShareUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtil.initShared(this);
        HttpManager.getInstance();
    }
}
