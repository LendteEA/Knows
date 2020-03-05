package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bumptech.glide.Glide;

public class showDetailModel {

    private ActivityShowDetailBinding binding;
    private Intent mIntent;

    public showDetailModel(ActivityShowDetailBinding binding,Intent intent) {
        this.binding = binding;
        this.mIntent=intent;
    }

    public void showGetPic(Context context,Intent intent){
        String path=intent.getStringExtra("picPath");
        binding.tvShowPath.setText(path);
        Toast.makeText(context,"文件已保存在本地："+path,Toast.LENGTH_SHORT).show();
        Glide.with(context)
                .asBitmap()
                .load(path)
                .into(binding.ivShowDetail);
    }
}
