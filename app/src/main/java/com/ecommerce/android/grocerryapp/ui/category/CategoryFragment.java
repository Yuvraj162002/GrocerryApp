package com.ecommerce.android.grocerryapp.ui.category;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.activities.SecondCategoryActivity;
import com.ecommerce.android.grocerryapp.adapter.NavCategory;
import com.ecommerce.android.grocerryapp.adapter.PopularAdapter;
import com.ecommerce.android.grocerryapp.model.NavcategoryModel;
import com.ecommerce.android.grocerryapp.model.PopularModle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    ProgressBar progressBar;
    NavCategory navCategory;
    List<NavcategoryModel> navcategoryModelList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_category, container, false);
        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.recy_category);
        progressBar = root.findViewById(R.id.progressBar_category);
        recyclerView.setVisibility(View.GONE);
       progressBar.setVisibility(View.VISIBLE);

        ImageView arrow = root.findViewById(R.id.side_arrow_cat);




        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        navcategoryModelList = new ArrayList<>();
        navCategory = new NavCategory(getActivity(), navcategoryModelList);
        recyclerView.setAdapter(navCategory);
        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavcategoryModel navcategoryModel = document.toObject(NavcategoryModel.class);
                                navcategoryModelList.add(navcategoryModel);
                                navCategory.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }


}




