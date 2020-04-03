package com.bs.knows.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.connect.Api;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.viewmodel.UserLoginVM;
import com.bs.knows.R;
import com.bs.knows.databinding.ActivityLoginBinding;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends BaseActivty {
    private Api api;
    private String TAG="RES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        UserLoginVM userLoginVM = new UserLoginVM(binding);
        binding.setUserlogin(userLoginVM);
        initNavBar(false, "登录", false);

    }

}
