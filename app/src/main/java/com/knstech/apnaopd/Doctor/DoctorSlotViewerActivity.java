package com.knstech.apnaopd.Doctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeOfDay;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.OnTimeOfDayClickListener;
import com.knstech.apnaopd.Utils.TimeOfDayAdapter;
import com.knstech.apnaopd.Utils.TimeSlabAdapter;

import java.util.ArrayList;
import java.util.List;

public class DoctorSlotViewerActivity extends AppCompatActivity {
    private RecyclerView slotList;
    private RecyclerView timeList;
    private TimeOfDayAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<TimeOfDay> mList;
    private View clickedView;
    private TimeSlabAdapter timeSlabAdapter;
    private List<TimeSlab> timeSlabList;
    private LinearLayoutManager layoutManager;
    private String[] receivedList;
    private String url= AppUtils.HOST_ADDRESS+"/api/doctors/time_slab/"+ UserAuth.getmUser().getGid();
    private String dayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_slot_viewer);

        slotList=findViewById(R.id.timeSlotList);


        RequestGet requestGet=new RequestGet(getApplicationContext());
        requestGet.getString(url, new RequestGet.StringResponseListener() {
            @Override
            public void onResponse(String response) {
                receivedList=getStringArray(response);
                populateRecyclerView();
            }
        });
        dayOfWeek=getIntent().getStringExtra("day");
    }

    private String[] getStringArray(String str) {
        String ar[];
        if(str.length()!=2)
        {
            String s=str.substring(2,str.length()-2);
            ar=s.split("\",\"");
        }
        else
        {
            ar=null;
        }
        return ar;
    }

    private void populateRecyclerView() {
        mList=new ArrayList<>();
        adapter=new TimeOfDayAdapter(mList, getApplicationContext(),dayOfWeek, receivedList,new OnTimeOfDayClickListener() {
            @Override
            public void OnClick(int i,TimeSlabAdapter adapter,List<TimeSlab> list) {

                for(int j=1;j<=6;j++)
                {
                    TimeSlab slab=new TimeSlab();
                    slab.setSl_no(""+j);
                    list.add(slab);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        linearLayoutManager=new LinearLayoutManager(this);
        slotList.setLayoutManager(linearLayoutManager);
        slotList.setAdapter(adapter);
        slotList.setHasFixedSize(true);
        for(int i=1;i<=4;i++)
        {
            TimeOfDay time=new TimeOfDay();
            time.setId(""+i);
            time.setTitle(C.setName(i));
            mList.add(time);
            adapter.notifyDataSetChanged();

        }

    }




}
