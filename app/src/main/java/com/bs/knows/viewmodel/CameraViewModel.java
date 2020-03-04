package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.R;
import com.bs.knows.activity.CameraActivity;
import com.bs.knows.activity.MainActivity;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.model.CameraModel;
import com.bs.knows.utils.GlideImageEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

public class CameraViewModel  {


    private Camera camera;
    private static String TAG = "error";
    private SurfaceHolder surfaceHolder;
    private ActivityCameraBinding binding;

    public CameraViewModel(ActivityCameraBinding binding, Camera camera, SurfaceHolder surfaceHolder) {
        this.binding = binding;
        this.camera = camera;
        this.surfaceHolder=surfaceHolder;
    }

    public void getCapture(View view) {
        Context context = view.getContext();
//        Log.d(TAG, "getCapture: " + camera);
        CameraModel cameraModel = new CameraModel(binding, camera,surfaceHolder);
//        Log.d(TAG, "getCapture: " + cameraModel);
//        cameraModel.getCaptures(context);
//        Log.d(TAG, "context" );
      cameraModel.ChooserImg(view);
    }

    public void exitCamera(View view) {
        Context context = view.getContext();
        context.startActivity(new Intent(context, MainActivity.class));
    }



}
