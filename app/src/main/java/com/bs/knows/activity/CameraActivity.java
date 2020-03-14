package com.bs.knows.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.camera.AppConstant;
import com.bs.knows.camera.BitmapUtils;
import com.bs.knows.databinding.ActivityCameraBinding;
import com.bs.knows.model.CameraModels;
import com.bs.knows.utils.AskPermission;
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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CameraActivity extends BaseActivty {

    private TextureView textureView;
    private CameraDevice mCameraDevice;
    private static final String TAG="error";
    /**
     * 摄像头id（0代表后置摄像头，1代表前置摄像头）
     */
    private String mCameraId = "0";
    private ImageReader imageReader;
    public static int height;
    public static int width;
    private Size previewSize;
    private CaptureRequest.Builder captureRequestBuilder;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mPreviewSession;
    /**
     * 判断是否有拍照权限的标识码
     */
    private final int RESULT_CODE_CAMERA = 1;
    private CaptureRequest.Builder mCaptureRequestBuilder;

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        initView();

    }

    private void initView() {
        textureView=findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(surfaceTextureListener);


    }





    /**
     * 启动拍照
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startCamera() {
        if (textureView.isAvailable()) {
            if (mCameraDevice == null) {
                openCameras();
            }
        } else {
            textureView.setSurfaceTextureListener(surfaceTextureListener);
        }
    }

    /**
     * 拍照
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void takePicture(View view) {
        try {
            if (mCameraDevice == null) {
                return;
            }
            // 创建拍照请求
            captureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            // 设置自动对焦模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 将imageReader的surface设为目标
            captureRequestBuilder.addTarget(imageReader.getSurface());
            // 获取设备方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            // 根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION
                    , ORIENTATIONS.get(rotation));
            // 停止连续取景
            Log.d(TAG, "takePicture: "+mPreviewSession);
            mPreviewSession.stopRepeating();
            //拍照
            CaptureRequest captureRequest = captureRequestBuilder.build();
            //设置拍照监听
            mPreviewSession.capture(captureRequest, captureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听拍照结果
     */
    private CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
        // 拍照成功
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
//            // 重设自动对焦模式
//            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
//            // 设置自动曝光模式
//            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
//            try {
//                //重新进行预览
//                mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            super.onCaptureFailed(session, request, failure);
        }
    };

    /**
     * TextureView的监听
     */
    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //相机可用时，设置参数并打开相机
            CameraActivity.width = width;
            CameraActivity.height = height;
            openCameras();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            stopCamera();
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };


    /**
     * 打开摄像头
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCameras() {
        // 判断相机权限 6.0 以上的动态权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            EasyPermissions.requestPermissions(this,
                    "申请权限",
                    0,
                    Manifest.permission.CAMERA);
            return;
        }

        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        //设置摄像头特性
        setCameraCharacteristics(manager);
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                String[] perms = {"android.permission.CAMERA"};
                ActivityCompat.requestPermissions(CameraActivity.this, perms, RESULT_CODE_CAMERA);
            } else {
                manager.openCamera(mCameraId, stateCallback, null);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置摄像头的参数
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setCameraCharacteristics(CameraManager manager) {
        try {
            // 获取指定摄像头的特性
            CameraCharacteristics characteristics
                    = manager.getCameraCharacteristics(mCameraId);
            // 获取摄像头支持的配置属性
            StreamConfigurationMap map = characteristics.get(
                    CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            // 获取摄像头支持的最大尺寸
            Size largest = Collections.max(
                    Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new CompareSizesByArea());
            // 创建一个ImageReader对象，用于获取摄像头的图像数据
            imageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(),
                    ImageFormat.JPEG, 2);
            //设置获取图片的监听
            imageReader.setOnImageAvailableListener(imageAvailableListener, null);
            // 获取最佳的预览尺寸
            previewSize = chooseOptimalSize(map.getOutputSizes(
                    SurfaceTexture.class), width, height, largest);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        }
    }

    /**
     * 为Size定义一个比较器Comparator
     */
    static class CompareSizesByArea implements Comparator<Size> {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public int compare(Size lhs, Size rhs) {
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static Size chooseOptimalSize(Size[] choices
            , int width, int height, Size aspectRatio) {
        // 收集摄像头支持的大过预览Surface的分辨率
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else {
            //没有合适的预览尺寸
            return choices[0];
        }
    }

    /**
     * 摄像头状态的监听
     */
    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        // 摄像头被打开时触发该方法
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onOpened(CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            // 开始预览
            takePreview();
        }

        // 摄像头断开连接时触发该方法
        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            stopCamera();
            Log.d(TAG, "onDisconnected: "+cameraDevice);
        }

        // 打开摄像头出现错误时触发该方法
        @Override
        public void onError(CameraDevice cameraDevice, int error) {
            stopCamera();
            Log.d(TAG, "onError: "+error);
        }
    };

    /**
     * 开始预览
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void takePreview() {
        SurfaceTexture mSurfaceTexture = textureView.getSurfaceTexture();
        //设置TextureView的缓冲区大小
        mSurfaceTexture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
        //获取Surface显示预览数据
        Surface mSurface = new Surface(mSurfaceTexture);
        try {
            //创建预览请求
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            // 设置自动对焦模式
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            //设置Surface作为预览数据的显示界面
            mCaptureRequestBuilder.addTarget(mSurface);
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            mCameraDevice.createCaptureSession(Arrays.asList(mSurface, imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        //开始预览
                        mCaptureRequest = mCaptureRequestBuilder.build();
                        mPreviewSession = session;
                        Log.d(TAG, "onConfigured: "+session);
                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                        mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放
     */
    private void stopCamera() {
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    /**
     * 监听拍照的图片
     */
    private ImageReader.OnImageAvailableListener imageAvailableListener = new ImageReader.OnImageAvailableListener() {
        // 当照片数据可用时激发该方法
        @Override
        public void onImageAvailable(ImageReader reader) {
            //先验证手机是否有sdcard
            String status = Environment.getExternalStorageState();
            if (!status.equals(Environment.MEDIA_MOUNTED)) {
                Toast.makeText(getApplicationContext(), "你的sd卡不可用。", Toast.LENGTH_SHORT).show();
                return;
            }
            Image image = reader.acquireNextImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            String filePath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" +
                    System.currentTimeMillis() + ".jpg";
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            BitmapUtils.saveJPGE_After(getApplicationContext(), bitmap, filePath, 100);
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            Intent intent = new Intent();
            intent.putExtra(AppConstant.KEY.IMG_PATH, filePath);
            intent.putExtra(AppConstant.KEY.PIC_WIDTH, width);
            intent.putExtra(AppConstant.KEY.PIC_HEIGHT, height);
            setResult(AppConstant.RESULT_CODE.RESULT_OK, intent);
            finish();
        }

    };

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraDevice != null) {
            stopCamera();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
        if(textureView.isAvailable()){
            openCameras();
        }
    }



}
