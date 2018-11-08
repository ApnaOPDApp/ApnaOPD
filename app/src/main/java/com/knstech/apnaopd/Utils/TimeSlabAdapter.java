package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;
import com.knstech.apnaopd.R;

import java.util.List;

public class TimeSlabAdapter extends RecyclerView.Adapter {

    Context mContext;
    View view;
    List<TimeSlab> mList;

    public TimeSlabAdapter(Context mContext, List<TimeSlab> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view= LayoutInflater.from(mContext).inflate(R.layout.time_slab_item_layout,null);
        return new TimeSlabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((TimeSlabHolder)viewHolder).bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class TimeSlabHolder extends RecyclerView.ViewHolder{
        Switch aSwitch;
        TextView tv;
        EditText editText;
        public TimeSlabHolder(@NonNull View itemView) {
            super(itemView);
            aSwitch=itemView.findViewById(R.id.switchBtn);
            tv=itemView.findViewById(R.id.time);
            editText=itemView.findViewById(R.id.persons);
        }
        public void bind(TimeSlab timeSlab)
        {
            editText.setText(timeSlab.getPatients_per());
            tv.setText(timeSlab.getSl_no());
            if(timeSlab.getAvailable()!=null&&timeSlab.getAvailable().equals("true"))
            {
                aSwitch.setChecked(true);
            }

        }
    }
}
