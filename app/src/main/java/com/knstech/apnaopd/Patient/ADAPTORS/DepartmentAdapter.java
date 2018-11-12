package com.knstech.apnaopd.Patient.ADAPTORS;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.GenModelClasses.User.Department;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Listeners.DepatmentClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DepartmentAdapter extends RecyclerView.Adapter{

    private List<Department> mList;
    private View view;

    public DepartmentAdapter(List<Department> mList, Context mContext, DepatmentClickListener mListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    private Context mContext;

    private DepatmentClickListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view= LayoutInflater.from(mContext).inflate(R.layout.dept_single_layout,viewGroup,false);
        return new DepartmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((DepartmentHolder)viewHolder).bind(mList.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(mList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class DepartmentHolder extends RecyclerView.ViewHolder{
        TextView name,desc;
        CircleImageView propic;
        public DepartmentHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.desc);
        }
        public void bind(Department department)
        {
            name.setText(department.getName());
            desc.setText(department.getDescription());
            if(department.getImage_link()!=null) {
                Glide.with(mContext).load(department.getImage_link()).into(propic);
            }
        }
    }
}
