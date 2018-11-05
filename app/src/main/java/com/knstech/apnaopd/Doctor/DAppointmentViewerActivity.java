package com.knstech.apnaopd.Doctor;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Patient;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DAppointmentViewerActivity extends AppCompatActivity {

    private RecyclerView appList;
    private AppointmentViewerAdapter adapter;
    private List<Patient> mList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dappointment_viewer);

        initRecyclerView();
    }

    private void initRecyclerView() {

        appList=findViewById(R.id.d_appointment);
        mList=new ArrayList<>();
        adapter=new AppointmentViewerAdapter(mList, this, new AppointmentViewerAdapter.OnUserClickedListener() {
            @Override
            public void onClick(String uid) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(DAppointmentViewerActivity.this);
                View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.cs_viewer_layout,null);
                final LinearLayout linearLayout=view.findViewById(R.id.list);
                RequestGet requestGet=new RequestGet(getApplicationContext());
                String slot=getIntent().getStringExtra("slot");
                String url=AppUtils.HOST_ADDRESS+"";
                requestGet.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                            String key = it.next();
                            View kv=LayoutInflater.from(getApplicationContext()).inflate(R.layout.key_value,null);
                            TextView keyTv,valueTv;
                            keyTv=kv.findViewById(R.id.key);
                            valueTv=kv.findViewById(R.id.value);
                            keyTv.setText(C.getValue(key));
                            linearLayout.addView(kv);
                            try {
                                valueTv.setText(object.getJSONObject(key).getString("title"));
                            } catch (JSONException e) {
                                try {
                                    valueTv.setText(object.getString(key));
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }

                        }
                    }
                });
            }
        });
        linearLayoutManager=new LinearLayoutManager(this);
        appList.setLayoutManager(linearLayoutManager);
        appList.setHasFixedSize(true);
        appList.setAdapter(adapter);
        populateView();
    }

    private void populateView() {

        String url= AppUtils.HOST_ADDRESS+"/api/";
        RequestGet requestGet=new RequestGet(this);
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {



            }
        });

    }
}
