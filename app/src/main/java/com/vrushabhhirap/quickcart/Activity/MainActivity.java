package com.vrushabhhirap.quickcart.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vrushabhhirap.quickcart.Fragment.CartFragment;
import com.vrushabhhirap.quickcart.Fragment.CategoriesFragment;
import com.vrushabhhirap.quickcart.Fragment.HomeFragment;
import com.vrushabhhirap.quickcart.Fragment.ProfileFragment;
import com.vrushabhhirap.quickcart.R;


public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.navigation_home){

                    loadFragment(new HomeFragment(),true);

                } else if (id == R.id.navigation_categories) {

                    loadFragment(new CategoriesFragment(),false);
                } else if (id == R.id.navigation_cart) {

                    loadFragment(new CartFragment(),false);
                } else if (id == R.id.navigation_profile) {

                    loadFragment(new ProfileFragment(),false);
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Clear any existing fragments
        for (Fragment existingFragment : fragmentManager.getFragments()) {
            if (existingFragment != null) {
                fragmentTransaction.remove(existingFragment);
            }
        }

        // Replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.container, fragment);

        // Add to back stack if required
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        // Commit the transaction
        fragmentTransaction.commit();
    }

    public void loadFragment_for_detailedproduct(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with the new fragment
        fragmentTransaction.add(R.id.container, fragment);


        // Commit the transaction
        fragmentTransaction.commit();
    }


}