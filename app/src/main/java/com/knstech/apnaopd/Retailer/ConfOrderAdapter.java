package com.knstech.apnaopd.Retailer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.User.Qutotation;
import com.knstech.apnaopd.R;

import java.util.List;

public class ConfOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;


    public ConfOrderAdapter(Context mContext, List<Qutotation> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    private List<Qutotation> mList;
    private View view;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    view= LayoutInflater.from(mContext).inflate(R.layout.medicine_details,null);

        return new ConfOrderHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ConfOrderHolder)viewHolder).bond(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ConfOrderHolder extends RecyclerView.ViewHolder{
        TextView medicine,price,dday,dper,oprice;

        public ConfOrderHolder(@NonNull View itemView) {
            super(itemView);
            medicine=itemView.findViewById(R.id.med_name1);
            price=itemView.findViewById(R.id.med_price1);
            oprice=itemView.findViewById(R.id.med_offer_price1);
            dday=itemView.findViewById(R.id.dosage_day1);
            dper=itemView.findViewById(R.id.dosage_per1);
             }
        public void bond(Qutotation qutotation)
        {
            medicine.setText("Drug Name : "+qutotation.getMedicine());
            price.setText("Price : "+qutotation.getPrice());
            oprice.setText("Offered Price : "+qutotation.getOffered_price());
            dday.setText("No of Day : "+qutotation.getDosage_day());
            dper.setText("No of dose per day : "+qutotation.getDosage_per());

        }
    }

}
