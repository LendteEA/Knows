package com.bs.knows.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.UploadNewPic;
import com.bs.knows.connect.bean.UserHistoryData;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.model.UserUtilsModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class FileUtil {

    private static String TAG="TAG";

    /**
     * 保存裁剪的图片
     * @param context   页面
     * @param bitmap    图片bitmap
     * @param picName   图片名称
     * @return          图片地址
     */
    public static File savebitmap(Context context, Bitmap bitmap, String picName)
    {
        Log.e(TAG, "保存图片");
        File dirFile=new File(StaticUtils.FILE_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        //创建文件，因为不存在2级目录，所以不用判断exist，要保存png，这里后缀就是png，要保存jpg，后缀就用jpg
        File file=new File(StaticUtils.FILE_PATH,picName);
        try {
            //文件输出流
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);



            //写入，这里会卡顿，因为图片较大
            fileOutputStream.flush();
            //记得要关闭写入流
            fileOutputStream.close();
            //成功的提示，写入成功后，请在对应目录中找保存的图片
            Toast.makeText(context,"写入成功！目录"+StaticUtils.FILE_PATH+picName,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //失败的提示
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //失败的提示
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return file;

    }

}
