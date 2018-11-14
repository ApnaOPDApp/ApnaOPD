package com.knstech.apnaopd.Retailer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.knstech.apnaopd.DrawersUtil.DrawerUtilRetailer;
import com.knstech.apnaopd.Patient.HomeActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.MyConnectionTester;

import butterknife.ButterKnife;

public class RetailerActivity extends AppCompatActivity {

    private RelativeLayout list_of_prescription,confirmed_orders,edit_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);

        overridePendingTransition(0,0);
        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(RetailerActivity.this)){
            connnection.buildDialog(RetailerActivity.this).show();
        }



        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Retailer Home");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        DrawerUtilRetailer.getDrawer(this,toolbar);

        

       list_of_prescription = (RelativeLayout) findViewById(R.id.retailer_cv1);
       confirmed_orders = (RelativeLayout) findViewById(R.id.retailer_cv2);
       edit_profile=(RelativeLayout)findViewById(R.id.retailer_cv3);




       list_of_prescription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Intent intent = new Intent(RetailerActivity.this, ListOfPrescriptionActivity.class);
               startActivity(intent);
           }
       });

       confirmed_orders.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(RetailerActivity.this,ConfirmedOrdersActivity.class));
           }
       });


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RetailerActivity.this, RetailerProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RetailerActivity.this,HomeActivity.class));
        finish();
    }
}
