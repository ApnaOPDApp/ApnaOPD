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

import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
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

    public AppointmentViewerAdapter getAdapter() {
        return adapter;
    }

    private AppointmentViewerAdapter adapter;

    public List<Patient> getmList() {
        return mList;
    }

    private List<Patient> mList;
    private String dayOfWeek;
    private LinearLayoutManager linearLayoutManager;
    private String timeOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dappointment_viewer);
        dayOfWeek=getIntent().getStringExtra("day");
        timeOfDay=getIntent().getStringExtra("time");
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
                String url=AppUtils.HOST_ADDRESS+"/api/casesheets/"+uid;
                requestGet.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                            String key = it.next();
                            if(!key.equals("_id")) {
                                View kv = LayoutInflater.from(getApplicationContext()).inflate(R.layout.key_value, null);
                                TextView keyTv, valTv;
                                int p = 20;
                                keyTv = kv.findViewById(R.id.key);
                                valTv = kv.findViewById(R.id.value);
                                keyTv.setPadding(p, p, p, p);
                                valTv.setPadding(p, p, p, p);
                                keyTv.setTextSize(18);
                                valTv.setTextSize(18);


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

        String url= AppUtils.HOST_ADDRESS+"/api/appointments/doctor/visiting/"+ UserAuth.getmUser(DAppointmentViewerActivity.this).getGid();
        RequestGet requestGet=new RequestGet(getApplicationContext());
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject obj=new JSONObject(jsonArray.getJSONObject(i).toString());
                        Patient patient=Patient.parseFromJson(obj.toString());
                        if(patient.getTime_slab().substring(0,3).equals(dayOfWeek+timeOfDay)) {
                            mList.add(0,patient);
                            adapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
