package com.bs.knows.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bs.knows.R;
import com.bs.knows.utils.UserUtils;
import com.bs.knows.views.InputView;

public class RegisterActivity extends BaseActivty {

    private InputView mInputPhone, mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);

        mInputPhone = fd(R.id.input_phone_register);
        mInputPassword = fd(R.id.input_password_register);
        mInputPasswordConfirm = fd(R.id.input_repassword_register);
    }

    public void onCommitClick(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void RegonRegisterClick(View view) {
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String passwordConfirm = mInputPasswordConfirm.getInputStr();
        Context context=getBaseContext();

        UserUtils.signUp(context,phone,password,passwordConfirm);
        this.finish();
    }
}
