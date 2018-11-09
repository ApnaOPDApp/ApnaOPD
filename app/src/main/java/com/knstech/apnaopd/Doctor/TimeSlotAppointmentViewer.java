package com.knstech.apnaopd.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.Doctor.DoctorAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;

public class TimeSlotAppointmentViewer extends AppCompatActivity {
    private LinearLayout linearLayout;
    private String dayOfWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot_appointment_viewer);
        dayOfWeek=getIntent().getStringExtra("day");
        linearLayout=findViewById(R.id.linearLayout);
        final String ar[]= C.getStringArray(DoctorAuth.getmDoctor().getTimeSlab());
        for(int i=0;i<ar.length;i++)
        {
            if(ar[i].substring(0,1).equals(dayOfWeek)) {
                TextView tv = new TextView(this);
                int p=10;
                tv.setPadding(p,p,p,p);
                tv.setTextSize(18);
                tv.setText(C.getTime(ar[i].substring(1, 3)));
                final int finalI = i;
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TimeSlotAppointmentViewer.this, DAppointmentViewerActivity.class);
                        intent.putExtra("day", dayOfWeek);
                        intent.putExtra("time", ar[finalI].substring(1, 3));
                        startActivity(intent);
                    }
                });
                linearLayout.addView(tv);
            }
        }
    }
}
