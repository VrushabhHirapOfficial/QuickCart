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

import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public class ProfileFragment_YourAddressesFragment extends Fragment implements AddressAdapter.SelectedAddress{


    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    MaterialButton AddAddress,ContinueToPayment;
    MainActivity mainActivity;
    String mAddress="";

//    public void onAttach(Context context){
//        super.onAttach(context);
//        this.context = context;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile__your_addresses, container, false);

        AddAddress = view.findViewById(R.id.AddAddress);
        ContinueToPayment = view.findViewById(R.id.continue_to_payment);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.address_recycler);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addressModelList = new ArrayList<>();
        addressAdapter= new AddressAdapter(getContext(),addressModelList,this);
        recyclerView.setAdapter(addressAdapter);


        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc :task.getResult().getDocuments()){
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
                mainActivity.loadFragment_for_detailedproduct(new Payment(),true);

            }
        });

        AddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment_for_detailedproduct(new ProfileAddAddresses(),true);
            }
        });

        return view;
    }

    @Override
    public void setAddress(String address) {

        mAddress = address;
    }
}