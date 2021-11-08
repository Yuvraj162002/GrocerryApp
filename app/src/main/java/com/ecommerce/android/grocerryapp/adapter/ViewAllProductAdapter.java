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
import com.ecommerce.android.grocerryapp.activities.ProductDeatiledActivity;
import com.ecommerce.android.grocerryapp.model.AllProductsModel;

import java.io.Serializable;
import java.util.List;

public class ViewAllProductAdapter extends RecyclerView.Adapter<ViewAllProductAdapter.ViewHolder> {
    Context context;
     List<AllProductsModel> allProductsModels;

    public ViewAllProductAdapter(Context context, List<AllProductsModel> allProductsModels) {
        this.context = context;
        this.allProductsModels = allProductsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewAllProductAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(allProductsModels.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(allProductsModels.get(position).getName());
        holder.rating.setText(allProductsModels.get(position).getRating());
        holder.desp.setText(allProductsModels.get(position).getDesp());
        holder.price.setText(allProductsModels.get(position).getPrice());
        holder.price.setText(allProductsModels.get(position).getPrice()+"/kg");

        if (allProductsModels.get(position).getType().equals("egg")){
            holder.price.setText(allProductsModels.get(position).getPrice()+"/dozen");
        }
        if (allProductsModels.get(position).getType().equals("fish")){
            holder.price.setText(allProductsModels.get(position).getPrice()+"/litre");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDeatiledActivity.class);

                /// TODO agr problem toh yha pe hogi.....

                intent.putExtra("detail",  allProductsModels.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allProductsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,desp,price,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_view);
            name = itemView.findViewById(R.id.name_view);
            desp = itemView.findViewById(R.id.desp_view);
            price = itemView.findViewById(R.id.dollar_view);
            rating = itemView.findViewById(R.id.rating_view);
        }
    }
}
