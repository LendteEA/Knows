package com.bs.knows.utils;

import android.Manifest;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bs.knows.R;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class PermissionUtils {

    public static void Permissionx(final Context context) {
        List<PermissionItem> permissions = new ArrayList<PermissionItem>();
        permissions.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取本地文件", R.drawable.ic_file));
        permissions.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入本地文件", R.drawable.ic_file));
        permissions.add(new PermissionItem(Manifest.permission.CAMERA,"允许打开相机",R.drawable.ic_camera));
        HiPermission.create(context)
                .title("请求权限")
                .permissions(permissions)
                .msg("为保证正常使用，请授权一下功能权限")
                .animStyle(R.style.PermissionAnimModal)
                .style(R.style.PermissionDefaultGreenStyle)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        Log.i(TAG, "onClose");
                        showToast(String.valueOf(R.string.permission_cancel),context);
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        Log.i(TAG, "onDeny");
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        Log.i(TAG, "onGuarantee");
                    }
                });


    }
    private static void showToast(String text, Context context) {//简化Toast
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    private static final String TAG = "RegisterActivity";
}
