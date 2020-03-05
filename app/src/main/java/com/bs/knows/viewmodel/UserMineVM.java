package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.activity.ChangePasswordActivity;
import com.bs.knows.databinding.ActivityMineBinding;
import com.bs.knows.model.UserUtilsModel;

public class UserMineVM extends BaseObservable {

    private ActivityMineBinding binding;

    public UserMineVM(ActivityMineBinding binding) {
        this.binding = binding;
    }

    public void gotoUpdatePassword(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, ChangePasswordActivity.class));
    }

    public void userLogout(View view){
        Context context=view.getContext();
        UserUtilsModel.logout(context);
    }
}
