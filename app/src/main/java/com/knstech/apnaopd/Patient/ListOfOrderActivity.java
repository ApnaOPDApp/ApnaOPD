package com.knstech.apnaopd.Patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Retailer.Order;
import com.knstech.apnaopd.GenModelClasses.User.PatientOrdersList;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfOrderActivity extends AppCompatActivity {

    private RecyclerView list_rv;
    private RecyclerView.LayoutManager layoutManager;
    private List<Order> mList;
    private String URL = AppUtils.HOST_ADDRESS+"/api/orders/patient/"+AppUtils.USER_GID;
    private ListOfOrderAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_order);
        mList = new ArrayList<>();
        list_rv=(RecyclerView)findViewById(R.id.list_of_order_rv);
        layoutManager= new LinearLayoutManager(this);
        list_rv.setLayoutManager(layoutManager);
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
}
