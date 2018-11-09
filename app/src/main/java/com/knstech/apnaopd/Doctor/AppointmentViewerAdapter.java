package com.knstech.apnaopd.Doctor;

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
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModelClasses.User.User;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestDelete;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentViewerAdapter extends RecyclerView.Adapter {
    private List<Patient> mList;
    private Context mContext;
    private OnUserClickedListener mListener;

    public AppointmentViewerAdapter(List<Patient> mList, Context mContext,OnUserClickedListener mListener)
    {

        this.mList = mList;
        this.mContext = mContext;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.d_appointment_item_layout,null);
        return new PatientHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Patient patient=mList.get(i);
        ((PatientHolder)viewHolder).bind(patient);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class PatientHolder extends RecyclerView.ViewHolder{
        private final View view;
        CircleImageView pic;
        TextView name,age,timestamp;
        Button changeStatus,cancel;
        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            pic=itemView.findViewById(R.id.patientPic);
            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            changeStatus=itemView.findViewById(R.id.diagnosed);
            cancel=itemView.findViewById(R.id.cancel);
            timestamp=itemView.findViewById(R.id.timestamp);

        }
        public void bind(final Patient patient)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(patient.getCasesheet_uid());
                }
            });
            age.setText(patient.getPatient_age());
            RequestGet requestGet=new RequestGet(mContext);
            String url= AppUtils.HOST_ADDRESS+"/api/users/"+patient.getPatient_gid();
            requestGet.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                @Override
                public void onResponse(JSONObject object) {
                    User obj=new User();
                    obj.parseFromJson(object.toString());
                    name.setText("Dr. "+obj.getName());
                    Glide.with(mContext).load(obj.getImageUrl()).into(pic);

                }
            });
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
                                    notifyDataSetChanged();
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
            timestamp.setText("Time : "+ C.getDateAndTime(patient.getTime_slab()));
            if(patient.getStatus().equals("0"))
            {
                changeStatus.setVisibility(View.GONE);
            }
            else if(patient.getStatus().equals("1"))
            {
                changeStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url=AppUtils.HOST_ADDRESS+"/api/appointments/status/doctor/"+patient.getAppointment_id()+"/"+patient.getDoctor_gid();
                        RequestPut put=new RequestPut(mContext);
                        put.putJSONObject(url, null, new RequestPut.JSONObjectResponseListener() {
                            @Override
                            public void onResponse(JSONObject object) {
                                mList.remove(patient);
                                notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
            else
            {
                mList.remove(patient);
                notifyDataSetChanged();
            }

        }
    }
    public interface OnUserClickedListener{
        void onClick(String uid);
    }
}
