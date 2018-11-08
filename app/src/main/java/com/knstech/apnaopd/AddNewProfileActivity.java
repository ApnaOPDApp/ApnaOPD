package com.knstech.apnaopd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddNewProfileActivity extends AppCompatActivity {

    private LinearLayout become_doctor_ll;
    private LinearLayout become_retailer_ll;
    private RelativeLayout become_doctor_form;
    private RelativeLayout become_retailer_form;
    int countd = 1;
    int countr=1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_profile);

        toolbar = findViewById(R.id.p_toolbar);
        toolbar.setTitle("Add new profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

       become_doctor_ll = (LinearLayout)findViewById(R.id.add_new_prof_ll_layout_card1);
       become_retailer_ll= (LinearLayout)findViewById(R.id.add_new_prof_ll_layout_card2);
       become_doctor_form  =(RelativeLayout) findViewById(R.id.become_doctor_form);
       become_retailer_form=(RelativeLayout)findViewById(R.id.become_retailer_form);

       become_doctor_ll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(countd == 1 ){
                   become_doctor_form.setVisibility(View.VISIBLE);
                   countd=0;
               }
               else{
                   become_doctor_form.setVisibility(View.GONE);
                   countd=1;
               }


           }
       });

       become_retailer_ll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(countr == 1 ){
                   become_retailer_form.setVisibility(View.VISIBLE);
                   countr=0;
               }
               else{
                   become_retailer_form.setVisibility(View.GONE);
                   countr=1;
               }
           }
       });

    }
}
