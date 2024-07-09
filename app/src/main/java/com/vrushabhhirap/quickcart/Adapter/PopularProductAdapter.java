package com.vrushabhhirap.quickcart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragmentNewProduct;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragmentPopularProduct;
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {


    private Context context;
    private List<PopularProductModel> popularProductModelList;
    private MainActivity mainActivity;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList,MainActivity mainActivity) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public PopularProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter.ViewHolder holder, int position) {

        PopularProductModel product = popularProductModelList.get(position);

        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText("₹"+(popularProductModelList.get(position).getPrice()));
        holder.newRating.setText(popularProductModelList.get(position).getRating());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailedProductOverViewFragmentPopularProduct fragment = DetailedProductOverViewFragmentPopularProduct.newInstance(
                        product.getImg_url(),
                        product.getName(),
                        product.getRating(),
                        (product.getPrice()),
                        product.getDescription(),
                        product.getType()
                );

                mainActivity.loadFragment_for_detailedproduct(fragment, true);

            }
        });


    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,newRating;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.all_item_img);
            name = itemView.findViewById(R.id.all_item_name);
            price = itemView.findViewById(R.id.all_item_price);
            newRating = itemView.findViewById(R.id.rating_tv);
        }
    }
}
