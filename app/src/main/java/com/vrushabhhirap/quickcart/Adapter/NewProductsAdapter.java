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

import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragmentNewProduct;

import com.bumptech.glide.Glide;
import com.vrushabhhirap.quickcart.Model.NewProductModel;
import com.vrushabhhirap.quickcart.R;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {

    private Context context;
    private List<NewProductModel> list;
    private MainActivity mainActivity;

    public NewProductsAdapter(Context context, List<NewProductModel> list,MainActivity mainActivity) {
        this.context = context;
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public NewProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductsAdapter.ViewHolder holder, int position) {

        NewProductModel product = list.get(position);

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newimg);
        holder.newName.setText(product.getName() != null ? product.getName() : "No Name");
        holder.newPrice.setText("₹"+(product.getPrice()));
        holder.newRating.setText(list.get(position).getRating());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailedProductOverViewFragmentNewProduct fragment = DetailedProductOverViewFragmentNewProduct.newInstance(
                        product.getImg_url(),
                        product.getName(),
                        product.getRating(),
                        product.getPrice(),
                        product.getDescription()
                );

                mainActivity.loadFragment_for_detailedproduct(fragment, true);

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newimg;
        TextView newName, newPrice,newRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newimg = itemView.findViewById(R.id.item_img);
            newName = itemView.findViewById(R.id.item_name);
            newPrice = itemView.findViewById(R.id.item_price);
            newRating = itemView.findViewById(R.id.rating_tv);
        }
    }

}
