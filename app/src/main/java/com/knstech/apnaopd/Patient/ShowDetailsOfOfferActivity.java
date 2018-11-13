package com.knstech.apnaopd.Patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.knstech.apnaopd.Patient.ADAPTORS.OfferDetailAdapter;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.User.Qutotation;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestPut;
import com.knstech.apnaopd.Utils.MyConnectionTester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailsOfOfferActivity extends AppCompatActivity {

    private String str;
    private List<Qutotation> mList;
    private OfferDetailAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Button accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_of_offer);


        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(ShowDetailsOfOfferActivity.this)){
            connnection.buildDialog(ShowDetailsOfOfferActivity.this).show();
        }


        mList=new ArrayList<>();
        adapter=new OfferDetailAdapter(mList,this);
        layoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.rv_show_details);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        str=getIntent().getStringExtra("quotation");
        try
        {

            JSONObject obj=new JSONObject(str);
            JSONArray array=obj.getJSONArray("qutotation");
            long price=0,offeredPrice=0;
            for(int i=0;i<array.length();i++)
            {
                Qutotation qutotation=(new Gson()).fromJson(array.getJSONObject(i).toString(),Qutotation.class);
                mList.add(qutotation);
                price+=Integer.parseInt(qutotation.getPrice());
                offeredPrice+=Integer.parseInt(qutotation.getOffered_price());
                adapter.notifyDataSetChanged();
            }
            TextView priceTv,offeredPriceTv;
            priceTv=findViewById(R.id.price);
            offeredPriceTv=findViewById(R.id.offeredPrice);
            priceTv.setGravity(Gravity.RIGHT);
            offeredPriceTv.setGravity(Gravity.RIGHT);
            offeredPriceTv.setText("Offered Price : "+offeredPrice);
            priceTv.setText("Price : "+price);

            final String order_id=getIntent().getStringExtra("order_id");
            final String offer_id=getIntent().getStringExtra("offer_id");
            accept=findViewById(R.id.accept);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url= AppUtils.HOST_ADDRESS+"/api/orders/"+order_id+"/"+AppUtils.USER_GID+"/"+offer_id;
                    RequestPut put=new RequestPut(ShowDetailsOfOfferActivity.this);
                    put.putJSONObject(url, null, new RequestPut.JSONObjectResponseListener() {
                        @Override
                        public void onResponse(JSONObject object) {
                            try {
                                Toast.makeText(ShowDetailsOfOfferActivity.this, object.getString("message").toString(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }
                    });

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
