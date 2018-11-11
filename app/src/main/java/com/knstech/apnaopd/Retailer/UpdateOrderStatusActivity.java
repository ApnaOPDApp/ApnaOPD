package com.knstech.apnaopd.Retailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModelClasses.User.Qutotation;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateOrderStatusActivity extends AppCompatActivity {


    private List<Qutotation> mList;
    private ConfOrderAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private String[] quotaion;
    private TextView upload_total_price,upload_delivery_time,upload_address_show;
    private String order_id;
    private String status;
    private String URL  = AppUtils.HOST_ADDRESS+"/api/orders/update/";

    private Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_offers_layout);


        upload_total_price = findViewById(R.id.upload_total_price);
        upload_delivery_time=findViewById(R.id.upload_delivery_time);
        upload_address_show=findViewById(R.id.upload_address_show);

        btn1 = findViewById(R.id.status1);
        btn2 = findViewById(R.id.status2);
        btn3 = findViewById(R.id.status3);
        btn4 = findViewById(R.id.status4);



        quotaion = getIntent().getStringArrayExtra("quot");
        String total_price = getIntent().getStringExtra("total_price");
        if(total_price == null) total_price="NOT Provided";
        String delivery_time = getIntent().getStringExtra("delivery_time");
        if(delivery_time == null) delivery_time="NOT Provided";
        String delivery_charge = getIntent().getStringExtra("delivery_charge");
        if(delivery_charge == null) delivery_charge="NOT Provided";
        String name = getIntent().getStringExtra("name");
        if(name == null) name="NOT Provided";
        String pincode = getIntent().getStringExtra("zip");
        if(pincode == null) pincode="NOT Provided";
        String locality = getIntent().getStringExtra("'locality");
        if(locality == null) locality="NOT Provided";
        String house_no = getIntent().getStringExtra("house_no");
        if(house_no == null) house_no ="NOT Provided";
        String status = getIntent().getStringExtra("status");
        order_id = getIntent().getStringExtra("_id");
        URL = URL+order_id+"/"+AppUtils.RET_GID;

        int stat = Integer.parseInt(status);
        switch(stat){
            case 1:
                    btn1.setEnabled(false);
                break;
            case 2:
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                break;
            case 3:
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                break;
            case 4:
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                break;

        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = URL+"/1";
                RequestPut request = new RequestPut(UpdateOrderStatusActivity.this);
                request.putJSONObject(URL, null, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Toast.makeText(UpdateOrderStatusActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                        btn1.setEnabled(false);

                    }
                });

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = URL+"/2";
                RequestPut request = new RequestPut(UpdateOrderStatusActivity.this);
                request.putJSONObject(URL, null, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Toast.makeText(UpdateOrderStatusActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                        btn2.setEnabled(false);

                    }
                });
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = URL+"/3";
                RequestPut request = new RequestPut(UpdateOrderStatusActivity.this);
                request.putJSONObject(URL, null, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Toast.makeText(UpdateOrderStatusActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                        btn3.setEnabled(false);

                    }
                });
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = URL+"/4";
                RequestPut request = new RequestPut(UpdateOrderStatusActivity.this);
                request.putJSONObject(URL, null, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Toast.makeText(UpdateOrderStatusActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                        btn4.setEnabled(false);

                    }
                });
            }
        });




        upload_total_price.setText("Total Price : "+total_price);
        upload_delivery_time.setText("Delivery time : "+delivery_time+"\nDelivery Charge : "+delivery_charge);
        upload_address_show.setText(name+"\n"+pincode+"\n"+house_no);




        recyclerView=findViewById(R.id.update_order_rv);
        mList=new ArrayList<>();
        adapter=new ConfOrderAdapter(this,mList);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        populateRecycler();

        }

    private void populateRecycler() {

        for(int i=0;i<quotaion.length;i++)
        {
            Qutotation qutotation=(new Gson()).fromJson(quotaion[i],Qutotation.class);
            mList.add(qutotation);
            adapter.notifyDataSetChanged();
        }

    }
}
