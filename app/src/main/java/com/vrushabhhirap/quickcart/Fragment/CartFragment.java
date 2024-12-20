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
import android.widget.Toast;

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

    private static final String TAG_FRAGMENT = "CartFragment";
    private static final String TAG_FIREBASE = "Firebase";
    private static final String TAG_BROADCAST = "BroadcastReceiver";


    RecyclerView recyclerView;
    List<myCartModel> cartModelList;
    MyCartAdapter myCartAdapter;
    FirebaseFirestore firestore;
    MaterialButton ProceedToPayment_btn;

    MainActivity mainActivity;

    TextView ItemsTotal;
    TextView DeliveryCharges;
    TextView allTotal;
    int totalBill;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
        Log.d(TAG_FRAGMENT, "onCreate called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        Log.d(TAG_FRAGMENT, "onCreateView called");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.mycartitems_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getContext(), cartModelList, mainActivity, auth);
        recyclerView.setAdapter(myCartAdapter);

        ItemsTotal = view.findViewById(R.id.ItemsTotal);
        DeliveryCharges = view.findViewById(R.id.DeliveryCharges);
        allTotal = view.findViewById(R.id.allTotal);


        ProceedToPayment_btn = view.findViewById(R.id.ProceedToPayment_btn);
        ProceedToPayment_btn.setOnClickListener(v -> {
            Log.d(TAG_FRAGMENT, "ProceedToPayment_btn clicked");
            Bundle bundle = new Bundle();
            if(totalBill>99){
                bundle.putInt("totalBill", totalBill);
                mainActivity.loadFragment_for_going_to_payment(new ProfileFragment_YourAddressesFragment(), true,bundle);
            }else {
                Toast.makeText(mainActivity, "No items in the cart", Toast.LENGTH_SHORT).show();
            }
            


        });

        // Registering BroadcastReceiver
        LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));
        Log.d(TAG_BROADCAST, "BroadcastReceiver registered for MyTotalAmount");

        if (auth.getCurrentUser() != null) {
            Log.d(TAG_FIREBASE, "User is logged in");
            firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                    .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    myCartModel myCartModel1 = documentSnapshot.toObject(myCartModel.class);
                                    //EDIT BY HARI -- 9-July -- START
                                    //Issue is "documentSnapshot.getLong()", instead we need to use "documentSnapshot.getString()" as its already string in database.
                                    //remove the tempTotalQuantity & set total quantity as below.

                                    myCartModel1.setTotalQuantity(documentSnapshot.getString("totalQuantity"));

                                    //EDIT BY HARI -- 9-July -- END
                                    cartModelList.add(myCartModel1);
                                    myCartAdapter.notifyDataSetChanged();
                                }

                            } else {
                                Log.e(TAG_FIREBASE, "Error getting documents: ", task.getException());
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG_FIREBASE, "Firestore query failed", e);
                        }
                    });
        } else {
            Log.e(TAG_FIREBASE, "User not logged in");
        }

        return view;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG_BROADCAST, "BroadcastReceiver onReceive called");
            totalBill = intent.getIntExtra("totalAmount", 0);
            Log.d(TAG_BROADCAST, "Total amount received: " + totalBill);

            ItemsTotal.setText("₹" + totalBill);

            if (totalBill == 0) {
                ItemsTotal.setText("₹"+totalBill);
                DeliveryCharges.setText("₹0");
                allTotal.setText("₹"+totalBill);

            } else if(totalBill > 500){
                DeliveryCharges.setText("FREE");
                allTotal.setText("₹" + totalBill);
            } else if (totalBill < 500 ) {
                totalBill = totalBill + 99;
                DeliveryCharges.setText("₹" + "99");
                allTotal.setText("₹" + totalBill);
            }


            Log.d(TAG_BROADCAST, "Updated UI with new totals");
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mMessageReceiver);
        Log.d(TAG_BROADCAST, "BroadcastReceiver unregistered");
    }
}
