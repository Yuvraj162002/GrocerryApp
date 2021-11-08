package com.ecommerce.android.grocerryapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    List<MyCartModel> myCartModelList;
    Context context;
    int totalprice = 0;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public MyCartAdapter(List<MyCartModel> myCartModelList, Context context) {
        this.myCartModelList = myCartModelList;
        this.context = context;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(myCartModelList.get(holder.getAdapterPosition()).getProductName());
        holder.price.setText(myCartModelList.get(holder.getAdapterPosition()).getProductPrice());
        holder.Date.setText(myCartModelList.get(holder.getAdapterPosition()).getCurrentDate());
        holder.totalprice.setText(String.valueOf(myCartModelList.get(holder.getAdapterPosition()).getTotalprice()));
        holder.time.setText(myCartModelList.get(holder.getAdapterPosition()).getCurrentTime());
        holder.quantity.setText(myCartModelList.get(holder.getAdapterPosition()).getTotalQuantity());



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CurrentUser").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(myCartModelList.get(holder.getAdapterPosition()).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    myCartModelList.remove(myCartModelList.get(holder.getAdapterPosition()));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;
        TextView quantity;
        TextView totalprice;
        TextView Date;
        TextView time;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.mycart_name11);
            delete = itemView.findViewById(R.id.delete_my_cart);
            price = itemView.findViewById(R.id.mycart_price11);
            quantity = itemView.findViewById(R.id.mycart_quantity11);
            totalprice = itemView.findViewById(R.id.mycart_totalprice11);
            Date = itemView.findViewById(R.id.mycart_date11);
            time = itemView.findViewById(R.id.mycart_time11);
        }
    }
}
