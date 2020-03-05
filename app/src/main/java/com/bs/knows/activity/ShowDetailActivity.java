package com.bs.knows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.NavBarBinding;
import com.bs.knows.viewmodel.ShowDetailVM;
import com.bumptech.glide.Glide;

public class ShowDetailActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.bs.knows.databinding.ActivityShowDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        Intent intent=getIntent();
        ShowDetailVM showDetailVM =new ShowDetailVM(binding,intent);
        binding.setShowDetail(showDetailVM);

        initNavBar(this,true, "确认图片", false);
                initView();
    }

    private void initView() {
        NavBarBinding barBinding= DataBindingUtil.setContentView(this,R.layout.nav_bar);

        barBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
