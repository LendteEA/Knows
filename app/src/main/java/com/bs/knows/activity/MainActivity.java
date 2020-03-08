package com.bs.knows.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.adapters.HistoryListAdapter;
import com.bs.knows.adapters.funcationAdapter;
import com.bs.knows.views.CridSpaceItemDecoration;

import pub.devrel.easypermissions.EasyPermissions;


//从父类BaseActivity继承一些共性的东西
public class MainActivity extends BaseActivty {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initNavBar(this,false, "Knows", true);

        //        PermissionUtils.Permissionx(this);

        EasyPermissions.requestPermissions(this,
                "申请权限",
                0,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        RecyclerView mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 2));
        mRvGrid.addItemDecoration(new CridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false);
        funcationAdapter mFunction = new funcationAdapter(this);
        mRvGrid.setAdapter(mFunction);

        /**
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */
        RecyclerView mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        HistoryListAdapter mListAdapter = new HistoryListAdapter(this);
        mRvList.setAdapter(mListAdapter);



        ImageView mMine = fd(R.id.iv_mine);
        mMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MineActivity.class));
            }
        });


    }

}
