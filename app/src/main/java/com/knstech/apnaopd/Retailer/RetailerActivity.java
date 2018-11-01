package com.knstech.apnaopd.Retailer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.knstech.apnaopd.DrawerUtil;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class RetailerActivity extends AppCompatActivity {

    private CardView list_of_prescription,confirmed_orders;

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

       list_of_prescription = (CardView)findViewById(R.id.retailer_c1);
       confirmed_orders = (CardView)findViewById(R.id.retailer_c2);
       list_of_prescription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(RetailerActivity.this,RetailerOfferSendActivity.class));
           }
       });

    }
}
