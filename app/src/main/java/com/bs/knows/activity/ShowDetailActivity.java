package com.bs.knows.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.knows.R;
import com.bumptech.glide.Glide;

public class ShowDetailActivity extends BaseActivty {

    private TextView mTexyView;
    private ImageView mImageView,mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        initView();
    }

    private void initView() {
        initNavBar(true,"确认图片",false);
        mTexyView=findViewById(R.id.tv_showPath);
        mImageView=findViewById(R.id.iv_showDetail);
        mBack=findViewById(R.id.iv_back);
        getPic();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getPic(){
        String path=getIntent().getStringExtra("picPath");
        mTexyView.setText(path);
        Toast.makeText(this,"文件已保存在本地："+path,Toast.LENGTH_SHORT).show();
        Glide.with(this)
                .asBitmap()
                .load(path)

                .into(mImageView);
    }
}
