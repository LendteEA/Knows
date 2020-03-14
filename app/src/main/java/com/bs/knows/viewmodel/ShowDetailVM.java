package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.NavBarBinding;
import com.bs.knows.model.showDetailModel;

public class ShowDetailVM {

    private Intent mIntent;
    private Context mContext;
    private ActivityShowDetailBinding mBinding;

    public ShowDetailVM(Context context,ActivityShowDetailBinding binding, Intent intent) {
        this.mBinding = binding;
        this.mIntent=intent;
        this.mContext=context;
    }

    public void getPic(){

        showDetailModel.showGetPic(mContext,mBinding,mIntent);
    }

}
