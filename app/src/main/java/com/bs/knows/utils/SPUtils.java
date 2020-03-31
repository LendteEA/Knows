package com.bs.knows.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bs.knows.model.UserUtilsModel;

/**
 * 保存SharedPreferences标记
 */
public class SPUtils {

    /**
     * 当用户登录应用程序时，利用SharedPreferences保存用户标记
     */
    public static boolean saveUser(Context context, String phone) {
        SharedPreferences sp = context.getSharedPreferences(SPconstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPconstants.SP_KEY_PHONE, phone);
        boolean result = editor.commit();//保存成功则为true 反之为false
        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean isLoginUser(Context context) {
        boolean result = false;
        SharedPreferences sp = context.getSharedPreferences(SPconstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPconstants.SP_KEY_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            result = true;
            UserUtilsModel.UserIsLogin.getInstance().setPhone(phone);
        }

        return result;
    }

    /**
     * 删除用户标记
     */

    public static boolean removeUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SPconstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(SPconstants.SP_KEY_PHONE);
        boolean result=editor.commit();
        return result;
    }
}
