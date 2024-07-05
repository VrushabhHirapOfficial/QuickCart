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
import com.vrushabhhirap.quickcart.Fragment.DetailedProductOverViewFragmentPopularProduct;
import com.vrushabhhirap.quickcart.Fragment.SearchResultFragment;
import com.vrushabhhirap.quickcart.Model.CategoryModel;
import com.vrushabhhirap.quickcart.Model.PopularProductModel;
import com.vrushabhhirap.quickcart.Model.SearchViewModel;
import com.vrushabhhirap.quickcart.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CategoryModel> list;
    private MainActivity mainActivity;

    // Define constants for view types
    private static final int VIEW_TYPE_LEFT = 0;
    private static final int VIEW_TYPE_RIGHT = 1;

    public CategoryAdapter(Context context, List<CategoryModel> list,MainActivity mainActivity) {
        this.context = context;
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getItemViewType(int position) {
        // Alternate view types
        return position % 2 == 0 ? VIEW_TYPE_LEFT : VIEW_TYPE_RIGHT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LEFT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_list_left, parent, false);
            return new LeftViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_list_right, parent, false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryModel categoryModel = list.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_LEFT) {
            ((LeftViewHolder) holder).bind(categoryModel);
        } else {
            ((RightViewHolder) holder).bind(categoryModel);
        }

//        SearchViewModel product = list.get(position);
        CategoryModel product = list.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (product.getType() != null) {
                    SearchResultFragment fragment = SearchResultFragment.newInstance(product.getType());

                    mainActivity.loadFragment_for_detailedproduct(fragment, true);
                } else {
                    Log.e("CategoryAdapter", "Product type is null!");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder {

        ImageView cat_image;
        TextView cat_name;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_image = itemView.findViewById(R.id.cat_img);
            cat_name = itemView.findViewById(R.id.cat_name);
        }

        public void bind(CategoryModel categoryModel) {
            Glide.with(context).load(categoryModel.getImg_url()).into(cat_image);
            cat_name.setText(categoryModel.getType());
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder {

        ImageView cat_image;
        TextView cat_name;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_image = itemView.findViewById(R.id.cat_img);
            cat_name = itemView.findViewById(R.id.cat_name);
        }

        public void bind(CategoryModel categoryModel) {
            Glide.with(context).load(categoryModel.getImg_url()).into(cat_image);
            cat_name.setText(categoryModel.getType());
        }
    }
}
