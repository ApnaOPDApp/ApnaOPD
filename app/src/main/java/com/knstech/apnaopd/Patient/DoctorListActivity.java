package com.knstech.apnaopd.Patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.Doctor.Doctor;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.DoctorItemClickedListener;

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
    private RequestGet requestGet;

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        if(!fees.equals(""))
            this.fees = fees;
        else
            fees="1000000000";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(!date.equals(""))
            this.date = date;
        else
            date=""+System.currentTimeMillis();
    }

    public String getCity() {
        //set default
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String fees,dept,date,time;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        //set initial filters
        String feeStr=getIntent().getStringExtra("Fee");
        if(!feeStr.equals(""))
        {
            fees=feeStr;
        }
        dept=getIntent().getStringExtra("Department");


        //init bottom nav
        BottomNavigationView navigationView=findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.city_item:
                        onCity();
                        return true;
                    case R.id.fee_item:
                        onFee();
                        return true;
                    case R.id.date_item:
                        onDate();
                        return true;
                }

                return false;
            }
        });

        requestGet=new RequestGet(this);

        initRecyclerView();

    }

    private void onDate() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        View view=LayoutInflater.from(this).inflate(R.layout.date_dialog,null);
        dialog.setView(view);
        final DatePicker datePicker=view.findViewById(R.id.date);
        datePicker.setMinDate(System.currentTimeMillis());
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDate(""+datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear());
                initRecyclerView();
            }
        }).setNegativeButton(R.string.cancel,null);
        dialog.show();
    }

    private void onFee() {
        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        View view=LayoutInflater.from(this).inflate(R.layout.fee_dialog,null);
        final EditText edt=view.findViewById(R.id.fee);
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String feeStr=edt.getText().toString();
                setFees(feeStr);
                initRecyclerView();
            }
        }).setNegativeButton(R.string.cancel,null);
        dialog.setView(view);
        dialog.show();
    }


    private void onCity() {
        final AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        String url=AppUtils.HOST_ADDRESS+"/api/doctors/filter/city";
        requestGet.getString(url, new RequestGet.StringResponseListener() {
            @Override
            public void onResponse(String str) {
                String ar[];
                if(str.length()!=2)
                {
                    String s=str.substring(2,str.length()-2);
                    ar=s.split("\",\"");
                }
                else
                {
                    ar=null;
                }
                View view=LayoutInflater.from(DoctorListActivity.this).inflate(R.layout.city_dialog,null);
                dialog.setView(view);
                final AutoCompleteTextView city=view.findViewById(R.id.city);
                ArrayAdapter<String> adapter=new ArrayAdapter<>(DoctorListActivity.this,R.layout.simple_text,ar);
                city.setAdapter(adapter);
                dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityStr=city.getText().toString();
                        setCity(cityStr);
                        initRecyclerView();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });
    }

    private void initRecyclerView() {



        doctorsList=findViewById(R.id.doctorsList);
        mList=new ArrayList<>();
        doctorAdapter=new DoctorAdapter(this, mList, new DoctorItemClickedListener() {
            @Override
            public void onDoctorItemClick(String uid) {
                Intent i=new Intent(DoctorListActivity.this,DoctorViewerActivity.class);
                i.putExtra("Doctor",uid);
                startActivity(i);

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

        //set filters
        String url= AppUtils.HOST_ADDRESS+"/api/doctors/filter?fee="+getFees()+"&department="+dept+"&city="+getCity()+"&date="+getDate();

        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject object=jsonArray.getJSONObject(i);
                        String json=object.toString();
                        Doctor doctor=new Doctor();
                        doctor.parseFromJson(json);
                        mList.add(doctor);
                        doctorAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
