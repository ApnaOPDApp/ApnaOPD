package com.knstech.apnaopd.Patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.DoctorItemClickedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {

    private RecyclerView doctorsList;
    private DoctorAdapter doctorAdapter;
    private List<Doctor> mList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        initRecyclerView();

    }

    private void initRecyclerView() {
        doctorsList=findViewById(R.id.doctorsList);
        mList=new ArrayList<>();
        doctorAdapter=new DoctorAdapter(this, mList, new DoctorItemClickedListener() {
            @Override
            public void onDoctorItemClick(String uid) {
                Toast.makeText(DoctorListActivity.this, uid+" clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutManager=new LinearLayoutManager(this);
        doctorsList.setAdapter(doctorAdapter);
        doctorsList.setHasFixedSize(true);
        doctorsList.setLayoutManager(linearLayoutManager);
        populateView();
    }
    public void populateView()
    {
        RequestGet requestGet=new RequestGet(this);
        String url="https://jsonplaceholder.typicode.com/posts";
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject object=jsonArray.getJSONObject(i);
                        String id=String.valueOf(object.get("id"));
                        Doctor doctor=new Doctor(id);
                        mList.add(doctor);
                        doctorAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
