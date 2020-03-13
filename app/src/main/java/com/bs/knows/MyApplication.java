package com.bs.knows;

import android.app.Application;

import com.bs.knows.utils.RecoginzeService;

import cn.bmob.v3.Bmob;

//存储关键信息，在开始运行时立即执行
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "642d533f46372da6128be5142a5dc513");
        RecoginzeService.initAccessTokenLicenseFile(this);
    }
}
