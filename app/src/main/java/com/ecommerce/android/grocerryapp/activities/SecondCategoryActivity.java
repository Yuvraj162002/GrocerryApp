package com.ecommerce.android.grocerryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.adapter.SecondCategoryAdapter;
import com.ecommerce.android.grocerryapp.adapter.ViewAllProductAdapter;
import com.ecommerce.android.grocerryapp.model.AllProductsModel;
import com.ecommerce.android.grocerryapp.model.PopularModle;
import com.ecommerce.android.grocerryapp.model.SecondCategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SecondCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;
    ProgressBar progressBar;
    SecondCategoryAdapter secondCategoryAdapter;
    List<SecondCategoryModel> secondCategoryModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_category);

        recyclerView = findViewById(R.id.recy_second_category);

        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

         String type = getIntent().getStringExtra("type");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progressBar_second_category);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        secondCategoryModelList = new ArrayList<>();
        secondCategoryAdapter = new SecondCategoryAdapter(this, secondCategoryModelList);
        recyclerView.setAdapter(secondCategoryAdapter);

        if(type != null & type.equalsIgnoreCase("healthy")) {
            firebaseFirestore.collection("NavSecondCategory").whereEqualTo("type", "healthy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                       SecondCategoryModel secondCategoryModel = documentSnapshot.toObject(SecondCategoryModel.class);
                        secondCategoryModelList.add(secondCategoryModel);
                        secondCategoryAdapter.notifyDataSetChanged();

                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });

           }

//        firebaseFirestore.collection("NavSecondCategory")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                SecondCategoryModel popularModle = document.toObject(SecondCategoryModel.class);
//                                secondCategoryModelList.add(popularModle);
//                                secondCategoryAdapter.notifyDataSetChanged();
////                                progressBar.setVisibility(View.GONE);
////                                scrollView.setVisibility(View.VISIBLE);
//                            }
//                        } else {
//                            Toast.makeText(SecondCategoryActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
    }
}