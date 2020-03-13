package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.ResponseResult;
import com.bs.knows.R;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.utils.ImageBean;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class showDetailModel {


    private static String TAG = "show";
    private static Context context;
    private boolean hasGotToken = false;
    private static ActivityShowDetailBinding binding;

    public showDetailModel(Context context, ActivityShowDetailBinding binding) {
        showDetailModel.context = context;
        showDetailModel.binding = binding;
    }

    public static void showGetPic(Intent intent) {
        String path = intent.getStringExtra("picPath");
        Log.d(TAG, "showGetPic: " + path);
        binding.tvShowDetail.setText(path);

        Glide.with(context)
                .load(path)
                .into(binding.ivShowDetail);
    }

    public void showpicPath(ActivityShowDetailBinding binding, Intent intent) {
        binding.tvShowDetail.setText(intent.getStringExtra("picPath"));

    }

    public String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);

    }


//    /**
//     * 通用文字识别接口（高精度含位置信息版）
//     * @param context   上下文
//     * @param filePath  图片文件路径
//     * @param ocrCallBack 请求回调
//     */
//    public static void recognizeAccurate(Context context,String filePath,final OCRCallBack<GeneralResult> ocrCallBack){
//        // 通用文字识别参数设置
//        GeneralParams param = new GeneralParams();
//        param.setDetectDirection(true);
//        param.setImageFile(new File(filePath));
//
//        // 调用通用文字识别服务
//        OCR.getInstance(context).recognizeAccurate(param, new OnResultListener<GeneralResult>() {
//            @Override
//            public void onResult(GeneralResult result) {
//                ocrCallBack.succeed(result);
//            }
//
//            @Override
//            public void onError(OCRError ocrError) {
//                ocrCallBack.failed(ocrError);
//            }
//        });
//    }
//
//    /**
//     * 从返回内容中提取识别出的信息
//     * @param result
//     * @return
//     */
//    public static String getResult(ResponseResult result){
//        String sb = result.getJsonRes();
//        return sb;
//    }
//
//
//    /**
//     * 图片识别统一回调接口
//     */
//    public interface OCRCallBack<T>{
//        void succeed(T data);
//        void failed(OCRError error);
//    }
//
//    /**
//     * 以license文件自定位置方式初始化token
//     */
//    private void initAccessTokenLicenseFile(final Context context) {
//        OCR.getInstance(context).initAccessToken(new OnResultListener<AccessToken>() {
//            @Override
//            public void onResult(AccessToken accessToken) {
//                String token = accessToken.getAccessToken();
//                hasGotToken = true;
//            }
//
//
//            @Override
//            public void onError(OCRError error) {
//                error.printStackTrace();
//                alertText(context, "自定义文件路径licence方式获取token失败", error.getMessage());
//            }
//        }, "aip.license", getApplicationContext());
//    }
//
//    /**
//     * 警示讯息，在初始化失败时呼叫
//     */
//    private void alertText(Context context, final String title, final String message) {
//
//
//    }
//
//    /**
//     * 检查token状态
//     */
//    private boolean checkTokenStatus() {
//        if (!hasGotToken) {
//            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
//        }
//        return hasGotToken;
//    }
//
//    /**
//     * 读取文件临时储存
//     */
//    public static class FileUtil {
//        public static File getSaveFile(Context context) {
//            File file = new File(context.getFilesDir(), "pic.jpg");
//            return file;
//        }
//    }
//
//    /**
//     * 识别结果显示
//     */
//    private void infoPopText(final String result) {
//
//        //显示结果以 JSON 格式呈现
//        binding.tvShowDetail.setText(result);
//    }


}
