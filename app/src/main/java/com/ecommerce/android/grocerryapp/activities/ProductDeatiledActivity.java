package com.ecommerce.android.grocerryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.model.AllProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDeatiledActivity extends AppCompatActivity {

    ImageView   Detailedimgview;
    TextView price,rating , desp , quantity;
    Button addtocart;
    ImageView remove , add;
    Toolbar toolbar;

    AllProductsModel allProductsModel = null;

     int qty = 1;
     int totalprice = 0;

     FirebaseFirestore firestore;
     FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatiled);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      final  Object object = getIntent().getSerializableExtra("detail");

      if (object instanceof AllProductsModel){
          allProductsModel = (AllProductsModel) object;

      }


        Detailedimgview = findViewById(R.id.detailed_img);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.deatiled_rating);
        desp = findViewById(R.id.deatiled_desp);
        add = findViewById(R.id.add_item);
        remove =  findViewById(R.id.remove_item);
        addtocart = findViewById(R.id.add_to_cart);
        quantity =  findViewById(R.id.quantity);


        if (allProductsModel !=null){
            Glide.with(getApplicationContext()).load(allProductsModel.getImg_url()).into(Detailedimgview);
            rating.setText(allProductsModel.getRating());
            desp.setText(allProductsModel.getDesp());
            price.setText("Price :$"+allProductsModel.getPrice()+"/kg");


                totalprice =  Integer.valueOf(allProductsModel.getPrice()) *  qty;


            if (allProductsModel.getType().equals("eggs")){
                price.setText("Price :$"+allProductsModel.getPrice()+"/dozen");
                totalprice =Integer.parseInt(allProductsModel.getPrice()) *  qty;
            }


        }
        addtocart = findViewById(R.id.add_to_cart);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

           remove.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if (qty>1){
                       qty--;
                       quantity.setText("" + qty);
                       totalprice =Integer.parseInt( allProductsModel.getPrice()) *  qty;

//                   Toast.makeText(ProductDeatiledActivity.this, "youoihdisdshd", Toast.LENGTH_SHORT).show();

                   }

               }
           });

           add.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (qty < 10) {
                       qty++;
                       quantity.setText("" + qty);


                   }
               }
           });


    }

    private void addedToCart() {
        String CurrentDate ,  CurrentTime;
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd,yyyy");
        CurrentDate  = simpleDateFormat.format(cal.getTime());


        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss a");
        CurrentTime = simpleDateFormat1.format(cal.getTime());

        final HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("ProductName",allProductsModel.getName());
      //  Object product_name = cartmap.put("Product Name", AllProductsModel.getName());
        cartmap.put("ProductPrice", price.getText().toString());
      //  cartmap.put("Product Price",price.getText().toString());
        cartmap.put("currentDate",CurrentDate);
        cartmap.put("currentTime",CurrentTime);
        cartmap.put("TotalQuantity",quantity.getText().toString());
        cartmap.put("totalprice",totalprice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartmap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ProductDeatiledActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

}