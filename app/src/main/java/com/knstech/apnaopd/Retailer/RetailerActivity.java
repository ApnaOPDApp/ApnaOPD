package com.knstech.apnaopd.Retailer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.DrawerUtil;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Volley.VolleySingleton;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;

public class RetailerActivity extends AppCompatActivity {

    private CardView list_of_prescription,confirmed_orders;
    String Url = AppUtils.HOST_ADDRESS+"/api/retailers/presc_list/ret2";


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

               StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {


                               Intent intent =   new Intent(RetailerActivity.this,ListOfPrescriptionActivity.class);
                               intent.putExtra("id array",response);
                               startActivity(intent);


                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {

                           }
                       }
               );

               VolleySingleton.getmInstance().addToRequestQueue(stringRequest);

           }
       });

    }
}
