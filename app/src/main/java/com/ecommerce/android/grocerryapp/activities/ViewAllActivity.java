package com.ecommerce.android.grocerryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.adapter.ViewAllProductAdapter;
import com.ecommerce.android.grocerryapp.model.AllProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewAllProductAdapter viewAllProductAdapter;
    List<AllProductsModel> allProductsModelList;
    ProgressBar progressBar;
  Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);


        toolbar = findViewById(R.id.toolbar);
        getSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.View_all_recyclerView);
        progressBar = findViewById(R.id.progressBar_view_all);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allProductsModelList = new ArrayList<>();
        viewAllProductAdapter = new ViewAllProductAdapter(this,allProductsModelList);
        recyclerView.setAdapter(viewAllProductAdapter);


        ////// Getting Potato.........
       if(type != null & type.equalsIgnoreCase("fruit")) {
           firestore.collection("AllProducts").whereEqualTo("type", "fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
               @Override
               public void onComplete(@NonNull Task<QuerySnapshot> task) {

                   for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                       AllProductsModel allProductsModel = documentSnapshot.toObject(AllProductsModel.class);
                       allProductsModelList.add(allProductsModel);
                       viewAllProductAdapter.notifyDataSetChanged();
                       recyclerView.setVisibility(View.VISIBLE);
                       progressBar.setVisibility(View.GONE);
                   }

               }
           });
       }
           ////// Getting Carrot.........
           if(type != null & type.equalsIgnoreCase("vegetable")) {
               firestore.collection("AllProducts").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {

                       for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                           AllProductsModel allProductsModel = documentSnapshot.toObject(AllProductsModel.class);
                           allProductsModelList.add(allProductsModel);
                           viewAllProductAdapter.notifyDataSetChanged();
                           recyclerView.setVisibility(View.VISIBLE);
                           progressBar.setVisibility(View.GONE);
                       }

                   }
               });
           }
        ////// Getting egg.........
        if(type != null & type.equalsIgnoreCase("egg")) {
            firestore.collection("AllProducts").whereEqualTo("type", "egg").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        AllProductsModel allProductsModel = documentSnapshot.toObject(AllProductsModel.class);
                        allProductsModelList.add(allProductsModel);
                        viewAllProductAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });
        }
        ////// Getting fish.........
        if(type != null & type.equalsIgnoreCase("fish")) {
            firestore.collection("AllProducts").whereEqualTo("type", "fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        AllProductsModel allProductsModel = documentSnapshot.toObject(AllProductsModel.class);
                        allProductsModelList.add(allProductsModel);
                        viewAllProductAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });
        }
        ////// Getting Carrot.........
        if(type != null & type.equalsIgnoreCase("vegetable")) {
            firestore.collection("AllProducts").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        AllProductsModel allProductsModel = documentSnapshot.toObject(AllProductsModel.class);
                        allProductsModelList.add(allProductsModel);
                        viewAllProductAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });
        }
           }

    private void getSupportActionBar(Toolbar toolbar) {
    }

}
