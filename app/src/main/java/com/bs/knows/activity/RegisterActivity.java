package com.bs.knows.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityRegisterBinding;
import com.bs.knows.viewmodel.UserRegisterVM;
import com.bs.knows.views.InputView;

public class RegisterActivity extends BaseActivty {

    private InputView mInputPhone, mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding binding=
                DataBindingUtil.setContentView(this,R.layout.activity_register);
        UserRegisterVM userRegisterVM =new UserRegisterVM(binding);
        binding.setUserRegister(userRegisterVM);

        initNavBar(true,"注册",false);
    }
}
