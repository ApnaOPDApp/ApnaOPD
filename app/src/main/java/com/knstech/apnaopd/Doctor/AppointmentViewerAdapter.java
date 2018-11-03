package com.knstech.apnaopd.Doctor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.GenModalClasses.User.User;
import com.knstech.apnaopd.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentViewerAdapter extends RecyclerView.Adapter {
    private List<User> mList;
    private Context mContext;
    private OnUserClickedListener mListener;

    public AppointmentViewerAdapter(List<User> mList, Context mContext,OnUserClickedListener mListener)
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
        User user=mList.get(i);
        ((PatientHolder)viewHolder).bind(user);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class PatientHolder extends RecyclerView.ViewHolder{
        private final View view;
        CircleImageView pic;
        TextView name,time;
        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            pic=itemView.findViewById(R.id.patientPic);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
        }
        public void bind(final User user)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(user);
                }
            });

            Glide.with(mContext).load(user.getImageUrl()).into(pic);
            name.setText(user.getName());

        }
    }
    public interface OnUserClickedListener{
        void onClick(User user);
    }
}
