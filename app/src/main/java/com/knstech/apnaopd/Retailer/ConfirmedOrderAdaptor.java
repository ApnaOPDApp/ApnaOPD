package com.knstech.apnaopd.Retailer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.knstech.apnaopd.GenModelClasses.Retailer.Offers;
import com.knstech.apnaopd.GenModelClasses.Retailer.PojoConfirmedOrder;
import com.knstech.apnaopd.GenModelClasses.User.Qutotation;
import com.knstech.apnaopd.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedOrderAdaptor extends RecyclerView.Adapter<ConfirmedOrderAdaptor.ConfirmedOrderViewHolder> {


    Context context;
    List<PojoConfirmedOrder> data;


    public ConfirmedOrderAdaptor(Context context, List<PojoConfirmedOrder> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ConfirmedOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.confirmed_order_single_layout,viewGroup,false);
        return new ConfirmedOrderAdaptor.ConfirmedOrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmedOrderViewHolder holder, int i) {
        holder.onBind(data.get(i),i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ConfirmedOrderViewHolder extends RecyclerView.ViewHolder{

        ImageView proPic,next;
        TextView name,address;
        RelativeLayout updateOffer;


        public ConfirmedOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            proPic = (ImageView) itemView.findViewById(R.id.confirm_order_pat_propic);
            next = (ImageView)itemView.findViewById(R.id.confirm_order_next);
            name = (TextView)itemView.findViewById(R.id.confirm_order_patient_name);
            address = (TextView)itemView.findViewById(R.id.confirm_order_address);
            updateOffer = (RelativeLayout)itemView.findViewById(R.id.update_offer);
        }

        public void onBind(final PojoConfirmedOrder pojoConfirmedOrder, final int i){



           List<Qutotation> qutotations =  pojoConfirmedOrder.getOffers().get(i).getQuotation();
           Gson gson = new Gson();
           final String arr[] = new String[qutotations.size()];
           for(int j = 0;j<qutotations.size();j++){
               arr[j] =  gson.toJson(qutotations.get(j));
           }



            Glide.with(context).load(pojoConfirmedOrder.getProfile_image()).into(proPic);
            name.setText(pojoConfirmedOrder.getAddress().getFull_name());
            final String n=pojoConfirmedOrder.getAddress().getCity();
            final String h=pojoConfirmedOrder.getAddress().getHouse_no();
            final String l=pojoConfirmedOrder.getAddress().getLandmark();
            final String lo=pojoConfirmedOrder.getAddress().getLocality();
            final String zip = pojoConfirmedOrder.getAddress().getPincode();

            name.setText(n);
            address.setText(h+"\n"+l+"\n"+lo+"\n"+zip);
            address.setText(n);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,UpdateOrderStatusActivity.class);
                    intent.putExtra("quot",arr);
                    intent.putExtra("total_price",pojoConfirmedOrder.getOffers().get(i).getDelivery_charge());
                    intent.putExtra("delivery_time",pojoConfirmedOrder.getOffers().get(i).getDelivery_time());
                    intent.putExtra("delivery_charge",pojoConfirmedOrder.getOffers().get(i).getDelivery_charge());
                    intent.putExtra("name",pojoConfirmedOrder.getAddress().getFull_name());
                    intent.putExtra("zip",zip);
                    intent.putExtra("locality",lo);
                    intent.putExtra("house_no",h);
                    intent.putExtra("status",pojoConfirmedOrder.getStatus());
                    intent.putExtra("_id",pojoConfirmedOrder.get_id());

                    context.startActivity(intent);
                }
            });
        }
    }
}
