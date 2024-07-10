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
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class DetailedProductOverViewFragmentPopularProduct extends Fragment {

//    private AddToCart addToCart;
    ImageView DetailedImage;
    TextView rating, name, description, price,quantity;
    MaterialButton addtothecart, buynow;
    ImageView additems, removeitems;
    String LastTotal;
    MainActivity mainActivity;


    int totalQuantity = 1;
    int totalPrice;

    private PopularProductModel popularProduct = null;
    private SearchViewModel searchViewModel;

    private String productType;


    FirebaseAuth auth;
    private FirebaseFirestore firestore;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get reference to the MainActivity
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_product_over_view, container, false);


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

//        addToCart = new AddToCart(firestore,auth);

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




        productType = getArguments().getString("type");
        popularProduct = new PopularProductModel(
                getArguments().getString("description"),
                getArguments().getString("img_url"),
                getArguments().getString("name"),
                getArguments().getString("rating"),
                getArguments().getInt("price"),
                getArguments().getString("type")
        );
        setProductDetails(popularProduct.getImg_url(), popularProduct.getName(), popularProduct.getRating(), popularProduct.getPrice(), popularProduct.getDescription());

        totalPrice = popularProduct.getPrice() * totalQuantity;


        if (getArguments() != null) {
            productType = getArguments().getString("type");
            if (productType != null) {
                if (productType.equals("Shoes")) {
                    popularProduct = new PopularProductModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(popularProduct.getImg_url(), popularProduct.getName(), popularProduct.getRating(), popularProduct.getPrice(), popularProduct.getDescription());
                } else if (productType.equals("Toys")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }else if (productType.equals("Fashion")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }else if (productType.equals("Electronics")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }else if (productType.equals("Books")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }else if (productType.equals("Watches")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }else if (productType.equals("jewellery")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }else if (productType.equals("Beauty")) {
                    searchViewModel = new SearchViewModel(
                            getArguments().getString("description"),
                            getArguments().getString("img_url"),
                            getArguments().getString("name"),
                            getArguments().getString("rating"),
                            getArguments().getInt("price"),
                            getArguments().getString("type")
                    );
                    setProductDetails(searchViewModel.getImg_url(), searchViewModel.getName(), searchViewModel.getRating(), searchViewModel.getPrice(), searchViewModel.getDescription());
                }
            }
        }

        //buy now
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("totalBill", popularProduct.getPrice());
                mainActivity.loadFragment_for_going_to_payment(new ProfileFragment_YourAddressesFragment(),true,bundle);
            }
        });

        //add to cart
        addtothecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
//                cartManager.addToCart(popularProduct.getDescription(), popularProduct.getRating(), popularProduct.getImg_url(), popularProduct.getName(), popularProduct.getPrice(), Integer.parseInt(quantity.getText().toString()), popularProduct.getPrice());
//                LastTotal = totalAmount + list.get(position).getTotalPrice();
//                cartFragment.updateTotalAmount(totalAmount);


            }
        });

        additems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if(popularProduct!= null){
                        totalPrice = popularProduct.getPrice() * totalQuantity;

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
    public void addToCart(){


        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();


        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());


        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("ProductDescription", description.getText().toString());
        cartMap.put("ProductRating", rating.getText().toString());


        cartMap.put("ProductImage", popularProduct.getImg_url());
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
//                        Toast.makeText(getContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
//
//                        if (getActivity() instanceof MainActivity) {
//                            ((MainActivity) getActivity()).navigateToCart();
//                        }
                        if (task.isSuccessful()) {
                            String documentId = task.getResult().getId();
                            cartMap.put("documentId", documentId);
                            task.getResult().set(cartMap);

                            Toast.makeText(getContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
                            if (getActivity() instanceof MainActivity) {
                                ((MainActivity) getActivity()).navigateToCart();
                            }
                        }

                    }
                });
    }


    private void setProductDetails (String imgUrl, String name, String rating,int price, String
    description){
        Glide.with(requireContext()).load(imgUrl).into(DetailedImage);
        this.name.setText(name);
        this.rating.setText(rating);
        this.price.setText(String.valueOf(price));
        this.description.setText(description);
    }

    // Static method to create a new instance of the fragment with data
    public static DetailedProductOverViewFragmentPopularProduct newInstance (String
    imgUrl, String name, String rating,int price, String description, String type){
        DetailedProductOverViewFragmentPopularProduct fragment = new DetailedProductOverViewFragmentPopularProduct();
        Bundle args = new Bundle();
        args.putString("img_url", imgUrl);
        args.putString("name", name);
        args.putString("rating", rating);
        args.putInt("price", price);
        args.putString("description", description);
        args.putString("type", type);

        fragment.setArguments(args);
        return fragment;
    }

}
