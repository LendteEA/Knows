package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.View;

import com.bs.knows.activity.MainActivity;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.model.CameraModels;

public class CameraVM {


//    private Camera camera;
    private static String TAG = "error";
    private Camera mCamera;
    private Uri mUri;
    private SurfaceHolder surfaceHolder;
    private ActivityCameraBinding binding;
    private CameraModels cameraModel = new CameraModels();

    public CameraVM(ActivityCameraBinding binding, SurfaceHolder surfaceHolder) {
        this.binding = binding;
        this.surfaceHolder=surfaceHolder;


    }




    public void exitCamera(View view) {

        view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class));
    }


    /**
     * 拍照
     * @param view
     */
    public void getCapture(View view) {
        Context context=view.getContext();
        cameraModel.getPic(context);


    }

    public void goPhotoalbum(View view){
        cameraModel.ChooseImg(view);

    }

}
