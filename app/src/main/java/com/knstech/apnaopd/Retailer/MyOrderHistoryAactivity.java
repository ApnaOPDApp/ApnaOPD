package com.knstech.apnaopd.Retailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;

public class MyOrderHistoryAactivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_history_aactivity);
        String URL = AppUtils.HOST_ADDRESS+"/api/retailers/history/"+ UserAuth.getmUser(getApplicationContext()).getGid();
        RequestGet request = new RequestGet(getApplicationContext());

        request.getJSONArray(URL, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {

            }
        });



    }
}
