package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bs.knows.activity.CamerasActivity;
import com.bs.knows.activity.MineActivity;
import com.bs.knows.adapters.HistoryListAdapter;
import com.bs.knows.databinding.ActivityMainBinding;

public class MainActivityModel {

    public static void initListView(ActivityMainBinding binding, Context context) {
        /**
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        HistoryListAdapter mListAdapter = new HistoryListAdapter(context);
        binding.rvList.setAdapter(mListAdapter);
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
}
