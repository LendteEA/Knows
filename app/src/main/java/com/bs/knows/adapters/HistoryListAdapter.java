package com.bs.knows.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.activity.ShowDetailActivity;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.UserHistoryData;
import com.bs.knows.connect.initRetrofit;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.viewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcaulationRvHeight;
    private String TAG="TAG";

    private List<UserHistoryData.HistoryBean> data=new ArrayList<>();

    public HistoryListAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_history_list,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

       UserHistoryData.HistoryBean databean=data.get(position);
       holder.tvName.setText(databean.getTitle());
       holder.tvAuthor.setText("最后更新时间："+databean.getUpdate_date());
        Log.d(TAG, "onBindViewHolder: "+StaticUtils.DOWNLOAD_IMG_BASE_URL+databean.getImg_path());

//        setRecyclerViewHeight();

        Glide.with(mContext)
                .load(StaticUtils.DOWNLOAD_IMG_BASE_URL+databean.getImg_path())
                .into(holder.ivIcon)
                .onStart();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ShowDetailActivity.class);
                intent.putExtra("picPaths",StaticUtils.DOWNLOAD_IMG_BASE_URL+databean.getImg_path());
                mContext.startActivity(intent);
            }
        });
//        holder.tvName.setText("历史1");
//        holder.tvAuthor.setText("历史1");
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ShowDetailActivity.class);
////                intent.putExtra(PlayMusicActivity.MUSIC_ID, musicModel.getMusicId());
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * 1、获取ItemView的高度
     * 2、itemView的数量
     * 3、使用 itemViewHeight * itemViewNum = RecyclerView的高度
     */
    private void setRecyclerViewHeight () {

//        if (isCalcaulationRvHeight || mRv == null) return;
//
//        isCalcaulationRvHeight = true;

//        获取ItemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
//        itemView的数量
        int itemCount = getItemCount();
//        使用 itemViewHeight * itemViewNum = RecyclerView的高度
        int recyclerViewHeight = itemViewLp.height * itemCount;
//        设置RecyclerView高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);
    }

    public void setData(UserHistoryData userHistoryData) {
        data.clear();
        data.addAll(userHistoryData.getHistory());
        notifyDataSetChanged();
    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon;
        TextView tvName, tvAuthor;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_list);
            tvName = itemView.findViewById(R.id.tv_name_list);
            tvAuthor = itemView.findViewById(R.id.tv_author_list);
        }
    }
}
