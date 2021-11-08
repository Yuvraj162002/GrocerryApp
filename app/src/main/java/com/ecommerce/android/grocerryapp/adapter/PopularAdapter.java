package com.ecommerce.android.grocerryapp.adapter;

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

import com.bumptech.glide.Glide;
import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.activities.ViewAllActivity;
import com.ecommerce.android.grocerryapp.model.PopularModle;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModle> popularModleList;

    public PopularAdapter(Context context, List<PopularModle> popularModleList) {
        this.context = context;
        this.popularModleList = popularModleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(popularModleList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModleList.get(position).getName());
        holder.rating.setText(popularModleList.get(position).getRating());
        holder.discounts.setText(popularModleList.get(position).getDiscount());
        holder.description.setText(popularModleList.get(position).getDesciption());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",popularModleList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularModleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView rating,name,description,discounts;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            rating = itemView.findViewById(R.id.pop_rating);
            description = itemView.findViewById(R.id.pop_des);
            discounts = itemView.findViewById(R.id.pop_discount);

        }
    }
}
