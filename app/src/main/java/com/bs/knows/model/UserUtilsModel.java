package com.bs.knows.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.bs.knows.R;
import com.bs.knows.activity.LoginActivity;
import com.bs.knows.activity.MainActivity;
import com.bs.knows.activity.MineActivity;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.UserRegister;
import com.bs.knows.connect.bean.UserUpdatePs;
import com.bs.knows.connect.bean.checkUserId;
import com.bs.knows.connect.bean.checkUserPswd;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.databinding.ActivityMineBinding;
import com.bs.knows.utils.SPUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class UserUtilsModel {

    private static String TAG = "TAG";


//    ======================================================  用户登录  ======================================================

    /**
     * 用户登录
     *
     * @param context  获得View
     * @param phone    用户名
     * @param password 密码
     */
    public static void userLogin(final Context context, String phone, String password) {
        if (!validateLogin(context, phone, password)) {
            return;
        }

        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.UserisExist(phone).enqueue(new Callback<checkUserId>() {
            @Override
            public void onResponse(Call<checkUserId> call, Response<checkUserId> response) {
                boolean isExist=response.body().isUserIsExist();


                if(isExist){
                    Log.d(TAG, "onResponse: "+phone +password);
                    api.UserDataPasswdisCorrect(phone,password).enqueue(new Callback<checkUserPswd>() {
                        @Override
                        public void onResponse(Call<checkUserPswd> call, Response<checkUserPswd> response) {
                            boolean isPassword=response.body().isPassword();
                            boolean isE=response.body().isError();
                            Log.d(TAG, "onResponse: "+isPassword+isE);
                            if(isPassword){
                                //保存用户登录标记，若失败则不可登录
                                boolean isSave = SPUtils.saveUser(context, phone);
                                if (!isSave) {
                                    Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                    //定义Activity跳转动画
                                    ((Activity) context).overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
                                    //保存用户标记（手机号）
                                    UserIsLogin.getInstance().setPhone(phone);

                                }
                            }else {
                                Toast.makeText(context, "登录失败！密码错误！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<checkUserPswd> call, Throwable t) {
                            Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    });


                }else {
                    Toast.makeText(context, "用户名不存在！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<checkUserId> call, Throwable t) {
                Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });

    }


//    ====================================================  用户自动登录  ====================================================

/**
 * 1.用户登录
 * 当用户登录应用程序时，利用SharedPreferences保存用户标记
 * 用全局单例类UserIsLogin保存登录信息
 * 在用户登录之后
 * 在用户重新打开程序时，检测SharedPreferences是否保存标记，
 * 如果存在则为UserIsLogin辅助，并进入主页。若不存在，则进入登录页面
 * <p>
 * 2.用户退出
 * 删除SharedPreferences中的标记，退出到登录页面
 */

public static class UserIsLogin {
    private static UserIsLogin instance;

    private UserIsLogin() {

    }

    /**
     * 验证是否存在已登录用户
     *
     * @return 返回标识符
     */
    public static boolean validateUserLogin(Context context) {
        return SPUtils.isLoginUser(context);
    }

    public static UserIsLogin getInstance() {
        if (instance == null) {
            synchronized (UserIsLogin.class) {
                if (instance == null) {
                    instance = new UserIsLogin();
                }
            }
        }
        return instance;
    }


    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
    //    ======================================================  用户信息  ======================================================

    public static void setUserName(ActivityMineBinding binding){
        binding.tvUser.setText("当前登录用户："+UserIsLogin.getInstance().getPhone());
    }







    //    ======================================================  用户注册  ======================================================

    /**
     * 账号密码注册
     *
     * @param context  获得View
     * @param phone    Username
     * @param password UserPassword
     */
    public static void signUp(final Context context, String phone, String password, String repassword) {
        if (!validateRegister(context, phone, password, repassword)) {
            return;
        }

        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.UserisExist(phone).enqueue(new Callback<checkUserId>() {
            @Override
            public void onResponse(Call<checkUserId> call, Response<checkUserId> response) {
                boolean isExist=response.body().isUserIsExist();
                if(!isExist){
                    api.UserRegister(phone,password).enqueue(new Callback<UserRegister>() {
                        @Override
                        public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                            boolean isRegister=response.body().isRegisterSuccess();
                            if(isRegister){
                                //保存用户登录标记，若失败则不可登录
                                boolean isSave = SPUtils.saveUser(context, phone);
                                if (!isSave) {
                                    Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                    //定义Activity跳转动画
                                    ((Activity) context).overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
                                    //保存用户标记（手机号）
                                    UserIsLogin.getInstance().setPhone(phone);

                                }
                            }else {
                                Toast.makeText(context, "注册失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegister> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(context, "注册失败！用户已存在！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<checkUserId> call, Throwable t) {

            }
        });
    }


//    ======================================================  用户退出登录  ======================================================

    /**
     * 退出登录
     *
     * @param context 获得View
     */
    public static void logout(Context context) {
//        删除sp保存的用户标记
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove) {
            Toast.makeText(context, "系统错误!请稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context, LoginActivity.class);
//        添加intent标志符，清理task栈，并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//        定义Activity跳转动画
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

//    ======================================================  用户修改密码  ======================================================

    /**
     * 用户修改密码
     *
     * @param context        获得view
     * @param old_password   旧密码
     * @param new_password   新密码
     * @param new_repassword 重新输入新密码
     */
    public static void updatePassword(final Context context, String old_password, String new_password, String new_repassword) {
        if (!validateUpdatePassword(context, old_password, new_password, new_repassword)) {
            return;
        }

        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        Log.d(TAG, "updatePassword: "+UserIsLogin.getInstance().getPhone());
        api.UserUpdatePs(UserIsLogin.getInstance().getPhone(),old_password,new_password).enqueue(new Callback<UserUpdatePs>() {
            @Override
            public void onResponse(Call<UserUpdatePs> call, Response<UserUpdatePs> response) {
                if(response.body().isOld_password()){
                    if(response.body().isUpdateSuccess()){
                        Toast.makeText(context, "密码修改成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MineActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        //        定义Activity跳转动画
                        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
                    }
                }else {
                    Toast.makeText(context, "修改密码失败！旧密码错误！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserUpdatePs> call, Throwable t) {

            }
        });

    }

//    ==============================================================================================================================


    /**
     * 验证登录输入框内容
     *
     * @param context  获得View
     * @param phone    Username
     * @param password UserPassword
     * @return boolean
     */
    private static boolean validateLogin(Context context, String phone, String password) {
//        精确地
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /**
     * 验证注册输入框内容
     *
     * @param context    获得View
     * @param phone      Username
     * @param password   UserPassword
     * @param repassword UserRePassword
     * @return boolean
     */
    private static boolean validateRegister(Context context, String phone, String password, String repassword) {

//        精确地
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(context, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(repassword)) {
            Toast.makeText(context, "两次新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /**
     * 验证重置密码输入框内容
     *
     * @param context        获得View
     * @param old_password   旧密码
     * @param new_password   新密码
     * @param new_repassword 重新输入新密码
     * @return boolean
     */
    private static boolean validateUpdatePassword(Context context, String old_password, String new_password, String new_repassword) {
//        简单的
//        RegexUtils.isMobileSimple(phone);
//        精确地
        if (TextUtils.isEmpty(old_password)) {
            Toast.makeText(context, "请输入旧密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(new_password)) {
            Toast.makeText(context, "请输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(new_repassword)) {
            Toast.makeText(context, "请再次输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!new_password.equals(new_repassword)) {
            Toast.makeText(context, "两次新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}

