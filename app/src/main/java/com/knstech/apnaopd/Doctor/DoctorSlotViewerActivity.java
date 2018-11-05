package com.knstech.apnaopd.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knstech.apnaopd.R;

public class DoctorSlotViewerActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_slot_viewer);

        linearLayout=findViewById(R.id.time_slot_list);
        for(int i=0;i<24;i++)
        {
            View view= LayoutInflater.from(this).inflate(R.layout.time_slab_item_view,null);
            TextView tv=view.findViewById(R.id.slot);
            int time=(i%12==0)?12:i%12;
            String m=(i/12==0)?"AM ":"PM ";
            tv.setText(time+":00 "+m+time+":55 "+m);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(DoctorSlotViewerActivity.this,DAppointmentViewerActivity.class);
                    i.putExtra("slot",""+i);
                    startActivity(i);
                }
            });
            linearLayout.addView(view);
        }

    }
}
