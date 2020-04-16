package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bs.knows.activity.CamerasActivity;
import com.bs.knows.activity.MineActivity;
import com.bs.knows.adapters.HistoryListAdapter;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.UserHistoryData;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityModel {
    private static HistoryListAdapter mListAdapter;
    private static String TAG="TAG";

    public static void initListView(ActivityMainBinding binding, Context context) {
        /**
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        mListAdapter = new HistoryListAdapter(context);
        binding.rvList.setAdapter(mListAdapter);
        getHistoryData(UserUtilsModel.UserIsLogin.getInstance().getPhone());

    }

    public static void startScan(Context context) {
        Intent intent = new Intent(context, CamerasActivity.class);
        context.startActivity(intent);
    }

    public static void goMine(Context context,ImageView mine) {
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MineActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void getHistoryData(String username){

        Retrofit retrofit= initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        Call<UserHistoryData> task=api.UserHistoryData(username);
        Log.d(TAG, "getHistoryData: Username "+username);
        task.enqueue(new Callback<UserHistoryData>() {
            @Override
            public void onResponse(Call<UserHistoryData> call, Response<UserHistoryData> response) {
                try {
                    UserHistoryData userHistoryData=response.body();
                    Log.d(TAG, "initListView:  getHistoryData"+userHistoryData);
                    UpdateList(userHistoryData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserHistoryData> call, Throwable t) {

            }
        });

    }

    private static void UpdateList(UserHistoryData userHistoryData){
        mListAdapter.setData(userHistoryData);
    }


    public static void startScanB(ActivityMainBinding mBinding, Context mContext) {
        mBinding.ivFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CamerasActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
