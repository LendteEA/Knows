package com.bs.knows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.viewmodel.ShowDetailVM;

import retrofit2.Retrofit;


public class ShowDetailActivity extends BaseActivty {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.bs.knows.databinding.ActivityShowDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        Intent intent = getIntent();


        ShowDetailVM showDetailVM = new ShowDetailVM(this, binding, intent);
        binding.setShowDetail(showDetailVM);
        binding.tvShowDetail.setText(intent.getStringExtra("picPaths"));
        initView();

        final ImageView imageView=(ImageView)findViewById(R.id.iv_showDetail);
        ViewTreeObserver viewTreeObserver=imageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        showDetailVM.getPic();

    }

    private void initView() {
        initNavBar(true, "确认图片", false);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
