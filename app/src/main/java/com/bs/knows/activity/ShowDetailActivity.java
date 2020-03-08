package com.bs.knows.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.NavBarBinding;
import com.bs.knows.model.CameraModel;
import com.bs.knows.model.showDetailModel;
import com.bs.knows.viewmodel.CameraVM;
import com.bs.knows.viewmodel.ShowDetailVM;
import com.bumptech.glide.Glide;

public class ShowDetailActivity extends BaseActivty {

    private String TAG="showdetail";
    private ActivityShowDetailBinding binding;
//    private NavBarBinding navBarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        Intent intent=getIntent();
        String path=intent.getStringExtra("picPathUri");

        Log.d(TAG, "onCreate: "+path);
        ShowDetailVM showDetailVM =new ShowDetailVM(binding,intent);
        binding.setShowDetail(showDetailVM);
        initView();
        showDetailModel.showGetPic(binding,this,intent);

    }

    private void initView() {
//        navBarBinding= DataBindingUtil.setContentView(this,R.layout.nav_bar);
        initNavBar(this,true, "确认图片", false);


        ImageView ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
