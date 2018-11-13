package com.knstech.apnaopd.Patient;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Doctor;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPost;
import com.knstech.apnaopd.Utils.Connections.RequestPut;
import com.knstech.apnaopd.Utils.MyConnectionTester;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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
    private String patient_age,patient_name,dayOfWeek;
    private String department,doctor_name,doctor_image,fee;
    private String cs_uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_viewer);

        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(DoctorViewerActivity.this)){
            connnection.buildDialog(DoctorViewerActivity.this).show();
        }



        name=findViewById(R.id.doctorName);
        fees=findViewById(R.id.fees);
        imageView=findViewById(R.id.profilePic);
        linearLayout=findViewById(R.id.time_slot_list);
        dayOfWeek=""+getIntent().getIntExtra("day",0);

        bookBtn=findViewById(R.id.book);
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookApp();
            }
        });
        bookBtn.setEnabled(false);
        cs_uid=getIntent().getStringExtra("cs_uid");

        uid=getIntent().getStringExtra("Doctor");

        getDoctor(uid);

    }

    private void preBookCheck() {
        String url=AppUtils.HOST_ADDRESS+"/api/appointments/";
        RequestPost requestPost=new RequestPost(this);
        patient_age=getIntent().getStringExtra("patient_age");
        patient_name=getIntent().getStringExtra("patient_name");
        Map map= new HashMap();
        map.put("patient_gid",UserAuth.getmUser().getGid());
        map.put("patient_age",patient_age);
        map.put("doctor_gid",uid);
        map.put("casesheet_uid",cs_uid);
        map.put("patient_name",patient_name);
        map.put("doctor_name",doctor_name);
        map.put("fee",fee);
        map.put("doctor_image",doctor_image);
        map.put("department",department);


        String timeCode="";
        for(int i=0;i<linearLayout.getChildCount();i++)
        {
            RadioButton rb= (RadioButton) linearLayout.getChildAt(i);
            if(rb.isChecked())
            {
                timeCode=C.getTimeCode(rb.getText().toString());
                break;
            }
        }
        if(timeCode!="") {
            for(int i=0;i<ar.length;i++)
            {
                if(ar[i].substring(0,3).equals(dayOfWeek+timeCode))
                {
                    map.put("time_slab", ar[i]);
                    String jsonStr=(new Gson()).toJson(map);
                    try {
                        final JSONObject obj = new JSONObject(jsonStr);
                        requestPost.postJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                            @Override
                            public void onResponse(JSONObject object) {
                                finish();
                                Toast.makeText(DoctorViewerActivity.this, "Booked Appointment!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

        }
        

    }
    void bookApp()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(DoctorViewerActivity.this);
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.cs_viewer_layout,null);
        final LinearLayout linearLayout=view.findViewById(R.id.list);
        RequestGet requestGet=new RequestGet(getApplicationContext());
        String url=AppUtils.HOST_ADDRESS+"/api/casesheets/"+cs_uid;
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
        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preBookCheck();
            }
        });
        dialog.show();


    }

    public void getDoctor(String uid) {
        RequestGet get = new RequestGet(this);
        String url = AppUtils.HOST_ADDRESS + "/api/doctors/" + uid;
        get.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject obj) {

                bookBtn.setEnabled(true);

                doctor = new Doctor();
                doctor.parseFromJson(obj.toString());
                name.setText("Dr. "+doctor.getName());
                fees.setText("Rs. "+doctor.getFee());

                department=doctor.getDepartment();
                fee=doctor.getFee();
                doctor_name=doctor.getName();
                doctor_image=doctor.getDoctor_image();
                if(doctor.getDoctor_image()!=null&&!doctor.getDoctor_image().equals(""))
                    Glide.with(getApplicationContext()).load(doctor.getDoctor_image()).into(imageView);

                ar= C.getStringArray(doctor.getTimeSlab());
                initRadio(ar);
                

            }
        });
    }
    void initRadio(String ar[])
    {
        for(int i=0;i<ar.length;i++)
        {
            if(ar[i].charAt(0)-'0'==Integer.parseInt(dayOfWeek)) {
                RadioButton rb = new RadioButton(this);
                rb.setText(C.getTime(ar[i].substring(1, 3)));
                linearLayout.addView(rb);
            }
        }
    }
}