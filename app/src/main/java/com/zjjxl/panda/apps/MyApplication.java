package com.zjjxl.panda.apps;

import android.app.Application;

import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.ShareUtil;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ShareUtil.initShared(this);
        HttpManager.getInstance();
    }


    public static MyApplication getInstance() {
        return instance;
    }
}
