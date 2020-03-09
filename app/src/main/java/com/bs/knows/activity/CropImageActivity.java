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

import com.bs.knows.R;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CropImageActivity extends BaseActivty {
    private CropImageView cropImageView;
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

    }

    public void getCropImage(View view) {
        Bitmap bitmap=cropImageView.getCroppedImage();
        Log.d(TAG, "initView Bitmap: "+bitmap);
        Intent intent=new Intent(this,ShowDetailActivity.class);
        intent.putExtra("picBitmap",bitmap);
    }

    public void rotateImageRight(View view) {
        cropImageView.rotateImage(-90);
    }

    public void rotateImageLeft(View view){
        cropImageView.rotateImage(90);
    }
}
