package com.bs.knows.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.bs.knows.R;
import com.bs.knows.activity.MainActivity;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.UploadNewPic;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.databinding.ActivityCropImageBinding;
import com.bs.knows.utils.FileUtil;
import com.bs.knows.utils.RecoginzeService;
import com.bs.knows.utils.StaticUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Response;

public class CropImageModel {

    private static String TAG="TAG";
    public static void initView(ActivityCropImageBinding binding, Intent intent){
        binding.cpImageView.setImageUriAsync(intent.getData());
    }

    public static void rotateImageRight(ActivityCropImageBinding binding) {
        binding.cpImageView.rotateImage(90);
    }
    public static void rotateImageLeft(ActivityCropImageBinding binding){
        binding.cpImageView.rotateImage(-90);
    }

    public static void getCrop(ActivityCropImageBinding binding, Context context){
        Bitmap bitmap=binding.cpImageView.getCroppedImage();
        File filePath= FileUtil.savebitmap(context,bitmap, StaticUtils.IMAGE_NAME);
        RequestBody fileBody=RequestBody.create(MediaType.parse("image/*"),filePath);
        Log.d(TAG, "getCrop: "+filePath);
        Api api=initRetrofit.getUserData().create(Api.class);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody bodys = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("username", UserUtilsModel.UserIsLogin.getInstance().getPhone())
                .addFormDataPart("file", String.valueOf(filePath),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(String.valueOf(filePath))))
                .build();

        Request request = new Request.Builder()
                .url(StaticUtils.BASE_URL+"/app.php?action=uploadnewpic")
                .method("POST", bodys)
                .build();


      client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFail: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response.body());

            }
        });


//        // MultipartBody.Part  和后端约定好Key，这里的partName是用file
//        MultipartBody.Part file = MultipartBody.Part.createFormData("file", filePath.getName(), requestFile);
//        Log.d(TAG, "getCrop: "+file);
//        // 添加描述
//        String descriptionString = UserUtilsModel.UserIsLogin.getInstance().getPhone();
//        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
//        Log.d(TAG, "getCrop: "+descriptionString);
//        // 执行请求
//       api.Uploadnewpic(description,file).enqueue(new Callback<UploadNewPic>() {
//           @Override
//           public void onResponse(Call<UploadNewPic> call, Response<UploadNewPic> response) {
//               Log.d(TAG, "onResponse: "+response.body().isUploadpic());
//           }
//
//           @Override
//           public void onFailure(Call<UploadNewPic> call, Throwable t) {
//               Log.d(TAG, "onFail: "+t.getMessage());
//           }
//       });




        AlertDialog alertDialog1 = new AlertDialog.Builder(context)
                .setTitle("图片上传成功")//标题
                .setMessage("图片已上传，识别完成后可以在识别历史中找到识别结果")//内容
                .setIcon(R.drawable.ic_saomiao)//图标
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(context, MainActivity.class);
//                        intent.putExtra("picPaths",String.valueOf(filePath));
                        context.startActivity(intent);
                    }
                })
                .create();
        alertDialog1.show();


        RecoginzeService.recAccurateBasic(context, filePath, new RecoginzeService.ServiceListener() {
            @Override
            public void onResult(String result) {
//                Intent intent=new Intent(CropImageActivity.this,ShowDetailActivity.class);
//                intent.putExtra("picDetail",result);
//                startActivity(intent);
            }
        });
    }
}
