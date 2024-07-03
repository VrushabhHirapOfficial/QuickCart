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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.vrushabhhirap.quickcart.Activity.MainActivity;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragment;

import com.bumptech.glide.Glide;
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragment;
import com.vrushabhhirap.quickcart.Fragment.ProfileFragment;
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

        Log.d("AdapterData", "Position: " + position + ", Name: " + product.getName() + ", Image URL: " + product.getImg_url());


        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newimg);
        holder.newName.setText(product.getName() != null ? product.getName() : "No Name");
        holder.newPrice.setText(String.valueOf(product.getPrice()));
        holder.newRating.setText(list.get(position).getRating());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("hello", "onClick: ");

                mainActivity.loadFragment_for_detailedproduct(new DetailedProductOverViewFragment(),false);

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
