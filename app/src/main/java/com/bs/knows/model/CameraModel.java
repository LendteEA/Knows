package com.bs.knows.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;


import com.bs.knows.activity.CameraActivity;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.utils.GlideImageEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SuppressLint("Registered")
public class CameraModel extends CameraActivity implements SurfaceHolder.Callback {

    private Camera mCamera;
    private Camera.PictureCallback mPictureCallback;

    private ActivityCameraBinding binding;
    private SurfaceHolder mSurfaceHolder;
    private static String TAG = "errors";
    private CameraActivity cameraActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSurfaceHolder = binding.svPreview.getHolder();
        mSurfaceHolder.addCallback(this);
    }



    public CameraModel(ActivityCameraBinding binding, Camera mCamera, SurfaceHolder surfaceHolder) {
        this.binding = binding;
        this.mCamera = mCamera;
    }

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



    public void getCaptures(final Context context) {

        Log.d(TAG, "getCapture: " + mCamera);


        Log.d(TAG, "getCapture: " + mSurfaceHolder);
        Camera.Parameters parameters = mCamera.getParameters();
        Log.d(TAG, "getCapture: " + parameters);
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPictureSize(1080, 960);
        parameters.setPreviewSize(1080, 960);
        Log.d(TAG, "getPic ");

        parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });

        mPictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                File tempFile = new File("/sdcard/temp.png");
                try {
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    fos.write(data);
                    fos.close();

                    Intent intent = new Intent(context, ShowDetailActivity.class);
                    intent.putExtra("picPath", tempFile.getAbsolutePath());
                    context.startActivity(intent);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException er) {
                    Log.d(TAG, "onPictureTaken: " + er);
                    er.printStackTrace();
                }
            }
        };

    }


    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: " + mCamera);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            Log.d("TAGs", "requestCode: " + requestCode + "   resultCode:" + resultCode);
        }
    }


    /**
     * 获取Camera对象
     *
     * @return Camera对象
     */
    public Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }


    /**
     * 开始显示实时图像
     */
    public void setStartPreview(Camera camera, SurfaceHolder holder) throws IOException {
        try {
            if (holder == null) {
                holder = mSurfaceHolder;
            }
            Log.d(TAG, "setStartPreview: " + holder);
            camera.setPreviewDisplay(holder);
            //预览角度进行调整
            camera.setDisplayOrientation(90);
            camera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放相机资源
     */
    public void releaseCamera() {
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
