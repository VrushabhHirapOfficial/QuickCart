package com.vrushabhhirap.quickcart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.vrushabhhirap.quickcart.Model.NewProductModel;
import com.vrushabhhirap.quickcart.R;

public class DetailedProductOverViewFragmentNewProduct extends Fragment {


    ImageView DetailedImage;
    TextView rating, name, description, price;
    MaterialButton addtothecart, buynow;
    ImageView additems, removeitems;


    NewProductModel product;

    private String productId; // or any other data type you want to pass

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_product_over_view, container, false);


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





    return view;
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