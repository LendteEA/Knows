package com.bs.knows.utils;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context,Camera camera){
        super(context);
        //初始化Camera对象
        mCamera=camera;
        //得到SurfaceHolder对象
        mHolder=getHolder();
        //添加回调，得到Surface的三个声明周期方法
        mHolder.addCallback(this);

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            //设置预览方向
            mCamera.setDisplayOrientation(90);
            //显示在SurfaceView上面
            mCamera.setPreviewDisplay(holder);
            //开始预览
            mCamera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(holder.getSurface()==null){
            return;
        }
        //停止预览
        mCamera.stopPreview();
        //重新设置预览
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
