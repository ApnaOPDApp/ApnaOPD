package com.knstech.apnaopd.Patient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.R;

import java.util.List;

public class PreviousAppointmentsAdapter extends RecyclerView.Adapter {

    public PreviousAppointmentsAdapter(List<Patient> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    private List<Patient> mList;
    private Context mContext;
    private View view;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view= LayoutInflater.from(mContext).inflate(R.layout.d_appointment_item_layout,null);
        return new PreviousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((PreviousViewHolder)viewHolder).bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class PreviousViewHolder extends RecyclerView.ViewHolder{

        TextView name,fee,department;
        Button changeStatus,cancel;

        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            fee=itemView.findViewById(R.id.fee);
            department=itemView.findViewById(R.id.age);
            changeStatus=itemView.findViewById(R.id.diagnosed);
            cancel=itemView.findViewById(R.id.cancel);
            changeStatus.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
        }
        public void bind(Patient patient)
        {
            name.setText(patient.getDoctor_name());
            fee.setText(patient.getFee());
            department.setText(patient.getDepartment());
        }
    }
}
