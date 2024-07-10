//package com.vrushabhhirap.quickcart.Fragment;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.button.MaterialButton;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.vrushabhhirap.quickcart.Activity.MainActivity;
//import com.vrushabhhirap.quickcart.Adapter.AddressAdapter;
//import com.vrushabhhirap.quickcart.Model.AddressModel;
//import com.vrushabhhirap.quickcart.R;
//import com.razorpay.Checkout;
//import com.razorpay.PaymentResultListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import android.content.Context;
//import android.widget.TextView;
//
//public class ProfileFragment_YourAddressesFragment extends Fragment implements AddressAdapter.SelectedAddress{
//
//
//    RecyclerView recyclerView;
//    private List<AddressModel> addressModelList;
//    private AddressAdapter addressAdapter;
//    FirebaseFirestore firestore;
//    FirebaseAuth auth;
//    TextView totalBill_tv;
//    TextView deliveryChargesAreAdded;
//
//    MaterialButton AddAddress,ContinueToPayment;
//    MainActivity mainActivity;
//    String mAddress="";
//    private int totalBill;
//    private Checkout checkout;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getActivity() instanceof MainActivity) {
//            mainActivity = (MainActivity) getActivity();
//        }
//        Bundle bundle = getArguments();
//        if (bundle!= null) {
//            totalBill = bundle.getInt("totalBill");
//
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view =  inflater.inflate(R.layout.fragment_profile__your_addresses, container, false);
//
//        AddAddress = view.findViewById(R.id.AddAddress);
//        ContinueToPayment = view.findViewById(R.id.continue_to_payment);
//        firestore = FirebaseFirestore.getInstance();
//        auth = FirebaseAuth.getInstance();
//        recyclerView = view.findViewById(R.id.address_recycler);
//        totalBill_tv = view.findViewById(R.id.totalBill_tv);
//        deliveryChargesAreAdded = view.findViewById(R.id.deliveryChargesAreAdded);
//
//        if(totalBill > 500 ){
//            totalBill_tv.setText("₹"+totalBill);
//        } else if (totalBill< 500) {
//            totalBill = totalBill + 99;
//            totalBill_tv.setText("₹"+totalBill);
//            deliveryChargesAreAdded.setText("*Delivery Charges of ₹99 are Added*");
//        }
////        checkout = new Checkout(getActivity(), "YOUR_RAZORPAY_KEY_ID");
//
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        addressModelList = new ArrayList<>();
//        addressAdapter= new AddressAdapter(getContext(),addressModelList,this);
//        recyclerView.setAdapter(addressAdapter);
//
//
//        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
//                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for (DocumentSnapshot doc :task.getResult().getDocuments()){
//                                AddressModel addressModel = doc.toObject(AddressModel.class);
//                                addressModelList.add(addressModel);
//                                addressAdapter.notifyDataSetChanged();
//                            }
//                        }
//
//                    }
//                });
//
//
//
//        ContinueToPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainActivity.loadFragment_for_detailedproduct(new Payment(),true);
//
//            }
//        });
//
//        AddAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainActivity.loadFragment_for_detailedproduct(new ProfileAddAddresses(),true);
//            }
//        });
//
//        return view;
//    }
//
//    @Override
//    public void setAddress(String address) {
//
//        mAddress = address;
//    }
//}
package com.vrushabhhirap.quickcart.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Adapter.AddressAdapter;
import com.vrushabhhirap.quickcart.Model.AddressModel;
import com.vrushabhhirap.quickcart.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class ProfileFragment_YourAddressesFragment extends Fragment implements AddressAdapter.SelectedAddress, PaymentResultListener {

    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    TextView totalBill_tv;
    TextView deliveryChargesAreAdded;

    MaterialButton AddAddress, ContinueToPayment;
    MainActivity mainActivity;
    String mAddress = "";
    private int totalBill;
    private Checkout checkout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            totalBill = bundle.getInt("totalBill");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile__your_addresses, container, false);

        AddAddress = view.findViewById(R.id.AddAddress);
        ContinueToPayment = view.findViewById(R.id.continue_to_payment);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.address_recycler);
        totalBill_tv = view.findViewById(R.id.totalBill_tv);
        deliveryChargesAreAdded = view.findViewById(R.id.deliveryChargesAreAdded);

        if (totalBill > 500) {
            totalBill_tv.setText("₹" + totalBill);
        } else if (totalBill < 500) {
            totalBill = totalBill + 99;
            totalBill_tv.setText("₹" + totalBill);
            deliveryChargesAreAdded.setText("*Delivery Charges of ₹99 are Added*");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getContext(), addressModelList, this,mainActivity);
        recyclerView.setAdapter(addressAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                AddressModel addressModel = doc.toObject(AddressModel.class);
                                addressModelList.add(addressModel);
                                addressAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        ContinueToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressModel addressModel = new AddressModel();
                
                if(isAnyAddressSelected()){
                    startPayment();
                }else{
                    Toast.makeText(mainActivity, "Select the address", Toast.LENGTH_SHORT).show();
                }
                
            }
        });


        AddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment_for_detailedproduct(new ProfileAddAddresses(), true);
            }
        });

        return view;
    }
    private boolean isAnyAddressSelected() {
        for (AddressModel address : addressModelList) {
            if (address.isSelected()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setAddress(String address) {
        mAddress = address;
    }

    private void startPayment() {
        checkout = new Checkout();
        checkout.setKeyID("rzp_test_FOUsCeLh8yxItn");

        final MainActivity activity = (MainActivity) getActivity();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "QuickCart");
            options.put("description", "Order Payment");
            options.put("currency", "INR");
            options.put("amount", totalBill * 100); // amount in paise

            checkout.open(activity, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        // Handle payment success
//        mainActivity.loadFragment_for_detailedproduct(new PaymentSuccessFragment(), true);
        Toast.makeText(mainActivity, "The payment is successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int code, String response) {
        // Handle payment failure
//        mainActivity.loadFragment_for_detailedproduct(new PaymentFailureFragment(), true);
        Toast.makeText(mainActivity, "The payment is Failed", Toast.LENGTH_SHORT).show();

    }
}
