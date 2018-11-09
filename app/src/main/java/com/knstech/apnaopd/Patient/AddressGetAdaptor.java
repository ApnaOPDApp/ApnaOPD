package com.knstech.apnaopd.Patient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.User.Address;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Listeners.AddressClickedListener;

import java.util.List;

public class AddressGetAdaptor extends RecyclerView.Adapter<AddressGetAdaptor.AddressGetViewHolder> {

    List<Address> addressPojoList;
    Context context;
    private AddressClickedListener mListener;
    View view;


    public AddressGetAdaptor(List<Address> addressPojoList, Context context, AddressClickedListener mListener) {
        this.addressPojoList = addressPojoList;
        this.context = context;
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public AddressGetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.address_single_layout,viewGroup,false);
        this.view = view;
        return new AddressGetViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddressGetViewHolder addressGetViewHolder, int i) {


        (addressGetViewHolder).bind(addressPojoList.get(i));
    }

    @Override
    public int getItemCount() {
        return addressPojoList.size();
    }


    class AddressGetViewHolder extends RecyclerView.ViewHolder{

        TextView addressSingle;

        public AddressGetViewHolder(@NonNull View itemView) {
            super(itemView);

            addressSingle = (TextView)itemView.findViewById(R.id.tv_address_single_layout);

        }
        public void bind(final Address address)
        {

            addressSingle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onAddressChecked(address);
                }
            });

            addressSingle.setText(address.getFull_name()+address.getHouse_no()+"\n"+address.getLandmark()+address.getLocality()+"\n"+address.getCity()+address.getPincode());
        }
    }



}
