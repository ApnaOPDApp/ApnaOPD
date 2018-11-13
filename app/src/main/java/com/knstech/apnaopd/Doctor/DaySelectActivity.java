package com.knstech.apnaopd.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.knstech.apnaopd.GenModelClasses.Doctor.DayOfWeek;
import com.knstech.apnaopd.Patient.ShowDetailsOfOfferActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.DaySelectAdapter;
import com.knstech.apnaopd.Utils.Listeners.OnDayClickListener;
import com.knstech.apnaopd.Utils.MyConnectionTester;

import java.util.ArrayList;
import java.util.List;

public class DaySelectActivity extends AppCompatActivity {

    private RecyclerView dayList;
    private DaySelectAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<DayOfWeek> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_select);

        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(DaySelectActivity.this)){
            connnection.buildDialog(DaySelectActivity.this).show();
        }



        dayList=findViewById(R.id.dayList);
        initRecView();

        Toolbar toolbar=findViewById(R.id.p_toolbar);
        toolbar.setTitle("Select Day");


    }

    private void initRecView() {

        mList=new ArrayList<>();
        adapter=new DaySelectAdapter(this, mList, new OnDayClickListener() {
            @Override
            public void onClick(DayOfWeek timeOfDay) {
                String calledActivity="";
                calledActivity=getIntent().getStringExtra("activity");
                Intent i;
                if(calledActivity==null||calledActivity.equals("")) {
                    i = new Intent(DaySelectActivity.this, DoctorSlotViewerActivity.class);
                }
                else
                {
                    i=new Intent(DaySelectActivity.this,TimeSlotAppointmentViewer.class);
                }
                i.putExtra("day",timeOfDay.getId());
                startActivity(i);
            }
        });
        linearLayoutManager=new LinearLayoutManager(this);
        dayList.setHasFixedSize(true);
        dayList.setAdapter(adapter);
        dayList.setLayoutManager(linearLayoutManager);
        populateRecView();

    }

    private void populateRecView() {

        for(int i=1;i<=7;i++)
        {
            DayOfWeek dayOfWeek=new DayOfWeek();
            dayOfWeek.setId(""+i);
            dayOfWeek.setTitle(C.setDayOfWeek(i));
            mList.add(dayOfWeek);
            adapter.notifyDataSetChanged();
        }

    }
}
