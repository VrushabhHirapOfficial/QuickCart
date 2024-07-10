package com.vrushabhhirap.quickcart.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Model.NewProductModel;
import com.vrushabhhirap.quickcart.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedProductOverViewFragmentNewProduct extends Fragment {


    ImageView DetailedImage;
    TextView rating, name, description, price,quantity;
    MaterialButton addtothecart, buynow;
    ImageView additems, removeitems;

    int totalQuantity = 1;
    int totalPrice;
    MainActivity mainActivity;


    NewProductModel product;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    private String productId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get reference to the MainActivity
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_product_over_view, container, false);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
//        addToCart = new AddToCart(firestore, auth);


        quantity = view.findViewById(R.id.quantity);
        DetailedImage = view.findViewById(R.id.product_image);
        rating = view.findViewById(R.id.rating);
        name = view.findViewById(R.id.product_name);
        description = view.findViewById(R.id.description_of_the_product);
        price = view.findViewById(R.id.price_of_the_product);
        addtothecart = view.findViewById(R.id.addtocart);
        buynow = view.findViewById(R.id.buynow);
        additems = view.findViewById(R.id.add_item);
        removeitems = view.findViewById(R.id.subtract_item);

        if (getArguments() != null) {
            product = new NewProductModel(
                    getArguments().getString("description"),
                    getArguments().getString("Img_url"),
                    getArguments().getString("name"),
                    getArguments().getString("rating"),
                    getArguments().getInt("price")
            );

        }

        Glide.with(getContext()).load(product.getImg_url()).into(DetailedImage);
        name.setText(product.getName());
        rating.setText(product.getRating());
        price.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());

        totalPrice = product.getPrice() * totalQuantity;

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("totalBill", product.getPrice());
                mainActivity.loadFragment_for_going_to_payment(new ProfileFragment_YourAddressesFragment(),true,bundle);
            }
        });



        addtothecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
//                cartManager.addToCart(description.getText().toString(), rating.getText().toString(), product.getImg_url(), name.getText().toString(), product.getPrice(), Integer.parseInt(quantity.getText().toString()), totalPrice);
            }
        });

        additems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if(product!= null){
                        totalPrice = product.getPrice() * totalQuantity;

                    }



                }

            }
        });

        removeitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }

            }
        });


    return view;
    }

    private void addToCart(){
        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();


        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());


        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("ProductDescription", product.getDescription());
        cartMap.put("ProductRating", product.getRating());


        cartMap.put("ProductImage", product.getImg_url());
        cartMap.put("ProductName",name.getText().toString());
        cartMap.put("ProductPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getContext(), "Added To Cart", Toast.LENGTH_SHORT).show();


                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).navigateToCart();
                        }


                    }
                });


    }

    // Static method to create a new instance of the fragment with data
    public static DetailedProductOverViewFragmentNewProduct newInstance(String Img_url, String name, String rating, int price, String description) {
        DetailedProductOverViewFragmentNewProduct fragment = new DetailedProductOverViewFragmentNewProduct();
        Bundle args = new Bundle();
        args.putString("Img_url", Img_url);
        args.putString("name", name);
        args.putString("rating", rating);
        args.putInt("price", price);
        args.putString("description", description);
        fragment.setArguments(args);
        return fragment;
    }
}