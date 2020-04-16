package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;

import com.bin.david.form.annotation.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.HistoryDataText;
import com.bs.knows.connect.bean.UserHistoryData;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.utils.ScanDataTable;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class showDetailModel {


    private static String TAG = "TAGSS";



    public static void showGetPic(Context context,ActivityShowDetailBinding binding,Intent intent) {
        List<ScanDataTable> scanDataTables=new ArrayList<>();


        String path = intent.getStringExtra("picPaths");
        String scanfilepath = intent.getStringExtra("scanfilePaths");

        binding.tvShowDetail.setText(path);

        Glide.with(context)
                .load(path)
                .into(binding.ivShowDetail);

        Retrofit retrofit= initRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.ShowScanText(scanfilepath).enqueue(new Callback<HistoryDataText>() {
            @Override
            public void onResponse(Call<HistoryDataText> call, Response<HistoryDataText> response) {
                HistoryDataText historyDataText=response.body();
//                historyDataText.getFiledata().getScanData().get(0).getData().get(0).getText();

                Log.d(TAG, "onResponse data.size: "+historyDataText.getFiledata().getScanData().get(0).getData().size());
//                Log.d(TAG, "onResponse data.get(0).getText(): "+data.get(0).getText());
                for (int i=0;i<historyDataText.getFiledata().getScanData().get(0).getData().size();i++){
                    scanDataTables.add(new ScanDataTable(historyDataText.getFiledata().getScanData().get(0).getData().get(i).getText(), (float) historyDataText.getFiledata().getScanData().get(0).getData().get(i).getTextprob(),historyDataText.getFiledata().getScanData().get(0).getData().get(i).getProb()));
                }
                binding.table.setData(scanDataTables);
                binding.table.getConfig().setContentStyle(new FontStyle(45, Color.WHITE));
                binding.table.notifyDataChanged();




            }

            @Override
            public void onFailure(Call<HistoryDataText> call, Throwable t) {

            }
        });
    }


    public String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);

    }
}
