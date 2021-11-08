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
import com.ecommerce.android.grocerryapp.activities.SecondCategoryActivity;
import com.ecommerce.android.grocerryapp.model.NavcategoryModel;

import java.util.List;

public class NavCategory extends RecyclerView.Adapter<NavCategory.ViewHolder> {

   private Context context;
   private List<NavcategoryModel> navcategoryModelList;

    public NavCategory(Context context, List<NavcategoryModel> navcategoryModelList) {
        this.context = context;
        this.navcategoryModelList = navcategoryModelList;
    }

    @NonNull
    @Override
    public NavCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavCategory.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategory.ViewHolder holder, int position) {
        Glide.with(context).load(navcategoryModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(navcategoryModelList.get(position).getName());
        holder.disc.setText(navcategoryModelList.get(position).getDisc());
        holder.desp.setText(navcategoryModelList.get(position).getDesp());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , SecondCategoryActivity.class);
                intent.putExtra("type",navcategoryModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return navcategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,desp,disc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (itemView).findViewById(R.id.img_cat);
            name = (itemView).findViewById(R.id.name_cat);
            desp = (itemView).findViewById(R.id.desp_cat);
            disc = (itemView).findViewById(R.id.disc_cat);
        }
    }
}
