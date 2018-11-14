package com.knstech.apnaopd.Retailer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Profile.AddressActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditRetailerProfileActivity extends AppCompatActivity {


    private EditText phone;
    private RadioGroup addressLL;
    private Button submit;
    private ImageView addBtn;
    private List<Map> mList;
    private List<com.knstech.apnaopd.GenModelClasses.Doctor.Address> addresses;
    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_retailer_profile);
        phone = findViewById(R.id.phone_number);

        addressLL = findViewById(R.id.addressLL);

        addBtn = findViewById(R.id.addBtn);

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = AppUtils.HOST_ADDRESS + "/api/retailers/profile/"+UserAuth.getmUser(EditRetailerProfileActivity.this).getGid();
                RequestPut put = new RequestPut(EditRetailerProfileActivity.this);

                Map map = new HashMap();
                map.put("phone_number", phone.getText().toString());
                map.put("address", mList.get(selected));
                JSONObject obj = new JSONObject(map);
                put.putJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        finish();
                    }
                });
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditRetailerProfileActivity.this, AddressActivity.class);
                i.putExtra("activity", 98);
                startActivity(i);
            }
        });

        loadAddresses();


    }

    private void loadAddresses() {

        String url = AppUtils.HOST_ADDRESS + "/api/users/address/" + UserAuth.getmUser(EditRetailerProfileActivity.this).getGid();
        RequestGet get = new RequestGet(EditRetailerProfileActivity.this);
        get.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                mList = new ArrayList<>();
                addressLL.removeAllViews();
                addresses= com.knstech.apnaopd.GenModelClasses.Doctor.Address.parseFromJson(jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Map map=(new Gson()).fromJson(jsonArray.getJSONObject(i).toString(),Map.class);
                        RadioButton rb = new RadioButton(EditRetailerProfileActivity.this);
                        com.knstech.apnaopd.GenModelClasses.Doctor.Address address=addresses.get(i);
                        final int finalI = i;
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selected = finalI;
                            }
                        });
                        rb.setText("\n" + address.getFull_name() + "," + address.getHouseLane()
                                + "\n" + address.getLandmark() + "," + address.getLocality() +
                                "\n" + address.getCity() + ", PIN- " + address.getPincode());
                        addressLL.addView(rb);
                        mList.add(map);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAddresses();
    }

}