package com.vrushabhhirap.quickcart.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.vrushabhhirap.quickcart.Activity.LoginActivity;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.R;


public class ProfileFragment extends Fragment {

    Button btn_logout;
    private FirebaseAuth auth;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);



        auth = FirebaseAuth.getInstance();

        btn_logout = view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

            }
        });

        return view;


    }
}