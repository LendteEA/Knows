package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.activity.ChangePasswordActivity;;
import com.bs.knows.model.UserUtilsModel;

public class UserMineVM extends BaseObservable {

    public void gotoUpdatePassword(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, ChangePasswordActivity.class));
    }

    public void userLogout(View view){
        Context context=view.getContext();
        UserUtilsModel.logout(context);
    }
}
