package com.bs.knows.connect;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    Call<UserUpdatePs> UserUpdatePs(@Field("username") String username, @Field("old_password") String old_password,@Field("new_password") String new_password);



}
