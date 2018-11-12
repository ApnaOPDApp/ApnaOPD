package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.knstech.apnaopd.Patient.ADAPTORS.OrderAdaptor;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.User.PatientOrdersList;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.OderClickedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {


    private String URL = AppUtils.HOST_ADDRESS+"/api/orders/offer/";
    private RecyclerView quot_list;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private OrderAdaptor mAdaptor;
    String order_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
/*
        toolbar=(Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Orders ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


*/
        order_id= getIntent().getStringExtra("_id");
        URL= URL+order_id;
        populateView();

    }

    private List<PatientOrdersList> patientOrdersList;

    private void populateView() {

        quot_list = (RecyclerView)findViewById(R.id.quot_recycler_view);
        layoutManager= new LinearLayoutManager(this);
        quot_list.setLayoutManager(layoutManager);
        patientOrdersList = new ArrayList<>();

        mAdaptor = new OrderAdaptor(patientOrdersList, getApplicationContext(), new OderClickedListener() {

            @Override
            public void onOrderClicked(PatientOrdersList quotation,String _id) {
                Intent i=new Intent(OrderActivity.this,ShowDetailsOfOfferActivity.class);
                String obj=(new Gson()).toJson(quotation,PatientOrdersList.class);
                i.putExtra("quotation",obj);
                i.putExtra("order_id",order_id);
                i.putExtra("offer_id",_id);
                startActivity(i);
            }
        });

        quot_list.setAdapter(mAdaptor);


        RequestGet getRequest=new RequestGet(this);
        getRequest.getJSONArray(URL, new RequestGet.JSONArrayResponseListener() {
            @Override

            public void onResponse(JSONArray obj) {

               for(int i=0;i<obj.length();i++){
                   try {

                       JSONObject jobj = obj.getJSONObject(i);
                       PatientOrdersList p = PatientOrdersList.parseFromJson(jobj);
                       patientOrdersList.add(p);
                       mAdaptor.notifyDataSetChanged();

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }


               }

            }
        });


    }


}
