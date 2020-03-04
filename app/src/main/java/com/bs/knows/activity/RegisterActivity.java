package com.bs.knows.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityRegisterBinding;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.viewmodel.UserLoginViewModel;
import com.bs.knows.viewmodel.UserRegisterViewModel;
import com.bs.knows.views.InputView;

public class RegisterActivity extends BaseActivty {

    private InputView mInputPhone, mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding binding=
                DataBindingUtil.setContentView(this,R.layout.activity_register);
        UserRegisterViewModel userRegisterViewModel =new UserRegisterViewModel(binding);
        binding.setUserRegister(userRegisterViewModel);

        initNavBar(true,"注册",false);
    }
}
