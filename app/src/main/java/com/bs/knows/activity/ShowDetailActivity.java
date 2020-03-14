package com.bs.knows.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.bs.knows.R;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.NavBarBinding;
import com.bs.knows.model.showDetailModel;
import com.bs.knows.utils.FileUtil;
import com.bs.knows.utils.RecoginzeService;
import com.bs.knows.viewmodel.CameraVM;
import com.bs.knows.viewmodel.ShowDetailVM;
import com.bumptech.glide.Glide;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowDetailActivity extends BaseActivty {

    private String TAG = "showdetail";

    private ActivityShowDetailBinding binding;
//    private boolean hasGotToken=false;
    private String token;

    //    private NavBarBinding navBarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String picDetail;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        Intent intent = getIntent();


        ShowDetailVM showDetailVM = new ShowDetailVM(this,binding, intent);
        binding.setShowDetail(showDetailVM);
        binding.tvShowDetail.setText(intent.getStringExtra("picPaths"));
        initView();
        showDetailVM.getPic();

    }

    private void initView() {
        initNavBar(this, true, "确认图片", false);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        initAccessTokenLicenseFile(this);

//        RecoginzeService.recAccurateBasic(this, bitmap, new RecoginzeService.ServiceListener() {
//            @Override
//            public void onResult(String result) {
//                Bundle bundle=new Bundle();
//                binding.tvShowDetail.setText(result);
//                Log.d(TAG, "onResult: "+result);
//            }
//        });

    }


//    /**
//     * 以license方式初始化OCR
//     */
//    public void initAccessTokenLicenseFile(Context context) {
//
//        OCR.getInstance(context).initAccessToken(new OnResultListener<AccessToken>() {
//            @Override
//            public void onResult(AccessToken accessToken) {
//                token = accessToken.getAccessToken();
//                Log.d(TAG, "onResult: " + accessToken.toString());
//                hasGotToken=true;
//            }
//
//            @Override
//            public void onError(OCRError ocrError) {
//                ocrError.printStackTrace();
//                Log.d(TAG, "onError: "+ocrError.getMessage());
//
//            }
//        },"aip.license",context);
//    }

}
