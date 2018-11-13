package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.knstech.apnaopd.Patient.ADAPTORS.ListOfOrderAdaptor;
import com.knstech.apnaopd.Retailer.RetailerActivity;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Retailer.Order;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.MyConnectionTester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfOrderActivity extends AppCompatActivity {

    private RecyclerView list_rv;
    private LinearLayoutManager layoutManager;
    private List<Order> mList;
    private String URL = AppUtils.HOST_ADDRESS+"/api/orders/patient/"+AppUtils.USER_GID;
    private ListOfOrderAdaptor adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_order);

        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(ListOfOrderActivity.this)){
            connnection.buildDialog(ListOfOrderActivity.this).show();
        }


        toolbar = findViewById(R.id.p_toolbar);
        toolbar.setTitle("Order Status");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        mList = new ArrayList<>();
        list_rv=(RecyclerView)findViewById(R.id.list_of_order_rv);

        /*  Please Check and Verify it */

        layoutManager= new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        /* ------------------------------*/
        list_rv.setLayoutManager(layoutManager);

        list_rv.setHasFixedSize(true);

        adapter = new ListOfOrderAdaptor(ListOfOrderActivity.this, mList);
        list_rv.setAdapter(adapter);


        RequestGet getRequest=new RequestGet(this);
        getRequest.getJSONArray(URL, new RequestGet.JSONArrayResponseListener() {
            @Override

            public void onResponse(JSONArray obj) {

                for(int i=0;i<obj.length();i++){
                    try {

                        JSONObject object=obj.getJSONObject(i);
                        Order order=Order.parseFromJson(object.toString());
                        mList.add(order);
                        adapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ListOfOrderActivity.this, RetailerActivity.class));
        finish();
    }
}
