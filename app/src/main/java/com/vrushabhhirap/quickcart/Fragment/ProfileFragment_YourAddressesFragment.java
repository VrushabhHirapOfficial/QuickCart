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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Adapter.AddressAdapter;
import com.vrushabhhirap.quickcart.Adapter.SearchViewAdapter;
import com.vrushabhhirap.quickcart.Model.AddressModel;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

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
                Bundle bundle = new Bundle();
                bundle.putInt("totalBill", totalBill);
                mainActivity.loadFragment_for_going_to_payment(new ProfileAddAddresses(), true,bundle);

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

            // Generate a unique order ID
            String orderId = UUID.randomUUID().toString();

            // Retrieve the user's email (Replace this with your actual user retrieval logic)
            String userEmail = getUserEmail();


            JSONObject options = new JSONObject();
            options.put("name", "QuickCart");
            options.put("description", "Order Payment");
            options.put("currency", "INR");
            options.put("amount", totalBill * 100);// amount in paise
//            options.put("order_id", orderId);
//            options.put("prefill.email", userEmail);

            checkout.open(activity, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Retrieve the user's email from Firebase Authentication
    private String getUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null ? user.getEmail() : "no-email@example.com";
    }


    public void onPaymentSuccess(String razorpayPaymentID) {
        clearCart();
//        addToYourOrders();
        mainActivity.loadFragment_for_detailedproduct(new CartFragment(),false);
        Toast.makeText(mainActivity, "Order Placed", Toast.LENGTH_SHORT).show();

    }

    public void onPaymentError(int code, String response) {
        Toast.makeText(mainActivity, "The payment is Failed", Toast.LENGTH_SHORT).show();
    }

    private void clearCart() {
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                                        .collection("User").document(document.getId()).delete();
                            }
                            mainActivity.loadFragment_for_detailedproduct(new CartFragment(),false);
                        } else {
                            Toast.makeText(getContext(), "Failed to clear cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addToYourOrders() {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        // Add the order details to the cartMap HashMap
        // ...

        firestore.collection("YourOrders").document(auth.getCurrentUser().getUid())
                .collection("User")
                .add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            String documentId = task.getResult().getId();
                            cartMap.put("documentId", documentId);
                            task.getResult().set(cartMap);

                            Toast.makeText(getContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();

                            // Clear the cart after the order is placed
                            clearCart();

                            // Navigate to the "Your Orders" fragment
                            mainActivity.loadFragment_for_detailedproduct(new SearchResultFragment(), false);
                        } else {
                            Toast.makeText(getContext(), "Failed to place order", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
