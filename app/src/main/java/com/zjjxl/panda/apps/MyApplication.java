package com.zjjxl.panda.apps;

import android.app.Application;

import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.ShareUtil;


/**
 * 双阳测试环境
 * //终端号248200000000
 * //用户名24820000
 * //密码00000000000000000000000000000000
 * //发卡机构代码05212482
 * //114.242.105.186 8800
 * sdkAccessChannel：xm100002
 */

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
