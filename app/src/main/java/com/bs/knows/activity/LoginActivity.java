package com.bs.knows.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.viewmodel.UserLoginVM;
import com.bs.knows.R;
import com.bs.knows.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        UserLoginVM userLoginVM = new UserLoginVM(binding);
        binding.setUserlogin(userLoginVM);
        initNavBar(false, "登录", false);



    }
}
