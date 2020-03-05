package com.bs.knows.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.NavBarBinding;

/**
 * 作为整个Activity的父类 描述所有Activity的共性
 */

@SuppressLint("Registered")
public class BaseActivty extends Activity {



    private long exitTime=0;
    /**
     * 返回一个继承自view的实体 findviewbyid的封装方法
     *
     * @param id    id
     * @param <T>   T
     * @return      findViewById
     */
    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }


    /**
     * 初始化NavBar
     *
     * @param isShowBack        是否显示返回键
     * @param title             页面名称
     * @param isShowMine        是否显示我的
     */
    protected void initNavBar(Activity activity,boolean isShowBack, String title, boolean isShowMine) {
//        NavBarBinding barBinding=DataBindingUtil.setContentView(activity,R.layout.nav_bar);
//        barBinding.ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
//        barBinding.ivMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
//        barBinding.tvTitle.setText(title);
//
//        barBinding.ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        ImageView ivBack=findViewById(R.id.iv_back);
        ImageView ivMine=findViewById(R.id.iv_mine);
        TextView tvTitle=findViewById(R.id.tv_title);

        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
        tvTitle.setText(title);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }



    /**
     * 按两次返回键退出
     * @param keyCode   获取按键id
     * @param event     按键事件
     * @return          返回是否按下按键
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

        if ((System.currentTimeMillis()-exitTime) > 3000) {
            Toast.makeText(this,
                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }




}