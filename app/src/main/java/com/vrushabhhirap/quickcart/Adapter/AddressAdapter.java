package com.vrushabhhirap.quickcart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import com.vrushabhhirap.quickcart.Model.AddressModel;
import com.vrushabhhirap.quickcart.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    Context context;
    List<AddressModel> addressModelList;

    SelectedAddress selectedAddress;

    private RadioButton selectedRadioButton;

    public AddressAdapter(Context context, List<AddressModel> addressModelList, SelectedAddress selectedAddress) {
        this.context = context;
        this.addressModelList = addressModelList;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {

        holder.address.setText(addressModelList.get(position).getUserAddress());


        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(AddressModel address: addressModelList){
                    address.setSelected(false);
                }
                addressModelList.get(position).setSelected(true);

                if(selectedRadioButton!=null){
                    selectedRadioButton.setChecked(false);
                }
                selectedRadioButton = (RadioButton) v;
                selectedRadioButton.setSelected(true);
                selectedAddress.setAddress(addressModelList.get(position).getUserAddress());
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address_add);
            radioButton = itemView.findViewById(R.id.select_address);
        }
    }
    public interface SelectedAddress{
        void setAddress(String address);
    }
}
