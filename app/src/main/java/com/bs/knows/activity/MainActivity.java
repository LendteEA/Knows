package com.bs.knows.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;


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
    private long exitTime = 0;

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
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        ImageView mine = findViewById(R.id.iv_mine);

        MainActivityVM.MineActivity(mine);


    }

    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            MainActivityVM.InitList();
            handler.postDelayed(this,20000);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,20000);
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    /**
     * 按两次返回键退出
     *
     * @param keyCode 获取按键id
     * @param event   按键事件
     * @return 返回是否按下按键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {

        if ((System.currentTimeMillis() - exitTime) > 3000) {
            Toast.makeText(this,
                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
