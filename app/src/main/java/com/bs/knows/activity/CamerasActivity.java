package com.bs.knows.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.bs.knows.R;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.utils.CameraPreview;
import com.bs.knows.utils.FileUtil;
import com.bs.knows.utils.GlideImageEngine;

import com.bs.knows.utils.ImageUtils;
import com.bs.knows.viewmodel.CameraVM;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class CamerasActivity extends BaseActivty implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ActivityCameraBinding binding;

    private static String TAG = "error";
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File filepath = new File("/sdcard/DCIM/Knows");
            File tempFile = new File(filepath + "/Knows" + System.currentTimeMillis() + ".jpg");
            if (!filepath.exists()) {
                try {
                    //按照指定的路径创建文件夹
                    filepath.mkdirs();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            try {

                Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
                Bitmap saveBitmap;


                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(data);
                fos.close();
                //图片旋转
                ImageUtils.setPictureDegreeZero(String.valueOf(tempFile));

                Intent intent = new Intent(CamerasActivity.this, CropImageActivity.class);
                intent.setData(Uri.fromFile(tempFile.getAbsoluteFile()));
                startActivity(intent);
                CamerasActivity.this.finish();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException er) {
                Log.d(TAG, "onPictureTaken: " + er);
                er.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);
        CameraVM cameraVM = new CameraVM(binding, mSurfaceHolder);
        binding.setCamera(cameraVM);

        initView();
    }

    private void initView() {
        mSurfaceView = binding.svPreview;
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

        EasyPermissions.requestPermissions(this,
                "申请权限",
                0,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void cameraFocus(View view) {
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                Log.d(TAG, "onAutoFocus: ");
            }
        });
    }

    public void getCapture(final View view) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPictureSize(1080, 1920);
        parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);

        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });
    }

    public void ChooserImg(View view) {
        Matisse.from((Activity) view.getContext())
                .choose(MimeType.ofImage(), false)
                //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                .showSingleMediaType(true)
                //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
//                .capture(true)
                .countable(true)
                .maxSelectable(1) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideImageEngine()) // 使用的图片加载引擎
                .forResult(0x13); // 设置作为标记的请求码

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 19 && resultCode == RESULT_OK) {
            Uri path = Matisse.obtainResult(data).get(0);
            Intent intent = new Intent(this, CropImageActivity.class);

            intent.setData(Matisse.obtainResult(data).get(0));
            startActivity(intent);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
            if (mSurfaceHolder != null) {
                try {
                    setStartPreview(mCamera, mSurfaceHolder);
                } catch (IOException e) {
                    Log.d(TAG, "onResume: " + e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取Camera对象
     *
     * @return Camera对象
     */
    private Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open(0);
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    /**
     * 开始显示实时图像
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) throws IOException {
        if (holder == null) {
            holder = mSurfaceHolder;
        }
        try {
            Log.d(TAG, "setStartPreview: " + holder);
            camera.setPreviewDisplay(holder);
            //预览角度进行调整
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CameraPreview cameraPreview=new CameraPreview(this,camera);

    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            setStartPreview(mCamera, holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        try {
            setStartPreview(mCamera, holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }


}