package com.example.projectgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgame.R;
import com.example.projectgame.model.User;

//คลาสเชื่อมระว่างข้อมูลกับการแสดงใน recycler view
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private User[] mUsers;

    public UserAdapter(Context context, User[] users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(v);
    }

    //เมธอดสำหรับกำหนดค่าต่างๆสำหรับการแสดงข้อมูลในหน้าจอหลัก
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mUsers[position];

        holder.productnametextview.setText(user.name);
        holder.bbetext.setText(user.bbe);
        holder.mfgtext.setText(user.mfg);
        holder.exptext.setText(user.exp);

    }

    //เมธอดส่งคืนขนาดของวัตถุที่จะนำไปแสดงผล
    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    //คลาสสำหรับการเชื่อมต่อกับ recycler view
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productnametextview;
        TextView bbetext;
        TextView mfgtext;
        TextView exptext;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //กำหนดตัวแปรต่างๆ
            this.productnametextview = itemView.findViewById(R.id.product_name_text_view);
            this.bbetext = itemView.findViewById(R.id.bbe_text);
            this.mfgtext = itemView.findViewById(R.id.mfg_text_view);
            this.exptext = itemView.findViewById(R.id.exp_text_view);

        }
    }
}