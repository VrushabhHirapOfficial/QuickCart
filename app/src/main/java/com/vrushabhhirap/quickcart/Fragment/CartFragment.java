package com.vrushabhhirap.quickcart.Fragment;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Adapter.MyCartAdapter;
import com.vrushabhhirap.quickcart.Model.myCartModel;
import com.vrushabhhirap.quickcart.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    List<myCartModel> cartModelList;
    MyCartAdapter myCartAdapter;
    private FirebaseAuth auth;
    FirebaseFirestore firestore;

   // private BroadcastReceiver broadcastReceiver;

    int totalAmountOfProduct;

    MaterialButton remove_item_cart_btn;

    TextView ItemsTotal;
    TextView DeliveryCharges;
    TextView allTotal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_cart, container, false);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.mycartitems_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getContext(),cartModelList,(MainActivity) getActivity(),auth);
        recyclerView.setAdapter(myCartAdapter);


        ItemsTotal = view.findViewById(R.id.ItemsTotal);
        DeliveryCharges = view.findViewById(R.id.DeliveryCharges);
        allTotal = view.findViewById(R.id.allTotal);


        //getting data of total amount from the mycartadapter
        LocalBroadcastManager.getInstance(getContext())
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));


        if (auth.getCurrentUser() != null){
            firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                    .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {


                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                    myCartModel myCartModel1 = documentSnapshot.toObject(myCartModel.class);
                                    cartModelList.add(myCartModel1);
                                    myCartAdapter.notifyDataSetChanged();

                                }
                            }else {
                                Log.e("CartFragment", "Error getting documents: ", task.getException());
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("CartFragment", "Firestore query failed", e);
                        }


                    });
        }else {
            Log.e("CartFragment", "User not logged in");
        }




        return view;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);
            ItemsTotal.setText("₹"+ totalBill);
            int overalltotal = totalBill + 99;

            if(totalBill>500){
                DeliveryCharges.setText("FREE");
                allTotal.setText("₹"+totalBill);

            }else {
                DeliveryCharges.setText("₹"+"99");
                allTotal.setText("₹"+overalltotal);
            }



        }
    };
}