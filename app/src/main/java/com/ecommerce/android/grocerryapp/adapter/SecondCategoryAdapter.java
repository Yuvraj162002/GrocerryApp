package com.ecommerce.android.grocerryapp.adapter;

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
import com.ecommerce.android.grocerryapp.model.SecondCategoryModel;

import java.util.List;

public class SecondCategoryAdapter extends RecyclerView.Adapter<SecondCategoryAdapter.ViewHolder> {

    Context context;
    List<SecondCategoryModel> secondCategoryModelList;

    public SecondCategoryAdapter(Context context, List<SecondCategoryModel> secondCategoryModelList) {
        this.context = context;
        this.secondCategoryModelList = secondCategoryModelList;
    }

    @NonNull
    @Override
    public SecondCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new SecondCategoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_second_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SecondCategoryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(secondCategoryModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(secondCategoryModelList.get(position).getName());
        holder.price.setText(secondCategoryModelList.get(position).getPrice());



    }

    @Override
    public int getItemCount() {
        return secondCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView price , name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_second_cat);
            price  = itemView.findViewById(R.id.price_second_cat);
            name = itemView.findViewById(R.id.name_second_cat);
        }
    }
}
