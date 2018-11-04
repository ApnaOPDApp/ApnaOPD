package com.knstech.apnaopd.Doctor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeSlotActivity extends AppCompatActivity {


    private LinearLayout linearLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_slab_manager_layout);

        populateLinearLayout();
        final String url=AppUtils.HOST_ADDRESS+"/api/doctors/time_slab/"+ UserAuth.getmUser().getGid();

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map> list=new ArrayList<>();

                for(int i=0;i<linearLayout.getChildCount();i++)
                {
                    Map<String,String> map=new HashMap<>();
                    View view=linearLayout.getChildAt(i);
                    EditText edt=view.findViewById(R.id.persons);
                    Switch sw=view.findViewById(R.id.switchBtn);
                    TimeSlab slab=new TimeSlab();
                    map.put("available",(sw.isChecked())?"1":"0");
                    map.put("patients_per",(!TextUtils.isEmpty(edt.getText().toString()))?edt.getText().toString():"0");
                    map.put("sl_no",""+i);
                    list.add(map);
                }
                Map map1=new HashMap();
                map1.put("time_slab",list);
                JSONObject obj=new JSONObject(map1);
                RequestPut put=new RequestPut(TimeSlotActivity.this);
                put.putJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        finish();
                    }
                });

            }
        });


    }

    private void populateLinearLayout() {

        linearLayout=findViewById(R.id.slabs);
        linearLayout.removeAllViews();

        for(int i=0;i<24;i++)
        {
            View view= LayoutInflater.from(this).inflate(R.layout.time_slab_item_layout,null);
            TextView textView=view.findViewById(R.id.time);
            int st;
            String m;
            m=(i/12==0)?"AM ":"PM ";
            st=(i%12!=0)?(i)%12:12;
            textView.setText(st+":00 "+m+" - "+st+":55 "+m);
            linearLayout.addView(view);
        }


        RequestGet requestGet=new RequestGet(this);
        String url= AppUtils.HOST_ADDRESS+"/api/doctors/time_slab/"+UserAuth.getmUser().getGid();
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                for(int i=0;i<jsonArray.length();i++)
                {

                    try {
                        JSONObject object=jsonArray.getJSONObject(i);
                        View view=linearLayout.getChildAt(i);
                        EditText edt=view.findViewById(R.id.persons);
                        edt.setText(object.getString("patients_per"));
                        Switch sw=view.findViewById(R.id.switchBtn);
                        if(object.getString("available").equals("true"))
                            sw.setChecked(true);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }
}
