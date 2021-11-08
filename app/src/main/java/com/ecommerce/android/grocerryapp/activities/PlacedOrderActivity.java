package com.ecommerce.android.grocerryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        List<MyCartModel> myCartModelList = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemlist");

        if (myCartModelList != null && myCartModelList.size() > 0){
            for (MyCartModel myCartModel : myCartModelList){
                final HashMap<String,Object> cartmap = new HashMap<>();
                cartmap.put("ProductName",myCartModel.getProductName());
                cartmap.put("ProductPrice", myCartModel.getProductPrice());
                //  cartmap.put("Product Price",price.getText().toString());
                cartmap.put("currentDate",myCartModel.getCurrentDate());
                cartmap.put("currentTime",myCartModel.getCurrentTime());
                cartmap.put("TotalQuantity",myCartModel.getTotalQuantity());
                cartmap.put("totalprice",myCartModel.getTotalprice());

                firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartmap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlacedOrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        }
    }
}