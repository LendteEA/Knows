package com.bs.knows.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bs.knows.R;
import com.bs.knows.model.UserUtilsModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author LendteEA
 * @since 2020/2/21 13:31
 * 欢迎页面 延迟三秒 跳转到主页面
 **/
public class WelcomeActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initwelcome();
    }

    /**
     * @author LendteEA
     * @since 2020/2/21 13:30
     * 欢迎页面初始化
     **/
    private void initwelcome() {
        final boolean isLogin = UserUtilsModel.UserIsLogin.validateUserLogin(this);
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(isLogin){
                    toMainActivity();
                }else {
                    toLoginActivity();
                }
            }
        }, 1000);

    }

    /**
     * @author LendteEA
     * @since 2020/2/21 13:29
     * 跳转到MainActivity
     **/
    private void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * @author LendteEA
     * @since 2020/2/21 13:29
     * 跳转到LoginActivity
     **/
    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
