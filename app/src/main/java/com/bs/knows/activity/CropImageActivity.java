package com.bs.knows.activity;


import android.content.Intent;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityCropImageBinding;
import com.bs.knows.viewmodel.CropImageVM;

public class CropImageActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        Intent intent=getIntent();
        ActivityCropImageBinding cropImageBinding= DataBindingUtil.setContentView(this,R.layout.activity_crop_image);
        CropImageVM cropImageVM=new CropImageVM(this,cropImageBinding,intent);
        cropImageBinding.setCropimage(cropImageVM);
        initNavBar(true,"剪裁图片",false);
        CropImageVM.initView();

    }

}
