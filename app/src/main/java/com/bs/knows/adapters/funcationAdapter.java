package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.activity.CameraActivity;
import com.bumptech.glide.Glide;

public class funcationAdapter extends RecyclerView.Adapter<funcationAdapter.viewHolder>{

    private Context mContext;

    public funcationAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_function_grid, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


//        final AlbumModel albumModel = mDataSource.get(i);
        Glide.with(mContext)
                .load("https://cdn.pixabay.com/user/2019/06/21/09-21-09-355_96x96.jpg")
                .into(holder.ivIcon);

//        holder.mTvPlayNum.setText("100w");
//        holder.mTvName.setText("扫描");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, CameraActivity.class);
//                intent.putExtra(AlbumListActivity.ALBUM_ID, albumModel.getAlbumId());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        View itemView;
        TextView mTvPlayNum, mTvName;

         viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_function);
            mTvPlayNum = itemView.findViewById(R.id.tv_play_num_function);
            mTvName = itemView.findViewById(R.id.tv_name_function);
        }


    }



}
