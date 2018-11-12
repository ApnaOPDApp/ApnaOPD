package com.knstech.apnaopd.Patient.ADAPTORS;

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

public class OfferDetailAdapter extends RecyclerView.Adapter {

    private List<Qutotation> mList;
    private Context mContext;
    private View view;

    public OfferDetailAdapter(List<Qutotation> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(mContext).inflate(R.layout.medicine_details,null);
        return new OfferHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((OfferHolder)viewHolder).bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class OfferHolder extends RecyclerView.ViewHolder{

        TextView med_name,med_price,med_offer_price,med_dosage_per,med_dosage_day;

        public OfferHolder(@NonNull View itemView) {
            super(itemView);

            med_name=(TextView)itemView.findViewById(R.id.med_name1);
            med_price=(TextView)itemView.findViewById(R.id.med_price1);
            med_offer_price=(TextView)itemView.findViewById(R.id.med_offer_price1);
            med_dosage_per=(TextView)itemView.findViewById(R.id.dosage_per1);
            med_dosage_day=(TextView)itemView.findViewById(R.id.dosage_day1);

        }
        public void bind(Qutotation quotation)
        {
            med_name.setText("Drug Name : "+quotation.getMedicine());
            med_price.setText("Price : "+quotation.getPrice());
            med_offer_price.setText("Offer Price : "+quotation.getOffered_price());
            med_dosage_per.setText("Dosage Per Day : "+quotation.getDosage_per());
            med_dosage_day.setText("Doses for no. of days :"+quotation.getDosage_day());

        }
    }
}
