package com.vrushabhhirap.quickcart.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.R;

import java.util.HashMap;
import java.util.Map;

public class ProfileAddAddresses extends Fragment {

    TextInputEditText name,address,city,postalCode,phoneNumber;
    MaterialButton AddAddress;

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    MainActivity mainActivity;


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
         View view = inflater.inflate(R.layout.fragment_profile_add_addresses, container, false);

         name = view.findViewById(R.id.ete_name);
         address = view.findViewById(R.id.addresslane);
         city = view.findViewById(R.id.ete_city);
         postalCode = view.findViewById(R.id.ete_postalcode);
         phoneNumber = view.findViewById(R.id.ete_phoneno);

        AddAddress = view.findViewById(R.id.AddAddress);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        AddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userAddress = address.getText().toString();
                String userCity = city.getText().toString();
                String userPostalCode = postalCode.getText().toString();
                String userPhoneNumber = phoneNumber.getText().toString();


                String finalAddress = "";

                if (!userName.isEmpty()){
                    finalAddress += userName;
                }
                if (!userAddress.isEmpty()){
                    finalAddress += userAddress;
                }
                if (!userCity.isEmpty()){
                    finalAddress += userCity;
                }
                if (!userPostalCode.isEmpty()){
                    finalAddress += userPostalCode;
                }
                if (!userPhoneNumber.isEmpty()){
                    finalAddress += userPhoneNumber;
                }
                if (!userName.isEmpty() &&
                        !userAddress.isEmpty()&&
                        !userCity.isEmpty()&&
                        !userPostalCode.isEmpty()&&
                        !userPhoneNumber.isEmpty()){

                    Map<String,String> map = new HashMap<>();
                    map.put("userAddress",finalAddress);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getContext(), "Address Added", Toast.LENGTH_SHORT).show();
                                        mainActivity.loadFragment_for_detailedproduct(new ProfileFragment_YourAddressesFragment(),true);
                                    }
                                }
                            });




                }else {
                    Toast.makeText(getContext(), "Kindly Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });









         return view;
    }
}