package com.vrushabhhirap.quickcart.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.vrushabhhirap.quickcart.Activity.LoginActivity;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.R;


public class ProfileFragment extends Fragment {


    MaterialButton YourOrders,BuyAgain,YourAccount,YourList,YourAddresses,CustomerSupport,ReturnToHomePage;
    MainActivity mainActivity;

    public ProfileFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        YourOrders = view.findViewById(R.id.YourOrders);
        BuyAgain = view.findViewById(R.id.BuyAgain);
        YourAccount = view.findViewById(R.id.YourAccount);
        YourList = view.findViewById(R.id.YourList);
        YourAddresses = view.findViewById(R.id.YourAddresses);
        CustomerSupport = view.findViewById(R.id.CustomerSupport);
        ReturnToHomePage = view.findViewById(R.id.ReturnToHomePage);

        YourOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new ProfileFragment_YourOrdersYourListBuyAgainFragment(),true);
            }
        });
        BuyAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new ProfileFragment_YourOrdersYourListBuyAgainFragment(),true);
            }
        });
        YourAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new ProfileFragment_YourAccountFragment(),true);


            }
        });
        YourList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new ProfileFragment_YourOrdersYourListBuyAgainFragment(),true);
            }
        });
        YourAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new ProfileFragment_YourAddressesFragment(),true);


            }
        });
        CustomerSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new Profile_Fragment_CustomerSupportFragment(),true);


            }
        });
        ReturnToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadFragment(new HomeFragment(),false);
                mainActivity.updateBottomNavigationView(R.id.navigation_home);


            }
        });




        return view;


    }
}