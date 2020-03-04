package com.bs.knows.permission;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PermissionUtils
 * Author: gjn.
 * Time: 2017/11/3.
 */

public class PermissionUtils {

    private static final String TAG = "PermissionUtils";

    public static final int CODE_CALENDAR = 0;      //日历权限
    public static final int CODE_CAMERA = 1;        //相机权限
    public static final int CODE_CONTACTS = 2;      //联系人权限
    public static final int CODE_LOCATION = 3;      //定位权限
    public static final int CODE_MICROPHONE = 4;    //麦克相关权限
    public static final int CODE_PHONE = 5;         //手机状态权限
    public static final int CODE_SENSORS = 6;       //传感器权限
    public static final int CODE_SMS = 7;           //短信权限
    public static final int CODE_STORAGE = 8;       //SD卡权限
    public static final int CODE_MULTI = 111;       //多个权限

    public static final String PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;
    public static final String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;

    public static final Map<Integer, String> codePermissions = new HashMap<>();

    static {
        codePermissions.put(CODE_CALENDAR, "日历权限");
        codePermissions.put(CODE_CAMERA, "相机权限");
        codePermissions.put(CODE_CONTACTS, "联系人权限");
        codePermissions.put(CODE_LOCATION, "定位权限");
        codePermissions.put(CODE_MICROPHONE, "麦克相关权限");
        codePermissions.put(CODE_PHONE, "手机状态权限");
        codePermissions.put(CODE_SENSORS, "传感器权限");
        codePermissions.put(CODE_SMS, "短信权限");
        codePermissions.put(CODE_STORAGE, "SD卡权限");
        codePermissions.put(CODE_MULTI, "多个权限");
    }

    public interface PermissionCallBack {
        void onSuccess(int code);

        void onFaile(int code);

        void onConsumer(int code);
    }

    /**
     * 判断是否需要获取权限
     *
     * @param activity
     * @param code
     * @param permissions
     * @return
     */
    public static boolean requestPermissions(Activity activity, int code, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(TAG, "api < 23");
            return false;
        }
        List<String> notGranted = new ArrayList<>();

        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permission);
            }
        }

        if (notGranted.size() != 0) {
            ActivityCompat.requestPermissions(activity,
                    notGranted.toArray(new String[notGranted.size()]), code);
            return true;
        }
        return false;
    }

    /**
     * 判断是否需要获取权限
     *
     *
     *
     *
     * @param activity
     * @param code
     * @return
     */
    public static boolean requestPermissions(Activity activity, Integer... code) {
        List<String> permissions = new ArrayList<>();
        List<Integer> codes = Arrays.asList(code);
        //日历权限
//        if (codes.contains(CODE_CALENDAR)) {
//            permissions.add(PERMISSION_READ_CALENDAR);
//        }
        //相机权限
        if (codes.contains(CODE_CAMERA)) {
            permissions.add(PERMISSION_CAMERA);
        }
        //联系人权限
//        if (codes.contains(CODE_CONTACTS)) {
//            permissions.add(PERMISSION_WRITE_CONTACTS);
//        }
//        //定位权限
//        if (codes.contains(CODE_LOCATION)) {
//            permissions.add(PERMISSION_ACCESS_FINE_LOCATION);
//        }
//        //麦克相关权限
//        if (codes.contains(CODE_MICROPHONE)) {
//            permissions.add(PERMISSION_RECORD_AUDIO);
//        }
//        //手机状态权限
//        if (codes.contains(CODE_PHONE)) {
//            permissions.add(PERMISSION_READ_PHONE_STATE);
//        }
//        //传感器权限
//        if (codes.contains(CODE_SENSORS)) {
//            permissions.add(PERMISSION_BODY_SENSORS);
//        }
//        //短信权限
//        if (codes.contains(CODE_SMS)) {
//            permissions.add(PERMISSION_READ_SMS);
//        }
        //SD卡权限
        if (codes.contains(CODE_STORAGE)) {
            permissions.add(PERMISSION_READ_EXTERNAL_STORAGE);
        }

        if (permissions.size() == 0) {
            return false;
        }

        return requestPermissions(activity, CODE_MULTI,
                permissions.toArray(new String[permissions.size()]));
    }

    public static void requestPermissionsResult(final Activity activity, final int requestCode,
                                                String[] permissions, int[] grantResults,
                                                final PermissionCallBack callBack) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(TAG, "api < 23");
            return;
        }
        List<String> notGranted = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() != 0) {
            StringBuilder notGrantedString = new StringBuilder();
            notGrantedString.append("被拒绝权限：\n");
            for (String s : notGranted) {
                notGrantedString.append(s + "\n");
            }
            notGrantedString.append("请进入 软件设置 -> 权限管理 中获取");
            showPermissionDialog(activity, notGrantedString.toString(),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startAppSettingIntent(activity);
                            callBack.onConsumer(requestCode);
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callBack.onFaile(requestCode);
                        }
                    });
        } else {
            callBack.onSuccess(requestCode);
        }
    }

    public static void startAppSettingIntent(Activity activity) {
        Toast.makeText(activity, "获取权限之后,请重启软件!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }

    public static void showPermissionDialog(Activity activity, String message,
                                            DialogInterface.OnClickListener oklistener,
                                            DialogInterface.OnClickListener canclelistener) {
        new AlertDialog.Builder(activity)
                .setTitle("帮助")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("获取", oklistener)
                .setNegativeButton("退出", canclelistener)
                .create()
                .show();
    }

}
