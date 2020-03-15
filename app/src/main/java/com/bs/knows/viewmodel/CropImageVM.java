package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.bs.knows.databinding.ActivityCropImageBinding;
import com.bs.knows.model.CropImageModel;

public class CropImageVM {
    private Context mContext;
    private static Intent intent;
    private static ActivityCropImageBinding mBinding;

    public CropImageVM(Context mContext, ActivityCropImageBinding mBinding, Intent intent) {
        this.mContext = mContext;
        CropImageVM.mBinding = mBinding;
        CropImageVM.intent =intent;
    }

    public static void initView(){
        CropImageModel.initView(mBinding,intent);
    }

    public void rotateRight(View view){
        CropImageModel.rotateImageRight(mBinding);
    }
    public void rotateLeft(View view){
        CropImageModel.rotateImageLeft(mBinding);
    }
    public void getCrop(View view){
        CropImageModel.getCrop(mBinding,mContext);
    }
}
