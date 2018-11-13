package com.knstech.apnaopd.Patient.ADAPTORS;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.Doctor.Doctor;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
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
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctors_single_layout,viewGroup,false);
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
        private TextView name,fee,dept;
        private LinearLayout linearlayout;
        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=view.findViewById(R.id.docters_card_name);
            fee=view.findViewById(R.id.textView2);
            dept=view.findViewById(R.id.doctors_card_department);
            linearlayout=view.findViewById(R.id.dayLinear);


        }
        public void bind(final Doctor doctor)
        {
            name.setText(doctor.getName());
            fee.setText("Rs."+doctor.getFee());
            dept.setText(doctor.getDepartment());

            for(int i=1;i<=7;i++)
            {
                int lock=0;
                View v=linearlayout.getChildAt(i-1);
                String ar[]= C.getStringArray(doctor.getTimeSlab());
                if(ar!=null) {
                    for (int j = 0; j < ar.length; j++) {
                        if (ar[j].charAt(0) - '0' == i) {
                            lock = 1;
                        }
                    }
                    if (lock != 0) {
                        final int finalI = i;
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onDoctorItemClick(doctor.getGid(), finalI);
                            }
                        });
                    } else {
                        v.setEnabled(false);
                    }
                }
            }
        }


    }


}
