package com.knstech.apnaopd.Patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Doctor;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorViewerActivity extends AppCompatActivity {

    private Doctor doctor;
    private TextView name,fees;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_viewer);

        name=findViewById(R.id.doctorName);
        fees=findViewById(R.id.fees);
        imageView=findViewById(R.id.profilePic);

        getDoctor(getIntent().getStringExtra("Doctor"));

    }
    public void getDoctor(String uid)
    {
        RequestGet get=new RequestGet(this);
        String url= AppUtils.HOST_ADDRESS+"/api/doctors/"+uid;
        get.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                try{
                        JSONObject obj=jsonArray.getJSONObject(0);
                        Doctor doctor = new Doctor();
                        doctor.parseFromJson(obj.toString());

                        name.setText(doctor.getName());
                        fees.append(" " + doctor.getFee());
                        Glide.with(DoctorViewerActivity.this).load(UserAuth.getmUser().getImageUrl())
                                .into(imageView);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
}
