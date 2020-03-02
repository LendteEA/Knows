package com.bs.knows.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bs.knows.R;
import com.bs.knows.utils.UserUtils;
import com.bs.knows.views.InputView;

public class ChangePasswordActivity extends BaseActivty {

    private InputView old_password,new_password,new_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwd);

        initView();

    }

    private void initView() {
        initNavBar(true,"修改密码",false);
        old_password=findViewById(R.id.input_password_old);
        new_password=findViewById(R.id.input_password_new);
        new_password_confirm=findViewById(R.id.input_password_confirm_new);

    }

    public void onRegisterClick(View view) {
        String old_passwords=old_password.getInputStr().trim();
        String new_passwords=new_password.getInputStr().trim();
        String new_passwords_confirm=new_password_confirm.getInputStr().trim();

        UserUtils.updatePassword(this,old_passwords,new_passwords,new_passwords_confirm);
        finish();
    }
}
