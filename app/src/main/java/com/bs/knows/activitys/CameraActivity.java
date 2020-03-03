package com.bs.knows.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bs.knows.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends BaseActivty implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private static String TAG="error";
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File tempFile = new File("/sdcard/temp.png");
            try {
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(data);
                fos.close();

                Intent intent=new Intent(CameraActivity.this,ShowDetailActivity.class);
                intent.putExtra("picPath",tempFile.getAbsolutePath());
                startActivity(intent);
                CameraActivity.this.finish();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException er) {
                Log.d(TAG, "onPictureTaken: "+er);
                er.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
    }

    private void initView() {
//        initNavBar(true, "扫描", false);
        mSurfaceView = findViewById(R.id.sv_preview);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);


    }


    public void ExitCamera(View view) {
        onBackPressed();
    }

    public void getCapture(View view) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPreviewSize(1080, 1920);
        parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });

//        //打开系统相册
//
//            Intent intent =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, 6);

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
                    Log.d(TAG, "onResume: "+e);
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
           camera=Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }


    /**
     * 开始显示实时图像
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) throws IOException{
        try {
            if(holder==null){
                holder=mSurfaceHolder;
            }
            Log.d(TAG, "setStartPreview: "+holder);
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
