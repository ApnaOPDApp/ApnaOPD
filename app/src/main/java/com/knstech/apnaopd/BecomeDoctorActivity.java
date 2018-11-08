package com.knstech.apnaopd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.knstech.apnaopd.GenModalClasses.User.Address;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.Profile.AddressActivity;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;

import java.util.List;

public class BecomeDoctorActivity extends AppCompatActivity {
    public static int ADDRESS=1;
    EditText fee,degree,regNo;
    AutoCompleteTextView department;
    ImageView addBtn;
    RadioGroup addressGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_doctor);

        fee=findViewById(R.id.fee);
        department=findViewById(R.id.dept);
        degree=findViewById(R.id.degree);
        regNo=findViewById(R.id.regNo);

        addBtn=findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BecomeDoctorActivity.this, AddressActivity.class);
                i.putExtra("activity",1);
                startActivityForResult(i,ADDRESS);
            }
        });

        addressGroup=findViewById(R.id.radioGroup);
        initRadio();
    }

    private void initRadio() {
        RequestGet get=new RequestGet(getApplicationContext());
        String url=AppUtils.HOST_ADDRESS+"/api/users/address/"+ UserAuth.getmUser().getGid();
        get.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                List<Address> addressList=Address.parseFromJson(jsonArray.toString());
                for(int i=0;i<addressList.size();i++)
                {
                    Address address=addressList.get(i);
                    RadioButton rb=new RadioButton(BecomeDoctorActivity.this);
                    rb.setTextSize(20);
                    int p=10;
                    rb.setPadding(p,p,p,p);
                    rb.setText(address.getFull_name()+" , "+address.getHouse_no()+"\n"+address.getLandmark()+" , "+address.getLocality()+"\n"+address.getCity()+" ,PIN- "+address.getPincode());
                    addressGroup.addView(rb);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==ADDRESS)
        {
            if(resultCode==RESULT_OK)
            {
                initRadio();

            }
        }
    }
}
