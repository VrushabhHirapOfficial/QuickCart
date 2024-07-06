package com.vrushabhhirap.quickcart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragmentPopularProduct;
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder>  {



    private Context context;
    MainActivity mainActivity;
    private List<SearchViewModel> list;


    public SearchViewAdapter(Context context, List<SearchViewModel> list,MainActivity mainActivity) {
        this.context = context;
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public SearchViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewAdapter.ViewHolder holder, int position) {
        SearchViewModel searchViewModel = list.get(position);
        holder.bind(searchViewModel);

        SearchViewModel product = list.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailedProductOverViewFragmentPopularProduct fragment = DetailedProductOverViewFragmentPopularProduct.newInstance(
                        product.getImg_url(),
                        product.getName(),
                        product.getRating(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getType()
                );
                mainActivity.loadFragment_for_detailedproduct(fragment, true);
            }
        });


        //Add to cart button on click
        holder.AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Add to cart here is work in progress...", Toast.LENGTH_SHORT).show();
//                DetailedProductOverViewFragmentPopularProduct fragment = new DetailedProductOverViewFragmentPopularProduct();
//                fragment.setAddToCartListener(SearchViewAdapter.this);
//                fragment.addToCart();

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView mname,mprice;
        MaterialButton AddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_imginsearch);
            mname = itemView.findViewById(R.id.tv_name_item);
            mprice = itemView.findViewById(R.id.tv_price_item);
            AddToCart = itemView.findViewById(R.id.addtocart);
        }

        public void bind(SearchViewModel searchViewModel) {
            Glide.with(context).load(searchViewModel.getImg_url()).into(itemImage);
            mname.setText(searchViewModel.getName());
            mprice.setText("₹"+(searchViewModel.getPrice()));
        }
    }


}
