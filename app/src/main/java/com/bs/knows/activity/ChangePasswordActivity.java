package com.bs.knows.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityChangePasswdBinding;
import com.bs.knows.viewmodel.UserUpdatePasswordVM;
import com.bs.knows.views.InputView;

public class ChangePasswordActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangePasswdBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_change_passwd);
        UserUpdatePasswordVM updatePasswordViewModel=new UserUpdatePasswordVM(binding);
        binding.setUserupdatepassword(updatePasswordViewModel);

        initNavBar(true, "修改密码", false);
    }
}
