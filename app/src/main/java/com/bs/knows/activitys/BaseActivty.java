package com.bs.knows.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.bs.knows.R;

/**
 * 作为整个Activity的父类 描述所有Activity的共性
 */

@SuppressLint("Registered")
public class BaseActivty extends Activity {

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
}