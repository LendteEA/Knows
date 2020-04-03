package com.bs.knows.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bs.knows.databinding.ActivityMainBinding;
import com.bs.knows.model.MainActivityModel;

public class MainActivityVM {
    private static Context mContext;
    private static ActivityMainBinding mBinding;

    public MainActivityVM(Context mContext, ActivityMainBinding mBinding) {
        MainActivityVM.mContext = mContext;
        MainActivityVM.mBinding = mBinding;
    }

    public static void MineActivity(ImageView mine){
        MainActivityModel.goMine(mContext,mine);
    }

    public static void InitList(){
        MainActivityModel.initListView(mBinding,mContext);
    }

    public void startScans(View view){
        MainActivityModel.startScan(mContext);
    }

    public void startScanB(View view){
        MainActivityModel.startScanB(mBinding,mContext);
    }
}
