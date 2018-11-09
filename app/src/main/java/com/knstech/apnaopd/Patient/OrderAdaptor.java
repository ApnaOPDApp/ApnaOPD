package com.knstech.apnaopd.Patient;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.GenModelClasses.User.PatientOrdersList;
import com.knstech.apnaopd.GenModalClasses.User.Qutotation;
import com.knstech.apnaopd.R;

import com.knstech.apnaopd.Utils.Listeners.OderClickedListener;

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


     //   TextView med_name,med_price,med_offer_price,med_dosage_per,med_dosage_day;

        public OrderAdaptorViewHolder(@NonNull View itemView) {
            super(itemView);

            shop_image = (ImageView)itemView.findViewById(R.id.patient_quoat_list_retailer_pic);
            next = (ImageView) itemView.findViewById(R.id.quot_list_prescr_offer);
            shop_name=(TextView)itemView.findViewById(R.id.patient_quoat_list_retailer_name);
            delivery_time=(TextView)itemView.findViewById(R.id.patient_quoat_list_delivertime);

        /*        med_name=(TextView)itemView.findViewById(R.id.med_name1);
            med_price=(TextView)itemView.findViewById(R.id.med_price1);
            med_offer_price=(TextView)itemView.findViewById(R.id.med_offer_price1);
            med_dosage_per=(TextView)itemView.findViewById(R.id.dosage_per1);
            med_dosage_day=(TextView)itemView.findViewById(R.id.dosage_day1);

         */
        }



            public void bind(final PatientOrdersList order)
            {


                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onOrderClicked(order);
                    }
                });

                delivery_time.setText("Delivery Charge: "+ order.getDelivery_charge()
                +"\n DeliverTime: "+order.getDelivery_time()
                );


                /*     med_name.setText(quotation.getMedicine());
                    med_price.setText(quotation.getPrice());
                    med_offer_price.setText(quotation.getOffered_price());
                    med_dosage_per.setText(quotation.getDosage_per());
                    med_dosage_day.setText(quotation.getDosage_day());
           */
            }


    }
}
