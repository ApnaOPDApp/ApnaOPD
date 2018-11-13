package com.knstech.apnaopd.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.knstech.apnaopd.DrawersUtil.DrawerUtil;
import com.knstech.apnaopd.GenModelClasses.Doctor.DoctorAuth;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class DoctorHomeActivity extends AppCompatActivity {

    private RelativeLayout c1,c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        DoctorAuth auth=new DoctorAuth();
        auth.signInDoctor(this, new DoctorAuth.OnSignInListener() {
            @Override
            public void onSignIn() {
                ButterKnife.bind(DoctorHomeActivity.this);

                Toolbar toolbar = (Toolbar)findViewById(R.id.p_toolbar);
                toolbar.setTitle("Doctor Home");
                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowCustomEnabled(true);

                c1=findViewById(R.id.doctor_c1);
                c2=findViewById(R.id.doctor_c2);

                DrawerUtil.getDrawer(DoctorHomeActivity.this,toolbar);
                if(DoctorAuth.getmDoctor(DoctorHomeActivity.this).getTimeSlab()==null)
                {
                    c1.setVisibility(View.VISIBLE);
                }
                else
                {
                    c1.setVisibility(View.GONE);
                }





                c1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DoctorHomeActivity.this,DaySelectActivity.class));
                    }
                });
                c2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(DoctorHomeActivity.this,DaySelectActivity.class);
                        i.putExtra("activity","1");
                        startActivity(i);
                    }
                });
            }
        });


    }
}
