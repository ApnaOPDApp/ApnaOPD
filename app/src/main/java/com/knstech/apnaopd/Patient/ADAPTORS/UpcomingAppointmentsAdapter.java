package com.knstech.apnaopd.Patient.ADAPTORS;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestDelete;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcomingAppointmentsAdapter extends RecyclerView.Adapter {

    private List<Patient> mList;
    private Context mContext;
    private View view;

    public UpcomingAppointmentsAdapter(List list, Context mContext) {
        this.mList = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mContext).inflate(R.layout.d_appointment_item_layout,null);
        return new UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((UpcomingViewHolder)viewHolder).bind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class UpcomingViewHolder extends RecyclerView.ViewHolder{
        private TextView name,department,fee,timestamp;
        private CircleImageView propic;
        private Button cancel,changeStatus;
        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            department=itemView.findViewById(R.id.age);
            fee=itemView.findViewById(R.id.fee);
            propic=itemView.findViewById(R.id.patientPic);
            cancel=itemView.findViewById(R.id.cancel);
            changeStatus=itemView.findViewById(R.id.diagnosed);
            timestamp=itemView.findViewById(R.id.timestamp);
        }
        public void bind(final Patient patient)
        {
            name.setText(patient.getFee());
            department.setText(patient.getDepartment());
            name.setText("Dr. "+patient.getDoctor_name()+"\n");
            Glide.with(mContext).load(patient.getDoctor_image()).into(propic);
            fee.setText("For - "+patient.getPatient_name()+"\nFee : Rs."+patient.getFee());
            if(patient.getStatus().equals("0"))
            {
                changeStatus.setText("Arrived");
                changeStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        patient.setStatus("1");
                        RequestPut put=new RequestPut(mContext);
                        String url= AppUtils.HOST_ADDRESS+"/api/appointments/status/patient/"+patient.getAppointment_id()+"/"+patient.getPatient_gid();
                        put.putJSONObject(url, null, new RequestPut.JSONObjectResponseListener() {
                            @Override
                            public void onResponse(JSONObject object) {
                                Toast.makeText(mContext, "Success!", Toast.LENGTH_SHORT).show();
                                changeStatus.setText("Waiting");
                                changeStatus.setEnabled(false);
                            }
                        });

                    }
                });
            }
            else if(patient.getStatus().equals("1"))
            {
                changeStatus.setText("Waiting");
                changeStatus.setEnabled(false);
            }
            else
            {
                mList.remove(patient);
                notifyDataSetChanged();
            }
            timestamp.setText("Time : "+ C.getDateAndTime(patient.getTime_slab()));
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String url=AppUtils.HOST_ADDRESS+"/api/appointments/"+patient.getAppointment_id();
                    AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
                    dialog.setMessage("Are you sure you want to cancel this appointment?");
                    dialog.setTitle("Confirm Cancellation");
                    dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestDelete delete=new RequestDelete(mContext);
                            delete.requestDelete(url, new RequestDelete.OnDeleteListener() {
                                @Override
                                public void onDelete() {
                                    mList.remove(patient);
                                    UpcomingAppointmentsAdapter.this.notifyDataSetChanged();
                                    Toast.makeText(mContext, "Successfully cancelled Appointment!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();


                }
            });

        }
    }
}
