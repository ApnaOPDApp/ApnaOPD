package com.knstech.apnaopd.Patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Doctor;
import com.knstech.apnaopd.GenModalClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DoctorViewerActivity extends AppCompatActivity {

    private Doctor doctor;
    private TextView name,fees,available;
    private ImageView imageView;
    private RadioGroup linearLayout;
    private String selectedSlot;
    private Button bookBtn;
    private String uid;
    private String[] ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_viewer);

        name=findViewById(R.id.doctorName);
        fees=findViewById(R.id.fees);
        imageView=findViewById(R.id.profilePic);
        linearLayout=findViewById(R.id.time_slot_list);


        bookBtn=findViewById(R.id.book);
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preBookCheck();
            }
        });

        uid=getIntent().getStringExtra("Doctor");

        getDoctor(uid);

    }

    private void preBookCheck() {
        String cs_uid=getIntent().getStringExtra("cs_uid");
        String url=AppUtils.HOST_ADDRESS+"/api/doctors/visiting/"+uid+"/"+selectedSlot;
        RequestPut requestPut=new RequestPut(this);
        final Patient patient=new Patient();
        patient.setPatient_gid(UserAuth.getmUser().getGid());
        patient.setCasesheet_uid(cs_uid);
        patient.setComment(getIntent().getStringExtra("comment"));
        Map map= new HashMap();
        map.put("patients",patient);
        String jsonStr=(new Gson()).toJson(map);
        try {
            final JSONObject obj=new JSONObject(jsonStr);
            requestPut.putJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                @Override
                public void onResponse(JSONObject object) {
                    Toast.makeText(DoctorViewerActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                }
            }, new RequestPut.JSONObjectErrorListener() {
                @Override
                public void onError(VolleyError error) {
                    Toast.makeText(DoctorViewerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        

    }

    public void getDoctor(String uid) {
        RequestGet get = new RequestGet(this);
        String url = AppUtils.HOST_ADDRESS + "/api/doctors/" + uid;
        get.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject obj) {
                doctor = new Doctor();
                doctor.parseFromJson(obj.toString());

                ar= C.getStringArray(doctor.getTimeSlab());
                

            }
        });
    }
}