package com.knstech.apnaopd.Retailer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.Patient.User;
import com.knstech.apnaopd.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class RetailerPrescriptionAdaptor extends RecyclerView.Adapter<RetailerPrescriptionAdaptor.RetailerPrescriptionViewHolder>{


   Context context;
   View view;
   User[] data;

   public RetailerPrescriptionAdaptor(Context context, User[] data){
       this.context=context;
       this.data = data;

   }

    @NonNull
    @Override
    public RetailerPrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.retailer_prescription_single_layout,viewGroup,false);
       this.view = view;
       return new RetailerPrescriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetailerPrescriptionViewHolder retailerPrescriptionViewHolder, int i) {

       User userdata = data[i];
        retailerPrescriptionViewHolder.displayName.setText(userdata.getName());
        retailerPrescriptionViewHolder.displayAdd.setText(userdata.getEmail());

        Glide.with(retailerPrescriptionViewHolder.propic.getContext()).load(userdata.getImageUrl())
                .into(retailerPrescriptionViewHolder.propic);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RetailerPrescriptionViewHolder extends RecyclerView.ViewHolder{

        CircleImageView propic;
        TextView displayName;
        TextView displayAdd;

        public RetailerPrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            propic = (CircleImageView)itemView.findViewById(R.id.retailer_list_prescr_patient_pic);
            displayName = (TextView)itemView.findViewById(R.id.retailer_list_prescr_patient_name);
            displayAdd=(TextView)itemView.findViewById(R.id.retailer_list_prescr_address);


        }
    }
}
