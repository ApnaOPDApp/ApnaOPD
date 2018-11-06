package com.knstech.apnaopd.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knstech.apnaopd.GenModalClasses.Doctor.DoctorAuth;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;
import com.knstech.apnaopd.R;

import java.util.List;

public class DoctorSlotViewerActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_slot_viewer);

        linearLayout=findViewById(R.id.slotList);
        List<TimeSlab> list= DoctorAuth.getmDoctor().getTimeSlab();
        for(int i=0;i<24;i++)
        {
            if(list.get(i).getAvailable().equals("true")) {
                View view = LayoutInflater.from(this).inflate(R.layout.time_slab_item_view, null);
                TextView tv = view.findViewById(R.id.slot);
                int time = (i % 12 == 0) ? 12 : i % 12;
                String m = (i / 12 == 0) ? "AM " : "PM ";
                tv.setText(time + ":00 " + m + time + ":55 " + m);
                final int finalI = i;
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent j = new Intent(DoctorSlotViewerActivity.this, DAppointmentViewerActivity.class);
                        j.putExtra("slot", "" + finalI);
                        startActivity(j);
                    }
                });
                linearLayout.addView(view);
            }
        }

    }
}
