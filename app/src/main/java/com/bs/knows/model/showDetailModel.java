package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.utils.ImageBean;
import com.bumptech.glide.Glide;

public class showDetailModel {


    private static String TAG="show";


    public static void showGetPic(ActivityShowDetailBinding binding, Context context, Intent intent){
        String path=intent.getStringExtra("picPath");
        Log.d(TAG, "showGetPic: "+path);
        binding.tvShowPath.setText(path);

        Glide.with(context)
                .load(path)
                .into(binding.ivShowDetail);
    }

    public void showpicPath(ActivityShowDetailBinding binding,Intent intent){
        binding.tvShowPath.setText(intent.getStringExtra("picPath"));

    }


}
