package com.bs.knows.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;

import com.bs.knows.R;

import java.util.List;

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
    protected void initNavBar(boolean isShowBack, String title, boolean isShowMine) {
        ImageView mIvBack = fd(R.id.iv_back);
        ImageView mIvMine = fd(R.id.iv_mine);
        TextView mTvTitle = fd(R.id.tv_title);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mIvMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
        mTvTitle.setText(title);

        mIvBack.setOnClickListener(new View.OnClickListener() {
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