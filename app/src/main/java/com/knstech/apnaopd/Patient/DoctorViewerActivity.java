package com.knstech.apnaopd.Patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Doctor;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DoctorViewerActivity extends AppCompatActivity {

    private Doctor doctor;
    private TextView name,fees;
    private ImageView imageView;
    private RadioGroup linearLayout;
    private String selectedSlot;
    private Button bookBtn;
    private String uid;

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
        if(doctor!=null) {
            int total = Integer.parseInt(doctor.getTimeSlab().get(Integer.parseInt(selectedSlot)).getPatients_per());
            int appointed=doctor.getVisiting().size();
            if(total-appointed>0)
            {

            }
            else
            {
                Toast.makeText(this, "Booking is full for this doctor!", Toast.LENGTH_SHORT).show();
            }

        }




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
                        doctor = new Doctor();
                        doctor.parseFromJson(obj.toString());
                        List<TimeSlab> list= doctor.getTimeSlab();

                        name.setText(doctor.getName());
                        fees.append(" " + doctor.getFee());
                        Glide.with(DoctorViewerActivity.this).load(UserAuth.getmUser().getImageUrl())
                                .into(imageView);
                        linearLayout.removeAllViews();
                        for(int i=0;i<list.size();i++)
                        {
                            RadioButton btn=new RadioButton(DoctorViewerActivity.this);
                            btn.setChecked(true);
                            final TimeSlab slab=list.get(i);
                            String m;
                            final int slot_no=(Integer.parseInt(slab.getSl_no())%12==0)?12:Integer.parseInt(slab.getSl_no())%12;
                            m=(Integer.parseInt(slab.getSl_no())/12==0)?"AM ":"PM ";
                            btn.setText(""+slot_no+":00 "+m+" - "+slot_no+":55 "+m);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    selectedSlot=slab.getSl_no();
                                }
                            });
                            if(slab.getAvailable().equals("true"))
                                linearLayout.addView(btn);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
}
