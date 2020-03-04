package com.bs.knows.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.viewmodel.UserLoginViewModel;
import com.bs.knows.R;
import com.bs.knows.databinding.ActivityLoginBinding;
import com.bs.knows.permission.PermissionUtils;
import com.bs.knows.views.InputView;

public class LoginActivity extends BaseActivty {

    private InputView mInputPhone, mInputPassword;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        UserLoginViewModel userLoginViewModel =new UserLoginViewModel(binding);
        binding.setUserlogin(userLoginViewModel);
        initView();
    }


    private void initView() {
        initNavBar(false, "登录", false);
        PermissionUtils.requestPermissions(this, 1, 8);
    }


}
