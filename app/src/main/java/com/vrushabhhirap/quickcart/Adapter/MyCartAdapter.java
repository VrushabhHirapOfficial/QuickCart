package com.vrushabhhirap.quickcart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;
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
    FirebaseAuth auth;
    private LocalBroadcastManager localBroadcastManager;
    private MainActivity mainActivity;

    public MyCartAdapter(Context context, List<myCartModel> list, MainActivity mainActivity, FirebaseAuth auth) {
        this.context = context;
        this.list = list;
        this.mainActivity = mainActivity;
        this.auth = auth;
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item_view, parent, false);
        return new MyCartAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        myCartModel product = list.get(position);

        holder.ProductName.setText(""+product.getProductName());
        holder.ProductPrice.setText("₹" + (product.getProductPrice()));
        holder.totalQuantity.setText((""+product.getTotalQuantity()));
        holder.totalPrice.setText("₹" + product.getTotalPrice());
        Glide.with(context).load(product.getProductImage()).into(holder.ProductImage);

        // Total amount pass to the cart activity
        totalAmount = 0;
        for (myCartModel item : list) {
            totalAmount += item.getTotalPrice();
        }
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalAmount);
        localBroadcastManager.sendBroadcast(intent);

        // Remove button in the cart
        holder.remove_item_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();

//                int position = holder.getAdapterPosition();
//                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//
//                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
//                        .collection("User").document(list.get(position).getDocumentId())
//                        .delete()
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    list.remove(position);
//                                    notifyItemRemoved(position);
//                                    Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(context, "Failed to remove item from cart", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
            }
        });

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
        TextView ProductName, ProductPrice, totalQuantity;
        TextView totalPrice;
        ImageView ProductImage;
        MaterialButton remove_item_cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            remove_item_cart = itemView.findViewById(R.id.remove_item_cart_btn);
            ProductImage = itemView.findViewById(R.id.item_imginsearch);
            ProductName = itemView.findViewById(R.id.tv_name_item);
            ProductPrice = itemView.findViewById(R.id.tv_price_item);
            totalQuantity = itemView.findViewById(R.id.quantity);
            totalPrice = itemView.findViewById(R.id.dispalytotalpricewithquantity);
        }
    }
}
