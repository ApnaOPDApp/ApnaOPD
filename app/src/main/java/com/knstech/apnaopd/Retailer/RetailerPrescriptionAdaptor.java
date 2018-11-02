package com.knstech.apnaopd.Retailer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.PojoUploadPrescription;
import com.knstech.apnaopd.Patient.User;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Volley.NetworkJSONLoader;
import com.knstech.apnaopd.Volley.NetworkStringLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RetailerPrescriptionAdaptor extends RecyclerView.Adapter<RetailerPrescriptionAdaptor.RetailerPrescriptionViewHolder>{


   Context context;
   View view;
   List<PojoUploadPrescription> data;
    private String name;
    private String imgUrl;

    public RetailerPrescriptionAdaptor(Context context,  List<PojoUploadPrescription> data){
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
    public void onBindViewHolder(@NonNull final RetailerPrescriptionViewHolder retailerPrescriptionViewHolder, int i) {

       final PojoUploadPrescription userdata = data.get(i);
        String patient_gid = userdata.getPatient_gid();

        String url = AppUtils.HOST_ADDRESS+"/api/users/"+patient_gid;
        name="";
        imgUrl="";
        RequestGet requestGet=new RequestGet(context);
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject jsonObj=jsonArray.getJSONObject(i);
                            name = (String) jsonObj.get("name");
                            imgUrl = (String) jsonObj.get("imageUrl");
                        retailerPrescriptionViewHolder.displayName.setText(name);

                        Glide.with(retailerPrescriptionViewHolder.propic.getContext()).load(imgUrl)
                                .into(retailerPrescriptionViewHolder.propic);
                        retailerPrescriptionViewHolder.displayAdd.setText(userdata.getAddress().toString());


                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
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
