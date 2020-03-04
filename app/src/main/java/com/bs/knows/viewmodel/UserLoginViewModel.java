package com.bs.knows.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.activity.MainActivity;
import com.bs.knows.activity.RegisterActivity;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.databinding.ActivityLoginBinding;
import com.bs.knows.views.InputView;

import static com.bs.knows.model.UserUtilsModel.userLogin;

public class UserLoginViewModel extends BaseObservable {

    private ActivityLoginBinding mbinding;


    public UserLoginViewModel(ActivityLoginBinding binding){
        this.mbinding=binding;

    }

    public void LoginCheckUserMessage(View view){
        Context context=view.getContext();
//        userLogin(context,
//                mbinding.inputPhoneLogin.getInputStr(),
//                mbinding.inputPasswordLogin.getInputStr());
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public void gotoRegister(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
