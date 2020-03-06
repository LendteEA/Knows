package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.NavBarBinding;
import com.bs.knows.model.showDetailModel;

public class ShowDetailVM {

    private Intent mIntent;
    private NavBarBinding navBarBinding;
    private ActivityShowDetailBinding binding;
    private showDetailModel showDetailModel;

    public ShowDetailVM(ActivityShowDetailBinding binding, Intent intent) {
        this.binding = binding;
        this.mIntent=intent;
    }

    public void getPic(View view){

        showDetailModel.showGetPic(binding,view.getContext(),mIntent);
    }

    public void getPicPath(View view){
        showDetailModel.showpicPath(binding,mIntent);
    }
}
