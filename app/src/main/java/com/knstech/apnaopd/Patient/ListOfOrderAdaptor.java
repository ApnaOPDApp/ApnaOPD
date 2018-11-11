package com.knstech.apnaopd.Patient;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.Retailer.Order;
import com.knstech.apnaopd.R;

import org.w3c.dom.Text;

import java.util.List;

public class ListOfOrderAdaptor extends RecyclerView.Adapter<ListOfOrderAdaptor.ListOfOrderViewHolder> {

    private Context mContext;
    private List<Order> mList;
    private View view;

    public ListOfOrderAdaptor(Context context,List<Order> mList) {
        this.mContext = context;
        this.mList=mList;
    }

    @NonNull
    @Override
    public ListOfOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view= LayoutInflater.from(mContext).inflate(R.layout.order_list_single_layout,null);
        return new ListOfOrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListOfOrderViewHolder listOfOrderViewHolder, int i) {
        listOfOrderViewHolder.bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ListOfOrderViewHolder extends RecyclerView.ViewHolder{

        TextView comment,status;
        ImageView imageView;

        public ListOfOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            comment=itemView.findViewById(R.id.order_comment);
            status=itemView.findViewById(R.id.order_status);
            imageView=itemView.findViewById(R.id.order_show_offer);

        }
        void bind(final Order order)
        {



            int stat=Integer.parseInt(order.getStatus());
            String commentStr;
            switch(stat)
            {
                case 0: commentStr= "Offers sent to you. Please accept the offers.";

                break;
                case 1: commentStr= "Item Packed!";

                break;
                case 2: commentStr= "Item Shipped.";

                break;
                default:commentStr="Item Delivered";

            }
            status.setText(commentStr);
            comment.setText(order.getComment());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListOfOrderActivity activity= (ListOfOrderActivity) mContext;
                    Intent i=new Intent(activity,OrderActivity.class);
                    i.putExtra("_id",order.getOrderId());
                    activity.startActivity(i);
                }
            });
        }
    }
}
