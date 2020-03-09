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
import com.bs.knows.activity.CamerasActivity;
import com.bs.knows.activity.MainActivity;
import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class funcationAdapter extends RecyclerView.Adapter<funcationAdapter.viewHolder>{

    private Context mContext;
    private String TAG="Main";

    public funcationAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_funcation_grid, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {

        Glide.with(mContext)
                .load("https://cdn.pixabay.com/user/2019/06/21/09-21-09-355_96x96.jpg")
                .into(holder.IvFunctionBackGround);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start();
                Intent intent = new Intent(mContext, CamerasActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ImageView IvFunctionBackGround;
        View itemView;
        TextView mTvPlayNum;

         viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            IvFunctionBackGround = itemView.findViewById(R.id.iv_function_background);
            mTvPlayNum = itemView.findViewById(R.id.tv_function_name);
        }


    }



}
