package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;

public class showDetailModel {


    private static String TAG = "show";


    public static void showGetPic(Context context,ActivityShowDetailBinding binding,Intent intent) {
        String path = intent.getStringExtra("picPaths");
        Log.d(TAG, "showGetPic: " + path);
        binding.tvShowDetail.setText(path);

        Glide.with(context)
                .load(path)
                .into(binding.ivShowDetail);
    }


    public String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);

    }
}
