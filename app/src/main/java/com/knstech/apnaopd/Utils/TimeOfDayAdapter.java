package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.knstech.apnaopd.GenModalClasses.Doctor.TimeOfDay;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;
import com.knstech.apnaopd.R;

import java.util.ArrayList;
import java.util.List;

public class TimeOfDayAdapter extends RecyclerView.Adapter {
    private View view;
    private List<TimeOfDay> mList;
    private Context mContext;
    private String dayOfWeek;
    private String[] receivedList;
    private OnTimeOfDayClickListener mListener;

    public TimeOfDayAdapter(List<TimeOfDay> mList, Context mContext, String dayOfWeek, String[] receivedList, OnTimeOfDayClickListener mListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.dayOfWeek = dayOfWeek;
        this.receivedList = receivedList;
        this.mListener = mListener;
    }

    @NonNull
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view= LayoutInflater.from(mContext).inflate(R.layout.time_slab_item_view,null);
        return  new TimeOfDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ((TimeOfDayViewHolder)viewHolder).bind(mList.get(i));


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    private class TimeOfDayViewHolder extends RecyclerView.ViewHolder  {
        TimeOfDay tod;
        TextView todView;
        LinearLayoutManager layoutManager;
        private TimeSlabAdapter timeSlabAdapter;
        private List<TimeSlab> timeSlabList;
        private boolean isExpanded =false;
        private RecyclerView timeList;


        public TimeOfDayViewHolder(View view) {
            super(view);
            todView=view.findViewById(R.id.slot);

        }
        public void bind(final TimeOfDay timeOfDay)
        {
            tod=timeOfDay;
            todView.setText(timeOfDay.getTitle());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timeList=v.findViewById(R.id.recView);
                    layoutManager=new LinearLayoutManager(mContext);
                    timeSlabList=new ArrayList<>();
                    timeSlabAdapter=new TimeSlabAdapter(mContext,timeSlabList);
                    timeList.setAdapter(timeSlabAdapter);
                    timeList.setLayoutManager(layoutManager);
                    timeList.setHasFixedSize(true);

                    if(!isExpanded) {
                        mListener.OnClick(Integer.parseInt(tod.getId()), timeSlabAdapter, timeSlabList);
                        Toast.makeText(mContext, tod.getId()+" Inflated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(timeSlabList!=null) {
                            timeSlabList.clear();
                        }
                        Toast.makeText(mContext, tod.getId()+" Deflated", Toast.LENGTH_SHORT).show();
                    }
                    isExpanded=(isExpanded)?false:true;
                }
            });
        }
    }
}
