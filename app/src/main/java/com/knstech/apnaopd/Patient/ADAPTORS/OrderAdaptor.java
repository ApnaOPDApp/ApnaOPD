package com.knstech.apnaopd.Patient.ADAPTORS;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.knstech.apnaopd.GenModelClasses.User.PatientOrdersList;
import com.knstech.apnaopd.R;

import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.OderClickedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class OrderAdaptor extends RecyclerView.Adapter<OrderAdaptor.OrderAdaptorViewHolder> {

    /*
    List<Qutotation> quotationPojo;
    Context context;

    View view;
*/
    List<PatientOrdersList> quotationPojo;
    Context context;
    OderClickedListener mListener;

    public OrderAdaptor(List<PatientOrdersList> quotationPojo, Context context, OderClickedListener mListener) {
        this.quotationPojo = quotationPojo;
        this.context = context;
        this.mListener = mListener;
    }
    View view;
    @NonNull
    @Override
    public OrderAdaptorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.patient_list_of_quotations_single,viewGroup,false);
        this.view = view;
        return new OrderAdaptorViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdaptorViewHolder orderAdaptorViewHolder, int i) {
       (orderAdaptorViewHolder).bind(quotationPojo.get(i));
    }

    @Override
    public int getItemCount() {
        return quotationPojo.size();
    }

    class OrderAdaptorViewHolder extends RecyclerView.ViewHolder{


        ImageView shop_image, next;
        TextView shop_name, delivery_time;


        public OrderAdaptorViewHolder(@NonNull View itemView) {
            super(itemView);

            shop_image = (ImageView)itemView.findViewById(R.id.patient_quoat_list_retailer_pic);
            next = (ImageView) itemView.findViewById(R.id.quot_list_prescr_offer);
            shop_name=(TextView)itemView.findViewById(R.id.patient_quoat_list_retailer_name);
            delivery_time=(TextView)itemView.findViewById(R.id.patient_quoat_list_delivertime);


        }



            public void bind(final PatientOrdersList order)
            {

                String url = AppUtils.HOST_ADDRESS+"/api/retailers/"+order.getRetailer_gid();
                RequestGet request = new RequestGet(context);
                request.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            shop_name.setText(object.getString("shop_name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onOrderClicked(order,order.get_id());
                    }
                });


                delivery_time.setText("Delivery Charge: "+ order.getDelivery_charge()
                +"\n DeliverTime: "+order.getDelivery_time()+"\n Total Price: "+order.getTotal_price()
                );


            }


    }
}
