package com.vrushabhhirap.quickcart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Fragment.CartFragment;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragmentPopularProduct;
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.Model.myCartModel;
import com.vrushabhhirap.quickcart.R;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<myCartModel> list;
    int totalAmount = 0;

    private MainActivity mainActivity;

    public MyCartAdapter(Context context, List<myCartModel> list,MainActivity mainActivity) {
        this.context = context;
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item_view, parent, false);
        return new MyCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {

        myCartModel product = list.get(position);

        holder.ProductName.setText(list.get(position).getProductName());
        holder.ProductPrice.setText("₹"+list.get(position).getProductPrice());
        holder.totalQuantity.setText(list.get(position).getTotalQuantity());
        holder.totalPrice.setText("₹"+(list.get(position).getTotalPrice()));
        Glide.with(context).load(list.get(position).getProductImage()).into(holder.ProductImage);

        //Total Amount passes to the cartFragment



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailedProductOverViewFragmentPopularProduct fragment = DetailedProductOverViewFragmentPopularProduct.newInstance(
                        product.getProductImage(),
                        product.getProductName(),
                        product.getProductRating(),
                        Integer.parseInt(product.getProductPrice()),
                        product.getProductDescription(),
                        product.getProductType()
                );

                if (mainActivity != null) {
                    mainActivity.loadFragment_for_detailedproduct(fragment, true);
                } else {
                    Log.e("MyCartAdapter", "mainActivity is null");
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ProductName,ProductPrice,totalQuantity;
        TextView totalPrice;
        ImageView ProductImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            ProductImage = itemView.findViewById(R.id.item_imginsearch);
            ProductName = itemView.findViewById(R.id.tv_name_item);
            ProductPrice = itemView.findViewById(R.id.tv_price_item);
            totalQuantity = itemView.findViewById(R.id.quantity);
            totalPrice = itemView.findViewById(R.id.dispalytotalpricewithquantity);


        }
    }
}
