package com.ecommerce.android.grocerryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.model.RecommededModel;

import java.util.List;
public class RecommededAdapter extends RecyclerView.Adapter<RecommededAdapter.ViewHolder> {

    private Context context;
   private List<RecommededModel> recommededModelList;

    public RecommededAdapter(Context context, List<RecommededModel> recommededModelList) {
        this.context = context;
        this.recommededModelList = recommededModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommeded_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(recommededModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(recommededModelList.get(position).getName());
        holder.rating.setText(recommededModelList.get(position).getRating());
        holder.desp.setText(recommededModelList.get(position).getDesp());

    }

    @Override
    public int getItemCount() {
        return recommededModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,desp,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.pop_img_recom);
            name = itemView.findViewById(R.id.pop_name_recom);
            desp = itemView.findViewById(R.id.pop_des_recom);
            rating = itemView.findViewById(R.id.pop_rating_recom);
        }
    }
}
