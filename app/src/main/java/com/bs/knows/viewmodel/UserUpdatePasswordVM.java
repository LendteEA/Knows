package com.bs.knows.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.databinding.ActivityChangePasswdBinding;
import com.bs.knows.model.UserUtilsModel;

public class UserUpdatePasswordVM extends BaseObservable {

    private ActivityChangePasswdBinding binding;

    public UserUpdatePasswordVM(ActivityChangePasswdBinding binding) {
        this.binding = binding;
    }

    public void CheckUserUpdateMessage(View view){
        Context context=view.getContext();
        UserUtilsModel.updatePassword(context,
                binding.inputPasswordOld.getInputStr().trim(),
                binding.inputPasswordNew.getInputStr().trim(),
                binding.inputPasswordConfirmNew.getInputStr().trim());
    }
}
