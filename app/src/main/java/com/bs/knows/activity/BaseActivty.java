package com.bs.knows.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bs.knows.R;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 作为整个Activity的父类 描述所有Activity的共性
 */

@SuppressLint("Registered")
public class BaseActivty extends Activity implements EasyPermissions.PermissionCallbacks {


    private long exitTime = 0;

    /**
     * 初始化NavBar
     *
     * @param isShowBack 是否显示返回键
     * @param title      页面名称
     * @param isShowMine 是否显示我的
     */
    protected void initNavBar(boolean isShowBack, String title, boolean isShowMine) {

        ImageView ivBack = findViewById(R.id.iv_back);
        ImageView ivMine = findViewById(R.id.iv_mine);
        TextView tvTitle = findViewById(R.id.tv_title);

        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
        tvTitle.setText(title);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


//    /**
//     * 按两次返回键退出
//     *
//     * @param keyCode 获取按键id
//     * @param event   按键事件
//     * @return 返回是否按下按键
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void exit() {
//
//        if ((System.currentTimeMillis() - exitTime) > 3000) {
//            Toast.makeText(this,
//                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            exitTime = System.currentTimeMillis();
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode){
            case 0:
                Toast.makeText(this, "已获取相机权限", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "已获取文件读取权限", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "已获取文件写入权限", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //处理权限名字字符串
        StringBuilder sb = new StringBuilder();
        for (String str : perms){
            sb.append(str);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2,sb.length(),"");

        switch (requestCode){
            case 0:
                Toast.makeText(this, "已拒绝Camera权限" + perms.get(0), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "已拒绝文件读取权限", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "已拒绝文件写入权限", Toast.LENGTH_SHORT).show();
                break;
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问" , Toast.LENGTH_SHORT).show();
            new AppSettingsDialog
                    .Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("是")
                    .setNegativeButton("否")
                    .build()
                    .show();
        }
    }



}