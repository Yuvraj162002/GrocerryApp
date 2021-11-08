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
import com.ecommerce.android.grocerryapp.model.ExplorerModel;

import java.util.List;

public class ExplorerAdapter extends RecyclerView.Adapter<ExplorerAdapter.ViewHolder> {

     Context context;
     List<ExplorerModel> explorerModelList;

    public ExplorerAdapter(Context context, List<ExplorerModel> explorerModelList) {
        this.context = context;
        this.explorerModelList = explorerModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.explorer_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(explorerModelList.get(position).getImg_url()).into(holder.image);
        holder.name.setText(explorerModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",explorerModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return explorerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_explorer);
            name = itemView.findViewById(R.id.name_explorer);
        }
    }
}
