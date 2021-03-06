package com.knstech.apnaopd.Retailer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.User.PojoUploadPrescription;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Listeners.OnDeclineClickedListener;

import java.util.List;


public class RetailerPrescriptionAdaptor extends RecyclerView.Adapter<RetailerPrescriptionAdaptor.RetailerPrescriptionViewHolder>{


   Context context;
   AppCompatActivity activity;
   View view;
   List<PojoUploadPrescription> data;
    private OnDeclineClickedListener mListener;
    String Url = AppUtils.HOST_ADDRESS+"/api/retailers/orders/"+AppUtils.RET_GID;// /order _id



    public RetailerPrescriptionAdaptor(Context context, List<PojoUploadPrescription> data, AppCompatActivity activity, OnDeclineClickedListener mListener){
       this.context=context;
       this.activity =activity;
       this.data = data;
       this.mListener = mListener;
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
    public void onBindViewHolder(@NonNull final RetailerPrescriptionViewHolder holder, int i) {

        holder.onBind(data.get(i));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RetailerPrescriptionViewHolder extends RecyclerView.ViewHolder{

        ImageView propic;
        TextView displayName;
        TextView displayAdd;
        ImageView offer, decline;

        public RetailerPrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);

            propic = (ImageView)itemView.findViewById(R.id.retailer_list_prescr_patient_pic);
            displayName = (TextView)itemView.findViewById(R.id.retailer_list_prescr_patient_name);
            displayAdd=(TextView)itemView.findViewById(R.id.retailer_list_prescr_address);
            offer = (ImageView) itemView.findViewById(R.id.retailer_list_prescr_offer);
            decline = (ImageView) itemView.findViewById(R.id.retailer_list_prescr_decline);

        }
        public void onBind(final PojoUploadPrescription pojoUploadPrescription){

            if(pojoUploadPrescription.getProfile_image()!=null) {
                Glide.with(context)
                        .load(pojoUploadPrescription.getProfile_image())
                        .into(propic);

            }
            displayName.setText(pojoUploadPrescription.getAddress().getFull_name());
            displayAdd.setText(pojoUploadPrescription.getAddress().getPincode()
                +"\n"+pojoUploadPrescription.getAddress().getFull_name()
                    +"\n"+pojoUploadPrescription.getAddress().getPhone_number()
            );

            offer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,RetailerOfferSendActivity.class);
                    i.putExtra("image_url",pojoUploadPrescription.getPhoto_prescription_link());
                    i.putExtra("pid",pojoUploadPrescription.getPatient_gid());
                    i.putExtra("_id",pojoUploadPrescription.get_id());
                    activity.startActivity(i);
                    activity.finish();
                }
            });

           decline.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mListener.onDecline(pojoUploadPrescription.get_id());
               }
           });

        }

    }
}
