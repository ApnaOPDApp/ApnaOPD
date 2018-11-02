package com.knstech.apnaopd.Doctor;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.User;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DAppointmentViewerActivity extends AppCompatActivity {

    private RecyclerView appList;
    private AppointmentViewerAdapter adapter;
    private List<User> mList;
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
            public void onClick(User user) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(DAppointmentViewerActivity.this);

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
