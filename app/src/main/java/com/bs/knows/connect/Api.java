package com.bs.knows.connect;

import com.bs.knows.connect.bean.UploadNewPic;
import com.bs.knows.connect.bean.UserBean;
import com.bs.knows.connect.bean.UserHistoryData;
import com.bs.knows.connect.bean.UserRegister;
import com.bs.knows.connect.bean.UserUpdatePs;
import com.bs.knows.connect.bean.checkUserId;
import com.bs.knows.connect.bean.checkUserPswd;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface Api {

    @GET("/app.php?action=read")
    Call<UserBean> getUserDataStatus();

    @GET("/app.php?action=read")
    Call<UserBean> getUserDatas();

    //                         查询密码是否正确
    @FormUrlEncoded
    @POST("/app.php?action=check_pswd")
    Call<checkUserPswd> UserDataPasswdisCorrect(@Field("username") String username, @Field("password") String password);

    //                         查询用户是否存在
    @FormUrlEncoded
    @POST("/app.php?action=check_user_exist")
    Call<checkUserId> UserisExist(@Field("username") String username);


    @FormUrlEncoded
    @POST("/app.php?action=register")
    Call<UserRegister> UserRegister(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/app.php?action=updates")
    Call<UserUpdatePs> UserUpdatePs(@Field("username") String username, @Field("old_password") String old_password, @Field("new_password") String new_password);

    @FormUrlEncoded
    @POST("/app.php?action=check_history")
    Call<UserHistoryData> UserHistoryData(@Field("username") String username);

    @Multipart
    @POST("/app.php?action=uploadnewpic")
    Call<UploadNewPic> Uploadnewpic(@Part("username") RequestBody username, @Part MultipartBody.Part file);

    @Multipart
    @POST("/app.php?action=uploadnewpic")
    Observable<UploadNewPic> UploadnewpicRX(@Part("username") RequestBody username, @Part MultipartBody.Part file);


}
