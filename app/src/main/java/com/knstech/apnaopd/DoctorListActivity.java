package com.knstech.apnaopd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class DoctorListActivity extends AppCompatActivity {

    private RecyclerView doctorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        doctorsList=findViewById(R.id.doctorsList);

    }
}
