package com.bs.knows.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bs.knows.R;
import com.bs.knows.utils.UserUtils;

public class MineActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initView();
    }

    private void initView() {
        initNavBar(true,"我的",false);
        ImageView mBack = findViewById(R.id.iv_back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onChangeClick(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    public void onLogoutClick(View view) {

        UserUtils.logout(this);
    }
}
