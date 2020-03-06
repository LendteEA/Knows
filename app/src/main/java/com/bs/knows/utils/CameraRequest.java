package com.bs.knows.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

import com.bs.knows.activity.CameraActivity;
import com.zhihu.matisse.Matisse;

import java.util.List;

@SuppressLint("Registered")
public class CameraRequest extends CameraActivity {

    private static Intent mdata;
    private static int mRequestCode;
    private static int mResultCode;

    public static Intent getMdata() {
        return mdata;
    }

    public static int getmRequestCode() {
        return mRequestCode;
    }

    public static int getmResultCode() {
        return mResultCode;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            mRequestCode=requestCode;
            mResultCode=resultCode;
            mdata=data;
        
    }
}
