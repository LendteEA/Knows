package com.bs.knows.utils;

import java.util.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
//    /**
//     * 通过图片URL的形式，获取图片内的文字信息
//     * @param accessToken 通过API Key和Secret Key获取的access_token
//     * @param url 图片的url
//     * @return observable对象用于rxjava,从RecognitionResultBean中可以获得图片文字识别的信息
//     */
//    @POST("rest/2.0/ocr/v1/general_basic")
//    @FormUrlEncoded
//    Observable<RecognitionResultBean> getRecognitionResultByUrl(@Field("access_token") String accessToken, @Field("url") String url);
//
//    /**
//     * 通过图片，获取图片内的文字信息
//     * @param accessToken 通过API Key和Secret Key获取的access_token
//     * @param image 图像数据base64编码后进行urlencode后的String
//     * @return  observable对象用于rxjava,从RecognitionResultBean中可以获得图片文字识别的信息
//     */
//    @POST("rest/2.0/ocr/v1/general_basic")
//    @FormUrlEncoded
//    Observable<RecognitionResultBean> getRecognitionResultByImage(@Field("access_token") String accessToken, @Field("image") String image);

}
