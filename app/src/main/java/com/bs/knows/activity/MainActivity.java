package com.bs.knows.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.connect.Api;
import com.bs.knows.databinding.ActivityMainBinding;
import com.bs.knows.viewmodel.MainActivityVM;

import pub.devrel.easypermissions.EasyPermissions;


//从父类BaseActivity继承一些共性的东西
public class MainActivity extends BaseActivty {
    private Api api;
    private String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityVM mainActivityVM = new MainActivityVM(this, binding);
        binding.setMainActivity(mainActivityVM);
        initView();
    }

    private void initView() {
        initNavBar(false, "Knows", true);
        MainActivityVM.InitList();

        EasyPermissions.requestPermissions(this,
                "申请权限",
                0,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        ImageView mine = findViewById(R.id.iv_mine);
        MainActivityVM.MineActivity(mine);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }
}
