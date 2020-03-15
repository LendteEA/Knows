package com.bs.knows.viewmodel;

import android.content.Intent;
import android.view.SurfaceHolder;
import android.view.View;

import com.bs.knows.activity.MainActivity;
import com.bs.knows.databinding.ActivityCameraBinding;


public class CameraVM {

    private SurfaceHolder surfaceHolder;
    private ActivityCameraBinding binding;

    public CameraVM(ActivityCameraBinding binding, SurfaceHolder surfaceHolder) {
        this.binding = binding;
        this.surfaceHolder=surfaceHolder;


    }




    public void exitCamera(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class));
    }
}
