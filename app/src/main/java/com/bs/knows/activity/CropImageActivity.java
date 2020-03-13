package com.bs.knows.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.ocr.sdk.model.AccessToken;
import com.bs.knows.R;
import com.bs.knows.utils.FileUtil;
import com.bs.knows.utils.RecoginzeService;
import com.bs.knows.utils.StaticUtils;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

public class CropImageActivity extends BaseActivty {
    private CropImageView cropImageView;
    private String AccessToken;
    private String TAG="crop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
//
//        Intent intent1=new Intent(this,ShowDetailActivity.class);
//        intent1.putExtra("picPathUri",cropped);

        initView();

    }

    private void initView() {
        initNavBar(this,true,"剪裁图片",false);

        cropImageView=findViewById(R.id.cp_ImageView);

        Log.d(TAG, "initView: "+getIntent().getData());

        cropImageView.setImageUriAsync(getIntent().getData());

//        AccessToken=RecoginzeService.initAccessTokenLicenseFile(this);

    }

    public void rotateImageRight(View view) {
        cropImageView.rotateImage(-90);
    }

    public void rotateImageLeft(View view){
        cropImageView.rotateImage(90);
    }

    public void getCropImages(View view) {
        Bitmap bitmap=cropImageView.getCroppedImage();
        Log.d(TAG, "initView Bitmap: "+bitmap);
        File filePath=FileUtil.savebitmap(this,bitmap, StaticUtils.IMAGE_NAME);

        RecoginzeService.recAccurateBasic(this, filePath, new RecoginzeService.ServiceListener() {
            @Override
            public void onResult(String result) {
                Intent intent=new Intent(CropImageActivity.this,ShowDetailActivity.class);
                intent.putExtra("picDetail",result);
                startActivity(intent);
            }
        });

//        intent.putExtra("picBitmap",bitmap);

    }
}
