package com.knstech.apnaopd.Retailer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.DrawersUtil.DrawerUtil;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class RetailerActivity extends AppCompatActivity {

    private RelativeLayout list_of_prescription,confirmed_orders;
    String Url = AppUtils.HOST_ADDRESS+"/api/retailers/orders/"+AppUtils.RET_GID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Retailer Home");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        DrawerUtil.getDrawer(this,toolbar);

        

       list_of_prescription = (RelativeLayout) findViewById(R.id.retailer_cv1);
       confirmed_orders = (RelativeLayout) findViewById(R.id.retailer_cv2);




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

    }
}
