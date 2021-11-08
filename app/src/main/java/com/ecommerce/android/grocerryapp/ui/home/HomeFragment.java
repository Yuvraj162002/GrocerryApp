package com.ecommerce.android.grocerryapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.adapter.ExplorerAdapter;
import com.ecommerce.android.grocerryapp.adapter.PopularAdapter;
import com.ecommerce.android.grocerryapp.adapter.RecommededAdapter;
import com.ecommerce.android.grocerryapp.adapter.ViewAllProductAdapter;
import com.ecommerce.android.grocerryapp.model.AllProductsModel;
import com.ecommerce.android.grocerryapp.model.ExplorerModel;
import com.ecommerce.android.grocerryapp.model.PopularModle;
import com.ecommerce.android.grocerryapp.model.RecommededModel;
import com.ecommerce.android.grocerryapp.model.Usermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView popularRec;
    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView explorerRec;
    RecyclerView recomRec;
     FirebaseFirestore db;
    // Popular products....
    PopularAdapter popularAdapter;
    List<PopularModle> popularModleList;


   //////////// ///////////////// Search View......
    EditText search_box;
    private List<AllProductsModel> allProductsModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllProductAdapter viewAllProductAdapter;

    // Explorer Category..
    ExplorerAdapter explorerAdapter;
    List<ExplorerModel> explorerModelList;

    //Recommeded Items....
    RecommededAdapter recommededAdapter;
    List<RecommededModel> recommededModelList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();


        popularRec = root.findViewById(R.id.pop_res);
        explorerRec =  root.findViewById(R.id.pop_explorer);
        recomRec =  root.findViewById(R.id.pop_recom);
        progressBar = root.findViewById(R.id.progres_bar);
        scrollView = root.findViewById(R.id.scroll_view);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);


        /// Popular products...
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModleList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModleList);
        popularRec.setAdapter(popularAdapter);
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               PopularModle popularModle = document.toObject(PopularModle.class);
                               popularModleList.add(popularModle);
                               popularAdapter.notifyDataSetChanged();
                               progressBar.setVisibility(View.GONE);
                               scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /// Explorer Category...

        explorerRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        explorerModelList= new ArrayList<>();
         explorerAdapter = new ExplorerAdapter(getActivity(), explorerModelList);
        explorerRec.setAdapter(explorerAdapter);
        db.collection("ExplorerCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ExplorerModel  explorerModel = document.toObject(ExplorerModel.class);
                                explorerModelList.add(explorerModel);
                                explorerAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        /// Recommeded Items
        recomRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommededModelList = new ArrayList<>();
        recommededAdapter = new RecommededAdapter(getActivity(),recommededModelList);
        recomRec.setAdapter(recommededAdapter);
        db.collection("RecommededItem")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommededModel recommededModel = document.toObject(RecommededModel.class);
                                recommededModelList.add(recommededModel);
                                recommededAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //////////// ///////////////// Search View......
        recyclerViewSearch = root.findViewById(R.id.home_recy);
        search_box = root.findViewById(R.id.search_bar);

        allProductsModelList = new ArrayList<>();
        viewAllProductAdapter = new ViewAllProductAdapter(getContext(),allProductsModelList);

        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllProductAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.toString().isEmpty()){
                    allProductsModelList.clear();
                    viewAllProductAdapter.notifyDataSetChanged();
                }else{
                    searchProduct(s.toString());
                }
            }
        });

        return root;
    }

    private void searchProduct(String type) {

        if (!type.isEmpty()){

            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null){

                                allProductsModelList.clear();
                                viewAllProductAdapter.notifyDataSetChanged();

                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                    AllProductsModel allProductsModel = documentSnapshot.toObject(AllProductsModel.class);
                                    allProductsModelList.add(allProductsModel);
                                    viewAllProductAdapter.notifyDataSetChanged();
                                }

                            }
                        }
                    });
        }
    }


}