package com.bs.knows.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.bs.knows.R;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.utils.GlideImageEngine;
import com.jph.takephoto.app.TakePhoto;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;


public class CameraModels {

    private TakePhoto takePhoto;
    private Uri imageUri;

//    public void showChosePhoto(Activity activity) {
//        // 进入相册 以下是例子：不需要的api可以不写
//        PictureSelector.create(activity)
//                .openGallery(ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
//                .maxSelectNum(1)// 最大图片选择数量
//                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//    }
//
//    public void showCamera(Activity activity) {
//        // 进入相册 以下是例子：不需要的api可以不写
//        PictureSelector.create(activity)
//                .openGallery(ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
//                .isCamera(true)// 是否显示拍照按钮
//                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
//                .compressSavePath(getCompressPath())//压缩图片自定义保存地址
//                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//    }



    public void getPic(Context context){
        takePhoto=getTakePhoto();
        imageUri=getImageCropUri();
        takePhoto.onPickFromCapture(imageUri);
        Intent intent=new Intent(context, ShowDetailActivity.class);
        intent.putExtra("picPath",imageUri);
        context.startActivity(intent);

    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    public TakePhoto getTakePhoto() {
        return takePhoto;
    }

    // 压缩后图片文件存储位置
//    private String getCompressPath() {
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PictureSelector/image/";
//        File file = new File(path);
//        if (file.mkdirs()) {
//            return path;
//        }
//        return path;
//    }


    /**
     //     * 从相册选图片
     //     *
     //     * @param view view
     //     */
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



}
