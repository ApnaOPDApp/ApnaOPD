package com.knstech.apnaopd.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Patient.HomeActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestSingleton;
import com.knstech.apnaopd.Utils.MyConnectionTester;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddressActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText fullName,contactNumber,zipCode,flatNo,colony,landmark,city;
    private AppCompatSpinner state;
    int lock=0;
    private String full_name1,contactNumber1,zipCode1,flatNo1,colony1,landmark1,city1,state1;


    private Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        final String url = AppUtils.HOST_ADDRESS+"/api/users/address/new/"+ UserAuth.getmUser(AddressActivity.this).getGid();

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Address");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        fullName = (EditText)findViewById(R.id.full_name);
        contactNumber = (EditText)findViewById(R.id.phone_number);
        zipCode = (EditText)findViewById(R.id.zip);
        flatNo = (EditText)findViewById(R.id.flat_no);
        colony = (EditText)findViewById(R.id.street);
        landmark = (EditText)findViewById(R.id.landmark);
        city = (EditText)findViewById(R.id.city);
        state = (AppCompatSpinner)findViewById(R.id.state);
        addbtn = (Button)findViewById(R.id.add);



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeString();

                Map<String,String> params = new HashMap<>();
                params.put("full_name",full_name1);
                params.put("phone_number",contactNumber1);
                params.put("pincode",zipCode1);
                params.put("house_no",flatNo1);
                params.put("landmark",landmark1);
                params.put("city",city1);
                params.put("state",state1);
                params.put("colony",colony1);


                Map val = new HashMap();
                val.put("address",params);

                JSONObject obj = new JSONObject(val);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,url,
                        obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if(getIntent().getIntExtra("activity",0)!=0)
                                {
                                    finish();
                                }
                                Toast.makeText(AddressActivity.this,"Successfully Added", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                new MyConnectionTester().buildDialog2(AddressActivity.this);
                            }
                        }
                );

                RequestSingleton.getInstance(AddressActivity.this).addToQueue(request);


            }
        });


    }

    private void initializeString(){
        full_name1 = fullName.getText().toString();
        contactNumber1 = contactNumber.getText().toString();
        zipCode1 = zipCode.getText().toString();
        flatNo1 = flatNo.getText().toString();
        colony1 = colony.getText().toString();
        landmark1 = landmark.getText().toString();
        city1 = city.getText().toString();

        state1 = state.getSelectedItem().toString();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddressActivity.this, HomeActivity.class));
        finish();
    }
}
