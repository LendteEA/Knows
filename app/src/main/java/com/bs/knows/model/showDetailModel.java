package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.ResponseResult;
import com.bs.knows.R;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.utils.ImageBean;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static cn.bmob.v3.Bmob.getApplicationContext;

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
