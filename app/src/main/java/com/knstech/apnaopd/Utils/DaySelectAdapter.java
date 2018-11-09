package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.Doctor.DayOfWeek;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Listeners.OnDayClickListener;

import java.util.List;

public class DaySelectAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private View view;

    public DaySelectAdapter(Context mContext, List<DayOfWeek> mList,OnDayClickListener mListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListener = mListener;
    }

    private List<DayOfWeek> mList;
    private OnDayClickListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view= LayoutInflater.from(mContext).inflate(R.layout.day_item_layout,viewGroup,false);
        return new DayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((DayHolder)viewHolder).bind(mList.get(i));
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

    private class DayHolder extends RecyclerView.ViewHolder {

        TextView day;


        public DayHolder(View view) {
            super(view);
            day=view.findViewById(R.id.day);
        }
        public void bind(DayOfWeek dayOfWeek)
        {
            day.setText(dayOfWeek.getTitle());
        }
    }
}
