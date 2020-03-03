package com.bs.knows.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.bs.knows.R;
import com.bs.knows.utils.UserUtils;
import com.bs.knows.views.InputView;

public class LoginActivity extends BaseActivty {

    private InputView mInputPhone, mInputPassword;
    private onBackButton mBackButton;
    private long exitTime=0;

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

//    /**
//     * 按两次返回键退出
//     * @param keyCode   获取按键id
//     * @param event     按键事件
//     * @return          返回是否按下按键
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void exit() {
//
//        if ((System.currentTimeMillis()-exitTime) > 3000) {
//            Toast.makeText(this,
//                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            exitTime = System.currentTimeMillis();
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }
}
