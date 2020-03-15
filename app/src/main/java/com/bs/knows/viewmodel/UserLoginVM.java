package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bs.knows.activity.RegisterActivity;
import com.bs.knows.databinding.ActivityLoginBinding;
import static com.bs.knows.model.UserUtilsModel.userLogin;

public class UserLoginVM {

    private ActivityLoginBinding mbinding;


    public UserLoginVM(ActivityLoginBinding binding){
        this.mbinding=binding;

    }

    public void LoginCheckUserMessage(View view){
        Context context=view.getContext();
        userLogin(context,
                mbinding.inputPhoneLogin.getInputStr(),
                mbinding.inputPasswordLogin.getInputStr());
    }

    public void gotoRegister(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
