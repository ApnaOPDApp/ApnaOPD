package com.knstech.apnaopd.Patient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knstech.apnaopd.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<Doctor> mList;

    public DoctorAdapter(Context mContext,List<Doctor> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_doctor_layout,viewGroup,false);
        return new DoctorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Doctor doctor=mList.get(i);
        ((DoctorHolder)viewHolder).bind(doctor);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class DoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private View view;
        private TextView name,fee,availibility;
        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=view.findViewById(R.id.doctorName);
            fee=view.findViewById(R.id.fee);
            availibility=view.findViewById(R.id.availability);
        }
        public void bind(Doctor doctor)
        {
            name.setText(doctor.getName());
            availibility.setText(doctor.getAvailability());
            fee.setText(doctor.getFee());
        }

        @Override
        public void onClick(View v) {
            mListener.onDoctorItemClick(v,getAdapterPosition());
        }
    }

    private DoctorClickListener mListener;

    public void setmListener(DoctorClickListener mListener) {
        this.mListener = mListener;
    }

    interface DoctorClickListener {
        void onDoctorItemClick(View view, int position);
    }
}
