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

    @FormUrlEncoded
    @POST("/app.php?action=check_pswd")
    Call<UserBean> getUserDataPasswd(@Field("username") String username,@Field("password") String password);

    @POST("/app.php?action=check_pswd")
    Observable<UserBean> getUserDataPasswdRx(@Field("username") String username,@Field("password") String password);


    @FormUrlEncoded
    @POST("/app.php?action=check_user_exist")
    Call<UserBean> UserisExist(@Field("username") String username);

//    @POST("/app.php?action=check_user_exist")
//    Observable<UserBean> UserisExistRx(@Field("username") String username);




}
