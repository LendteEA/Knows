package com.bs.knows.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class getBacePermission implements requestPermission {
    @Override
    public void requestPermission(Activity activity,String permission) {
        //检查这个权限是否已经获取
        int checkWriteExternalStoragePermission = ActivityCompat.checkSelfPermission(activity, permission);
        if (checkWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            //如果没有权限则获取权限 requestCode在后面回调中会用到
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 3);
            Log.e("没有权限，", "请求权限");
        }
    }
}
