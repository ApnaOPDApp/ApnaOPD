package com.knstech.apnaopd.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.knstech.apnaopd.GenModelClasses.Doctor.Address;
import com.knstech.apnaopd.GenModelClasses.Doctor.Doctor;
import com.knstech.apnaopd.GenModelClasses.Doctor.DoctorAuth;
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

public class DoctorDetailsActivity extends AppCompatActivity {

    private EditText fee,phone,comment;
    private Spinner dept;
    private RadioGroup addressLL;
    private Button submit;
    private ImageView addBtn;
    private List<Address> mList;
    private int selected;
    private String[] ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        dept=findViewById(R.id.dept);
        fee=findViewById(R.id.fee);
        phone=findViewById(R.id.phone_number);
        comment=findViewById(R.id.comment);

        Doctor doc= DoctorAuth.getmDoctor(DoctorDetailsActivity.this);
        fee.setText(doc.getFee());
        phone.setText(doc.getPhoneNumber());
        comment.setText(doc.getComment());
        
        String url=AppUtils.HOST_ADDRESS+"/api/casesheets/departments/";
        RequestGet get=new RequestGet(DoctorDetailsActivity.this);
        get.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                ar=new String[jsonArray.length()];
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {
                        JSONObject obj=jsonArray.getJSONObject(i);
                        ar[i]=obj.getString("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<>(DoctorDetailsActivity.this,R.layout.spinner_item,ar);
                dept.setAdapter(adapter);
            }
        });
        
        addressLL=findViewById(R.id.addressLL);

        addBtn=findViewById(R.id.addBtn);

        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=AppUtils.HOST_ADDRESS+"/api/doctors/"+ UserAuth.getmUser(DoctorDetailsActivity.this).getGid();
                RequestPut put=new RequestPut(DoctorDetailsActivity.this);

                Map map=new HashMap();
                map.put("fee",fee.getText().toString());
                map.put("department",dept.getSelectedItem());
                map.put("phone_number",phone.getText().toString());
                map.put("comment",comment.getText().toString());
                map.put("address",mList.get(selected));
                JSONObject obj=new JSONObject(map);

                Doctor doc=DoctorAuth.getmDoctor(DoctorDetailsActivity.this);
                doc.setComment(comment.getText().toString());
                doc.setDepartment(dept.getSelectedItem().toString());
                doc.setFee(fee.getText().toString());
                doc.setPhoneNumber(phone.getText().toString());

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
                Intent i=new Intent(DoctorDetailsActivity.this, AddressActivity.class);
                i.putExtra("activity",98);
                startActivity(i);
            }
        });

        loadAddresses();



    }

    private void loadAddresses() {
        String url= AppUtils.HOST_ADDRESS+"/api/users/address/"+UserAuth.getmUser(DoctorDetailsActivity.this).getGid();
        RequestGet get=new RequestGet(DoctorDetailsActivity.this);
        get.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                mList=new ArrayList<>();
                addressLL.removeAllViews();
                mList=Address.parseFromJson(jsonArray.toString());
                for(int i=0;i<mList.size();i++)
                {
                    Address address=mList.get(i);
                    RadioButton rb=new RadioButton(DoctorDetailsActivity.this);
                    final int finalI = i;
                    rb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selected= finalI;
                        }
                    });
                    rb.setText("\n"+address.getFull_name()+","+address.getHouseLane()
                    +"\n"+address.getLandmark()+","+address.getLocality()+
                    "\n"+address.getCity()+", PIN- "+address.getPincode());
                    addressLL.addView(rb);
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
