package com.bs.knows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityMineBinding;
import com.bs.knows.viewmodel.UserMineVM;

public class MineActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        ActivityMineBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_mine);
        UserMineVM userMineVM =new UserMineVM(binding);
        binding.setUserDetail(userMineVM);

        initView();
    }

    private void initView() {
        initNavBar(this,true, "我的", false);
        ImageView mBack = findViewById(R.id.iv_back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
