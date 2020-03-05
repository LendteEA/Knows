package com.bs.knows.activity;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.model.CameraModels;
import com.bs.knows.utils.PermissionUtils;
import com.bs.knows.viewmodel.CameraVM;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CameraActivity extends BaseActivty {

    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ActivityCameraBinding binding;
    private static String TAG = "errorcamera";

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);
        mSurfaceView=binding.svPreview;
        mSurfaceHolder=mSurfaceView.getHolder();
       CameraVM cameraVM=new CameraVM(binding,mSurfaceHolder);
        binding.setCamera(cameraVM);


        initView();

    }

    private void initView() {
        PermissionUtils.Permissionx(this);
        //        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
