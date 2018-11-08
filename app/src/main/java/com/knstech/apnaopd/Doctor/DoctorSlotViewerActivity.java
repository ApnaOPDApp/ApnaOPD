package com.knstech.apnaopd.Doctor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DoctorSlotViewerActivity extends AppCompatActivity {
    private LinearLayout r1,r2,r3,r4;
    private String url= AppUtils.HOST_ADDRESS+"/api/doctors/time_slab/"+ UserAuth.getmUser().getGid();
    private String dayOfWeek;
    private String[] receivedList;
    private List<String> selectedList;
    private CardView mornCv,afterCv,eveCv,nightCv;
    private String selectedTime;
    private boolean isExpanded1,isExpanded2,isExpanded3,isExpanded4;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_slot_viewer);

        selectedList=new ArrayList<>();

        r1=findViewById(R.id.recView1);
        r2=findViewById(R.id.recView2);
        r3=findViewById(R.id.recView3);
        r4=findViewById(R.id.recView4);

        mornCv=findViewById(R.id.mornCv);
        afterCv=findViewById(R.id.afterCv);
        eveCv=findViewById(R.id.eveCv);
        nightCv=findViewById(R.id.nightCv);




        RequestGet requestGet=new RequestGet(getApplicationContext());
        requestGet.getString(url, new RequestGet.StringResponseListener() {
            @Override
            public void onResponse(String response) {
                receivedList=C.getStringArray(response);
                selectedTime="1";
                initRecView(r1);
                r1.setVisibility(View.GONE);
                selectedTime="2";
                initRecView(r2);
                r2.setVisibility(View.GONE);
                selectedTime="3";
                initRecView(r3);
                r3.setVisibility(View.GONE);
                selectedTime="4";
                initRecView(r4);
                r4.setVisibility(View.GONE);
                mornCv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedTime="1";
                        if(!isExpanded1) {
                            r1.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            r1.setVisibility(View.GONE);
                        }
                        isExpanded1=(isExpanded1)?false:true;
                    }
                });
                afterCv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedTime="2";
                        if(!isExpanded2) {
                            r2.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            r2.setVisibility(View.GONE);
                        }
                        isExpanded2=(isExpanded2)?false:true;
                    }
                });
                eveCv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedTime="3";
                        if(!isExpanded3) {
                            r3.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            r3.setVisibility(View.GONE);
                        }
                        isExpanded3=(isExpanded3)?false:true;
                    }
                });
                nightCv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedTime="4";
                        if(!isExpanded4) {
                            r4.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            r4.setVisibility(View.GONE);
                        }
                        isExpanded4=(isExpanded4)?false:true;
                    }
                });

            }
        });
        dayOfWeek=getIntent().getStringExtra("day");

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<4;i++)
                {
                    LinearLayout l=(i==0)?r1:(i==1)?r2:(i==2)?r3:r4;
                    selectedTime=(i==0)?"1":(i==1)?"2":(i==2)?"3":"4";
                    for(int j=0;j<6;j++)
                    {
                        View view=l.getChildAt(j);
                        Switch sw=view.findViewById(R.id.switchBtn);
                        EditText editText=view.findViewById(R.id.persons);
                        if(sw.isChecked())
                        {
                            String persons=editText.getText().toString();
                            if(TextUtils.isEmpty(editText.getText()))
                            {
                                persons="00";
                            }
                            else if(persons.length()==1)
                            {
                                persons="0"+persons;
                            }
                            selectedList.add(dayOfWeek+selectedTime+(j+1)+persons);
                        }
                    }
                }
                RequestPut put=new RequestPut(getApplicationContext());
                JSONArray obj=new JSONArray(selectedList);
                String url=AppUtils.HOST_ADDRESS+"/api/doctors/time_slab/"+UserAuth.getmUser().getGid();
                put.putJSONArray(url, obj, new RequestPut.JSONArrayResponseListener() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                    }
                });
                finish();
            }
        });

    }

    private void initRecView(LinearLayout linearLayout) {

        for(int i=1;i<=6;i++)
        {
            View view= LayoutInflater.from(this).inflate(R.layout.time_slab_item_layout,null);

            TextView title=view.findViewById(R.id.time);
            Switch sw=view.findViewById(R.id.switchBtn);
            EditText edt=view.findViewById(R.id.persons);

            title.setText(C.getTime(selectedTime+i));
            if(receivedList!=null)
            {
                for(int j=0;j<receivedList.length;j++)
                {
                    if(receivedList[j].substring(0,3).equals(dayOfWeek+selectedTime+i))
                    {
                        sw.setChecked(true);
                        edt.setText(receivedList[j].substring(3));
                    }
                }
            }
            linearLayout.addView(view);
        }

    }








}
