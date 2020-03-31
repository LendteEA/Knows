package com.bs.knows.connect;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;

public class GetUserData {

    private static String TAG="TAG";


    public static boolean returnGetUserDataisError(){
        final boolean[] result = {false};
        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.getUserDataStatus().enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {

                result[0] =response.body().isError();
                Log.d(TAG, "onResponse returnGetUserDataisError: "+ result[0]);

            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Log.d(TAG, "onFailure returnGetUserDataisError: "+t.toString());
            }
        });

        if(result[0]){
            return true;
        }else{
            return false;
        }


    }

    public static void returnGetUserData(){
        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.getUserDatas().enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                String username=response.body().getUsers().get(0).getUsername();
                Log.d(TAG, "onResponse: "+username);
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }
        });

    }


    //    检查用户输入密码是否正确
    public static boolean UserDataPasswdIsCorrect(String username, String password){
        final boolean[] pswd = {false};
        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.getUserDataPasswd(username,password).enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                pswd[0] =response.body().isPassword();
                Log.d(TAG, "onResponse UserDataPasswdIsCorrect: "+ pswd[0]);
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Log.d(TAG, "onFailure UserDataPasswdIsCorrect: "+t.toString());
            }
        });
        return pswd[0];
    }

    //    检查用户输入用户名是否存在
    public static boolean UserIsExist(String username){
        final boolean[] usernames = {false};
        Retrofit retrofit=initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.UserisExist(username).enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                usernames[0] =response.body().isUserIsExist();
                Log.d(TAG, "onResponse UserIsExist: "+ usernames[0]);
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Log.d(TAG, "onFailure UserIsExist: "+t.toString());
            }
        });
        return usernames[0];
    }

//    public static boolean UserIsExistRx(String username){
//        final boolean[] usernames = {false};
//        Retrofit retrofit=initRetrofit.getUserData();
//        Api api=retrofit.create(Api.class);
//        api.UserisExistRx(username).subscribe(new Subscriber<UserBean>() {
//            @Override
//            public void onCompleted() {
//
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onNext UserisExistRx FALSE");
//            }
//
//            @Override
//            public void onNext(UserBean userBean) {
//
//                usernames[0]=userBean.isUserIsExist();
//
//                Log.d(TAG, "onNext userBean: "+userBean.isUserIsExist());
//
////                api.getUserDataPasswdRx(username,password).subscribe(new Subscriber<UserBean>() {
////                    @Override
////                    public void onCompleted() {
////
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.d(TAG, "onNext getUserDataPasswdRx FALSE");
////                    }
////
////                    @Override
////                    public void onNext(UserBean userBean) {
////                        Log.d(TAG, "onNext getUserDataPasswdRx YES");
////                    }
////                });
//            }
//        });
//
//
////        api.UserisExist(username).enqueue(new Callback<UserBean>() {
////            @Override
////            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
////                usernames[0] =response.body().isUserIsExist();
////                Log.d(TAG, "onResponse UserIsExist: "+ usernames[0]);
////            }
////
////            @Override
////            public void onFailure(Call<UserBean> call, Throwable t) {
////                Log.d(TAG, "onFailure UserIsExist: "+t.toString());
////            }
////        });
//        return usernames[0];
//    }
}
