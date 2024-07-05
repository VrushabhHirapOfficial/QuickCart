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
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;

public class DetailedProductOverViewFragmentPopularProduct extends Fragment {

    ImageView DetailedImage;
    TextView rating, name, description, price;
    MaterialButton addtothecart, buynow;
    ImageView additems, removeitems;

    private PopularProductModel popularProduct = null;
    private SearchViewModel searchViewModel;

    private String productType;

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
        return view;

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
