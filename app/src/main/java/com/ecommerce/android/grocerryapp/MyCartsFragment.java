package com.ecommerce.android.grocerryapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.grocerryapp.activities.PlacedOrderActivity;
import com.ecommerce.android.grocerryapp.adapter.MyCartAdapter;
import com.ecommerce.android.grocerryapp.adapter.NavCategory;
import com.ecommerce.android.grocerryapp.model.MyCartModel;
import com.ecommerce.android.grocerryapp.model.NavcategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView finaltotal;
    MyCartAdapter myCartAdapter;
    ProgressBar progressBar;
   List<MyCartModel> myCartModelList;
   Button button;
   TextView textView;


    public MyCartsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts,container,false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView =root.findViewById(R.id.recyclerView);
        finaltotal = root.findViewById(R.id.total_value_cart);

        progressBar = root.findViewById(R.id.progressBar_my_cart);
        button = root.findViewById(R.id.buy_now);
        textView = root.findViewById(R.id.total_value_cart);

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);




        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        myCartModelList = new ArrayList<>();
       myCartAdapter = new MyCartAdapter(myCartModelList,getActivity());
        recyclerView.setAdapter(myCartAdapter);
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {

                                String documentId = document.getId();
                                MyCartModel myCartModel = document.toObject(MyCartModel.class);
                                myCartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();

                                myCartModel.setDocumentId(documentId);

                                recyclerView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                textView.setVisibility(View.VISIBLE);
                            }

                            caluclateTotalAmount(myCartModelList);


                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PlacedOrderActivity.class);
                intent.putExtra("itemlist", (Serializable) myCartModelList);
                startActivity(intent);

            }
        });
        return root;
    }

    private void caluclateTotalAmount(List<MyCartModel> myCartModelList) {

      double  totalAmount = 0.0;

      for (MyCartModel myCartModel : myCartModelList){
          totalAmount += myCartModel.getTotalprice();
      }

      finaltotal.setText("Total Amoount : " +totalAmount);

    }


}