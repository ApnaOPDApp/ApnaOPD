package com.knstech.apnaopd.Patient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knstech.apnaopd.GenModalClasses.Doctor.Doctor;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Listeners.DoctorItemClickedListener;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private List<Doctor> mList;
    private DoctorItemClickedListener listener;

    public DoctorAdapter(Context mContext, List<Doctor> mList,DoctorItemClickedListener  listener) {
        this.mContext = mContext;
        this.mList = mList;
        this.listener = listener;
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
    public class DoctorHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView name,fee,availability;
        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=view.findViewById(R.id.doctorName);
            fee=view.findViewById(R.id.fee);
            availability=view.findViewById(R.id.availability);
        }
        public void bind(final Doctor doctor)
        {
            name.setText(doctor.getName());
            //availability.setText(doctor.getAvailability());
            fee.setText(doctor.getFee());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDoctorItemClick(doctor.getGid());
                }
            });
        }


    }


}
