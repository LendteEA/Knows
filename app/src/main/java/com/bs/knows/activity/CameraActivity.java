package com.bs.knows.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.model.CameraModel;
import com.bs.knows.viewmodel.CameraViewModel;
import com.zhihu.matisse.Matisse;

import java.io.IOException;
import java.util.List;

public class CameraActivity extends BaseActivty {

    private SurfaceHolder mSurfaceHolder;
    private static String TAG = "errorcamera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.bs.knows.databinding.ActivityCameraBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);
        mSurfaceHolder = binding.svPreview.getHolder();
        CameraViewModel cameraViewModel = new CameraViewModel(binding, getmCamera(), mSurfaceHolder);
        binding.setCamera(cameraViewModel);

        //        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        initView();

    }

    private Camera getmCamera() {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                String path = Matisse.obtainPathResult(data).get(0);
                if (path != null) {
                    Log.d("TAGs", "requestCode: " + requestCode + "   resultCode:" + resultCode);
                    Intent intent = new Intent(this, ShowDetailActivity.class);

                    intent.putExtra("picPath", path);
                    startActivity(intent);
                }
            }
        }

    }

//        private void initView() {
//
//
//
//
//
//
//    }
//
//
//    public void ExitCamera(View view) {
//        onBackPressed();
//    }
//
////    public void getCapture(View view) {
////        Camera.Parameters parameters = mCamera.getParameters();
////        parameters.setPictureFormat(ImageFormat.JPEG);
////        parameters.setPictureSize(1080,960);
////        parameters.setPreviewSize(1080, 960);
////        parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);
////        mCamera.autoFocus(new Camera.AutoFocusCallback() {
////            @Override
////            public void onAutoFocus(boolean success, Camera camera) {
////                if (success) {
////                    mCamera.takePicture(null, null, mPictureCallback);
////                }
////            }
////        });
////        mPictureCallback = new Camera.PictureCallback() {
////            @Override
////            public void onPictureTaken(byte[] data, Camera camera) {
////                File tempFile = new File("/sdcard/temp.png");
////                try {
////                    FileOutputStream fos = new FileOutputStream(tempFile);
////                    fos.write(data);
////                    fos.close();
////
////                    Intent intent=new Intent(CameraActivity.this,ShowDetailActivity.class);
////                    intent.putExtra("picPath",tempFile.getAbsolutePath());
////                    startActivity(intent);
////                    CameraActivity.this.finish();
////
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                } catch (IOException er) {
////                    Log.d(TAG, "onPictureTaken: "+er);
////                    er.printStackTrace();
////                }
////            }
////        };
////
//////        //打开系统相册
//////
//////            Intent intent =new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//////            startActivityForResult(intent, 6);
//
////    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        releaseCamera();
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mCamera == null) {
//            mCamera = getCamera();
//            if (mSurfaceHolder != null) {
//                try {
//                    setStartPreview(mCamera, mSurfaceHolder);
//                } catch (IOException e) {
//                    Log.d(TAG, "onResume: "+e);
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 获取Camera对象
//     *
//     * @return Camera对象
//     */
//    private Camera getCamera() {
//        Camera camera;
//        try {
//           camera=Camera.open();
//        } catch (Exception e) {
//            camera = null;
//            e.printStackTrace();
//        }
//        return camera;
//    }
//
//
//    /**
//     * 开始显示实时图像
//     */
//    private void setStartPreview(Camera camera, SurfaceHolder holder) throws IOException{
//        try {
//            if(holder==null){
//                holder=mSurfaceHolder;
//            }
//            Log.d(TAG, "setStartPreview: "+holder);
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
//    private void releaseCamera() {
//        if (mCamera != null) {
//            mCamera.setPreviewCallback(null);
//            mCamera.stopPreview();
//            mCamera.release();
//            mCamera = null;
//        }
//
//    }
//
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//            setStartPreview(mCamera, holder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        mCamera.stopPreview();
//        try {
//            setStartPreview(mCamera, holder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        releaseCamera();
//    }


}
