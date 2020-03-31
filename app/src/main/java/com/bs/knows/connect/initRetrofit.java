package com.bs.knows.connect;

import com.baidu.ocr.sdk.model.GeneralParams;
import com.bs.knows.utils.StaticUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class initRetrofit {

    public static Retrofit getUserData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(StaticUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
