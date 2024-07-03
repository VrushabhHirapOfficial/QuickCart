package com.vrushabhhirap.quickcart.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Adapter.NewProductsAdapter;
import com.vrushabhhirap.quickcart.Adapter.PopularProductAdapter;
import com.vrushabhhirap.quickcart.Model.NewProductModel;
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ProgressDialog progressDialog;

    LinearLayout linearLayout;

    RecyclerView newProductRecyclerView,popularProductsRecyclerView;
    PopularProductAdapter popularProductAdapter;
    List<PopularProductModel> popularProductModelList;

    // New Product recyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductModel> newProductModelList;
    FirebaseFirestore db;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressDialog = new ProgressDialog(getActivity());
        linearLayout = view.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);


        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        // New Products
        newProductRecyclerView = view.findViewById(R.id.new_product_rec);
        popularProductsRecyclerView = view.findViewById(R.id.popular_products_rec);

        slideModels.add(new SlideModel(R.drawable.banner2, "Nike Epic", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3, "Fashion Stylist service", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner1, "Sale upto 50% off", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);


        progressDialog.setTitle("Welcome to QucikCart");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();





        //new produds section

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        newProductRecyclerView.setLayoutManager(gridLayoutManager);

        newProductModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getContext(), newProductModelList,(MainActivity) getActivity());
        newProductRecyclerView.setAdapter(newProductsAdapter);

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductModel newProductModel = document.toObject(NewProductModel.class);
                                // Log the data to ensure it's correct
                                Log.d("FirestoreData", "Document data: " + document.getData());
                                newProductModelList.add(newProductModel);
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                            newProductsAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //popular products section

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        popularProductsRecyclerView.setLayoutManager(gridLayoutManager1);

        popularProductModelList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getContext(), popularProductModelList);
        popularProductsRecyclerView.setAdapter(popularProductAdapter);


        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                // Log the data to ensure it's correct
                                Log.d("FirestoreData", "Document data: " + document.getData());
                                popularProductModelList.add(popularProductModel);
                            }
                            popularProductAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return view;
    }
}
