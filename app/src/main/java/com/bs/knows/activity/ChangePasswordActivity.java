package com.bs.knows.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityChangePasswdBinding;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.viewmodel.UserUpdatePasswordViewModel;
import com.bs.knows.views.InputView;

public class ChangePasswordActivity extends BaseActivty {

    private InputView old_password, new_password, new_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangePasswdBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_change_passwd);
        UserUpdatePasswordViewModel updatePasswordViewModel=new UserUpdatePasswordViewModel(binding);
        binding.setUserupdatepassword(updatePasswordViewModel);

        initNavBar(true, "修改密码", false);
    }
}
