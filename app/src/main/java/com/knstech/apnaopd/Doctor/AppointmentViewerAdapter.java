package com.knstech.apnaopd.Doctor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModalClasses.User.User;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

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
        TextView name,comment;
        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            pic=itemView.findViewById(R.id.patientPic);
            name=itemView.findViewById(R.id.name);
            comment=itemView.findViewById(R.id.comment);

        }
        public void bind(final Patient patient)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(patient.getCasesheet_uid());
                }
            });
            comment.setText(patient.getComment());
            RequestGet requestGet=new RequestGet(mContext);
            String url= AppUtils.HOST_ADDRESS+"";
            requestGet.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                @Override
                public void onResponse(JSONObject object) {
                    User obj=(new Gson()).fromJson(object.toString(),User.class);
                    name.setText(obj.getName());
                    Glide.with(mContext).load(obj.getImageUrl()).into(pic);

                }
            });

        }
    }
    public interface OnUserClickedListener{
        void onClick(String uid);
    }
}
