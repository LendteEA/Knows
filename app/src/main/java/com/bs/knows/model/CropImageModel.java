package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.databinding.ActivityCropImageBinding;
import com.bs.knows.utils.FileUtil;
import com.bs.knows.utils.RecoginzeService;
import com.bs.knows.utils.StaticUtils;

import java.io.File;

public class CropImageModel {

    public static void initView(ActivityCropImageBinding binding, Intent intent){
        binding.cpImageView.setImageUriAsync(intent.getData());
    }

    public static void rotateImageRight(ActivityCropImageBinding binding) {
        binding.cpImageView.rotateImage(90);
    }
    public static void rotateImageLeft(ActivityCropImageBinding binding){
        binding.cpImageView.rotateImage(-90);
    }

    public static void getCrop(ActivityCropImageBinding binding, Context context){
        Bitmap bitmap=binding.cpImageView.getCroppedImage();
        File filePath= FileUtil.savebitmap(context,bitmap, StaticUtils.IMAGE_NAME);

        Intent intent=new Intent(context, ShowDetailActivity.class);
        intent.putExtra("picPaths",String.valueOf(filePath));
        context.startActivity(intent);

        RecoginzeService.recAccurateBasic(context, filePath, new RecoginzeService.ServiceListener() {
            @Override
            public void onResult(String result) {
//                Intent intent=new Intent(CropImageActivity.this,ShowDetailActivity.class);
//                intent.putExtra("picDetail",result);
//                startActivity(intent);
            }
        });
    }
}
