package com.knstech.apnaopd.Doctor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
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

                String url=AppUtils.HOST_ADDRESS+"/api/casesheets/"+UserAuth.getmUser().getGid()+"/"+uid;
                requestGet.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                            String key = it.next();
                            View kv=LayoutInflater.from(getApplicationContext()).inflate(R.layout.key_value,null);
                            TextView keyTv,valTv;
                            keyTv=kv.findViewById(R.id.key);
                            valTv=kv.findViewById(R.id.value);
                            keyTv.setText(C.getValue(key));
                            try {
                                valTv.setText(object.getJSONObject(key).getString("title"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                try {
                                    valTv.setText(object.getString(key));
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            linearLayout.addView(kv);

                        }
                    }
                });
                dialog.setView(view);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });
        linearLayoutManager=new LinearLayoutManager(this);
        appList.setLayoutManager(linearLayoutManager);
        appList.setHasFixedSize(true);
        appList.setAdapter(adapter);
        populateView();
    }

    private void populateView() {

        String url= AppUtils.HOST_ADDRESS+"/api/doctors/visiting/"+ UserAuth.getmUser().getGid();
        RequestGet requestGet=new RequestGet(getApplicationContext());
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject obj=jsonArray.getJSONObject(i);
                        String slot=getIntent().getStringExtra("slot");

                        if(slot.equals(obj.getString("sl_no")))
                        {
                            JSONArray array=obj.getJSONArray("patients");
                            for(int j=0;j<array.length();j++)
                            {
                                Patient patient=(new Gson()).fromJson(array.getJSONObject(j).toString(),Patient.class);
                                mList.add(patient);
                                adapter.notifyDataSetChanged();

                            }
                            break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
