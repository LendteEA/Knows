package com.bs.knows.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.bs.knows.R;
import com.bs.knows.activity.CameraActivity;
import com.bs.knows.activity.MainActivity;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.utils.GlideImageEngine;
import com.bs.knows.viewmodel.CameraVM;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class CameraModel extends CameraActivity {
//
//
//    private Camera.PictureCallback mPictureCallback;
//
//    private static ActivityCameraBinding binding;
//
//    private static String TAG = "camera";
//
//    public CameraModel(ActivityCameraBinding binding) {
//        this.binding = binding;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    /**
//     * 拍照
//     *
//     * @param context view
//     */
//    public void getCaptures(final Context context,Camera mCamera) {
//
//        Camera.Parameters parameters = mCamera.getParameters();
//        Log.d(TAG, "getCapture: " + parameters);
//        parameters.setPictureFormat(ImageFormat.JPEG);
//        parameters.setPictureSize(1080, 960);
//        parameters.setPreviewSize(1080, 960);
//        Log.d(TAG, "getPic ");
//
//        parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);
//        final Camera finalMCamera = mCamera;
//        mCamera.autoFocus(new Camera.AutoFocusCallback() {
//            @Override
//            public void onAutoFocus(boolean success, Camera camera) {
//                if (success) {
//                    finalMCamera.takePicture(null, null, mPictureCallback);
//                    Log.d(TAG, "autoFocus：Success ");
//                }
//            }
//        });
//        Log.d(TAG, "go CallBack ");
//        mPictureCallback = new Camera.PictureCallback() {
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//                File tempFile = new File("/sdcard/temp.png");
//                try {
//                    FileOutputStream fos = new FileOutputStream(tempFile);
//                    fos.write(data);
//                    fos.close();
//
//                    Intent intent = new Intent(context, ShowDetailActivity.class);
//                    intent.putExtra("picPath", tempFile.getAbsolutePath());
//                    context.startActivity(intent);
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException er) {
//                    Log.d(TAG, "onPictureTaken: " + er);
//                    er.printStackTrace();
//                }
//            }
//        };
//
//    }
//
//    /**
//     * 从相册选图片
//     *
//     * @param view view
//     */
//    public void ChooserImg(View view) {
//        Context context = view.getContext();
//        Matisse.from((Activity) context)
//                .choose(MimeType.ofImage(), false)
//                .countable(true)
//                .maxSelectable(1) // 图片选择的最多数量
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                .thumbnailScale(0.85f) // 缩略图的比例
//                .imageEngine(new GlideImageEngine()) // 使用的图片加载引擎
//                .forResult(0x13); // 设置作为标记的请求码
//    }
//
//
//
//
//
//    /**
//     * 获取Camera对象
//     *
//     * @return Camera对象
//     */
//    public static Camera getCamera() {
//        Camera camera;
//        try {
//            camera = Camera.open();
//        } catch (Exception e) {
//            camera = null;
//            e.printStackTrace();
//        }
//        return camera;
//    }
//
//    /**
//     * view重新创建时获得camera
//     */
//    public static void whenResume(Camera mCamera,SurfaceHolder mSurfaceHolder) {
//        if (mCamera == null) {
//            mCamera = getCamera();
//            if (mSurfaceHolder != null) {
//
//                    CameraVM.setStartPreviews(mCamera,mSurfaceHolder);
//
//            }
//        }
//    }
//
//    /**
//     * 开始显示实时图像
//     */
//    public static void setStartPreview(Camera camera, SurfaceHolder holder) {
//        try {
//
//            Log.d(TAG, "setStartPreview: " + holder);
//            camera.setPreviewDisplay(holder);
//            //预览角度进行调整
//            camera.setDisplayOrientation(90);
//            camera.startPreview();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 释放相机资源
//     */
//    public static void releaseCamera(Camera camera) {
//        if (camera != null) {
//            camera.setPreviewCallback(null);
//            camera.stopPreview();
//            camera.release();
//        }
//
//    }

}
