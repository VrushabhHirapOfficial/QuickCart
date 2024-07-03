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
import com.vrushabhhirap.quickcart.Model.NewProductModel;
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.R;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {


    private Context context;
    private List<PopularProductModel> popularProductModelList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public PopularProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelList.get(position).getPrice()));
        holder.newRating.setText(popularProductModelList.get(position).getRating());


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
