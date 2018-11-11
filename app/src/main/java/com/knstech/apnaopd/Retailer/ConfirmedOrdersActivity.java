package com.knstech.apnaopd.Retailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Retailer.PojoConfirmedOrder;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedOrdersActivity extends AppCompatActivity {

    private String URL = AppUtils.HOST_ADDRESS+"/api/orders/retailer/"+AppUtils.RET_GID;
    private List<PojoConfirmedOrder> data;
    private RecyclerView  confirmed_list_rv;
    private ConfirmedOrderAdaptor mAdaptor;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_orders);



        confirmed_list_rv = (RecyclerView)findViewById(R.id.confirm_order_rv);
        layoutManager = new LinearLayoutManager(this);
        confirmed_list_rv.setLayoutManager(layoutManager);
        data = new ArrayList<>();

        mAdaptor = new ConfirmedOrderAdaptor(getApplicationContext(),data);
        confirmed_list_rv.setAdapter(mAdaptor);
        confirmed_list_rv.setHasFixedSize(true);

        RequestGet request = new RequestGet(this);
        request.getJSONArray(URL, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++){

                    try {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        PojoConfirmedOrder confirmedOrder = PojoConfirmedOrder.parseFromJson(jsonObject);
                        data.add(confirmedOrder);
                        mAdaptor.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
