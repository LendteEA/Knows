package com.bs.knows.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import com.bs.knows.R;
import com.bs.knows.activity.CameraActivity;
import com.bs.knows.activity.MainActivity;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.utils.CameraRequest;
import com.bs.knows.utils.GlideImageEngine;
import com.jph.takephoto.app.TakePhoto;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;

public class CameraModels extends CameraActivity{

    private TakePhoto takePhoto;
    private Uri imageUri;
    private String TAG="shows";


    public void getPic(Context context){
        takePhoto=getTakePhoto();
        File tempFile = new File("/sdcard/Knows/Knows"+ System.currentTimeMillis()+".jpg");
        takePhoto.onPickFromCapture(Uri.fromFile(tempFile));
        Intent intent=new Intent(context, ShowDetailActivity.class);
        intent.putExtra("picPath",imageUri);
        context.startActivity(intent);

    }

    private TakePhoto getTakePhoto() {
        return takePhoto;
    }


    /**
     //     * 从相册选图片
     //     *
     //     * @param view view
     //     */


    public void ChooserImg(View view) {
        Context context = view.getContext();
        Matisse.from((Activity) context)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .maxSelectable(1) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideImageEngine()) // 使用的图片加载引擎
                .forResult(0x13); // 设置作为标记的请求码
    }

    //剪裁







}
