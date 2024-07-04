package com.vrushabhhirap.quickcart.Activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

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

    ImageView iv_baglogo;
    TextView QuickCart;
    ImageView app_logo;






    public void updateBottomNavigationView(int itemId) {
        bottomNavigationView.setSelectedItemId(itemId);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Keyboad handling
        View mainLayout = findViewById(R.id.main); // Replace with your main layout ID

        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Determine keyboard visibility and adjust layout if needed
                Rect r = new Rect();
                mainLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = mainLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    // Keyboard is hidden, restore your layout
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });

        //Hnadling the bag

        iv_baglogo = findViewById(R.id.iv_baglogo);



        iv_baglogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CartFragment(),false);
                updateBottomNavigationView(R.id.navigation_cart);
            }
        });

        //Handling the quickcart name

        QuickCart = findViewById(R.id.QuickCart);
        QuickCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment(),false);
                updateBottomNavigationView(R.id.navigation_home);
            }
        });

        //Handling the image view of qucikcart

        app_logo = findViewById(R.id.app_logo);
        app_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment(),false);
                updateBottomNavigationView(R.id.navigation_home);
            }
        });



        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.navigation_home){

                    loadFragment(new HomeFragment(),false);

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

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }else {

        }
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

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
        fragmentTransaction.replace(R.id.container, fragment);

        // Add to back stack if required
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        // Commit the transaction
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        count = count+1;
        if (count > 0) {
            fragmentManager.popBackStack(); // Navigate back to the previous fragment
        } else {
            finish(); // Default back button behavior (finish activity)
        }
    }
}