package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.activity.LoginActivity;
import com.bs.knows.databinding.ActivityRegisterBinding;
import com.bs.knows.model.UserUtilsModel;

public class UserRegisterVM extends BaseObservable {

    private ActivityRegisterBinding binding;
    private UserUtilsModel userUtilsModel;

    public UserRegisterVM(ActivityRegisterBinding binding) {
        this.binding = binding;
    }

    public void CheckUserRegisterMessage(View view) {
        Context context = view.getContext();
        UserUtilsModel.signUp(context, binding.inputPhoneRegister.getInputStr().trim(),
                binding.inputPasswordRegister.getInputStr().trim(),
                binding.inputRepasswordRegister.getInputStr().trim());
    }

    public void backtoLoginActivity(View view) {
        Context context=view.getContext();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
