package com.bs.knows.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bs.knows.R;
import com.bs.knows.utils.UserUtils;
import com.bs.knows.views.InputView;

public class LoginActivity extends BaseActivty {

    private InputView mInputPhone, mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        initNavBar(false, "登录", false);
        mInputPhone = fd(R.id.input_phone_login);
        mInputPassword = fd(R.id.input_password_login);
    }


    /**
     * 跳转注册页面点击事件
     */
    public void onRegisterClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));

    }

    /**
     * 验证成功后 跳转主面点击事件
     */
    public void onCommitClick(View view) {
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();

//        UserUtils.userLogin(this,phone,password);
//        this.finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
