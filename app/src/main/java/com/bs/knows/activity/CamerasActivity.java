package com.bs.knows.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.camera.BitmapUtils;
import com.bs.knows.camera.CameraUtils;
import com.bs.knows.databinding.ActivityCameraBinding;

import com.bs.knows.utils.GlideImageEngine;

import com.bs.knows.utils.StaticUtils;
import com.bs.knows.viewmodel.CameraVM;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class CamerasActivity extends BaseActivty implements SurfaceHolder.Callback {

    private Camera mCamera;
    private CameraUtils cameraInstance;
    private int screenWidth;
    private int screenHeight;

    private SurfaceHolder mSurfaceHolder;
    private ActivityCameraBinding binding;

    private static String TAG = "error";
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            @SuppressLint("SdCardPath")
            File filepath = new File(StaticUtils.FILE_PATH);
            File tempFile = new File(filepath + "/Knows" + System.currentTimeMillis() + ".jpeg");
            if (!filepath.exists()) {
                try {
                    //按照指定的路径创建文件夹
                    filepath.mkdirs();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);
            Bitmap saveBitmap=cameraInstance.setTakePicktrueOrientation(0,bitmap);
            saveBitmap=Bitmap.createScaledBitmap(saveBitmap,screenWidth,screenHeight,true);

            BitmapUtils.saveJPGE_After(getApplicationContext(),saveBitmap, String.valueOf(tempFile),100);
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            if (!saveBitmap.isRecycled()) {
                saveBitmap.recycle();
            }
            Intent intent = new Intent(CamerasActivity.this, CropImageActivity.class);
            intent.setData(Uri.fromFile(tempFile.getAbsoluteFile()));
            startActivity(intent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_camera);
        CameraVM cameraVM=new CameraVM(binding,mSurfaceHolder);
        binding.setCamera(cameraVM);

        initView();
    }

    private void initView() {
        SurfaceView mSurfaceView = binding.svPreview;
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

        //camera工具类初始化
        cameraInstance=CameraUtils.getInstance();
        DisplayMetrics dm=getResources().getDisplayMetrics();
        screenWidth=dm.widthPixels;
        screenHeight=dm.heightPixels;


        EasyPermissions.requestPermissions(this,
                "申请权限",
                0,
                Manifest.permission.CAMERA);

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
            int PreviewWidth = 0;
            int PreviewHeight = 0;
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);//获取窗口的管理器
//            Display display = wm.getDefaultDisplay();//获得窗口里面的屏幕
            Camera.Parameters parameters  = mCamera.getParameters();
            // 选择合适的预览尺寸
            List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();

            // 如果sizeList只有一个我们也没有必要做什么了，因为就他一个别无选择
            if (sizeList.size() > 1) {
                Iterator<Camera.Size> itor = sizeList.iterator();
                while (itor.hasNext()) {
                    Camera.Size cur = itor.next();
                    if (cur.width >= PreviewWidth
                            && cur.height >= PreviewHeight) {
                        PreviewWidth = cur.width;
                        PreviewHeight = cur.height;
                        break;
                    }
                }
            }
            parameters.setPreviewSize(PreviewWidth, PreviewHeight); //获得摄像区域的大小
            parameters.setPreviewFrameRate(60);//每秒60帧  每秒从摄像头里面获得60个画面
            parameters.setPictureFormat(ImageFormat.JPEG);//设置照片输出的格式
            parameters.set("jpeg-quality", 100);//设置照片质量
            parameters.setPictureSize(PreviewWidth, PreviewHeight);//设置拍出来的屏幕大小
            parameters.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);
//            parameters.setRotation(90);
            Log.d(TAG, "setStartPreview: " + holder);
            camera.setParameters(parameters);
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
