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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Doctor;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
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
    private String selectedDay;
    private String patient_name,patient_age;

    public String getFees() {
        return fees;
    }



    public void setFees(String fees) {
        if(!fees.equals(""))
            this.fees = fees;
        else
            fees="1000000000";
    }


    public String getCity() {
        //set default
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String fees,dept;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        //set initial filters
        String feeStr=getIntent().getStringExtra("Fee");
        if(feeStr!=null)
        {
            fees=feeStr;
        }
        else
        {
            fees="1000000000";
        }
        dept=getIntent().getStringExtra("Department");
        patient_name=getIntent().getStringExtra("patient_name");
        patient_age=getIntent().getStringExtra("patient_age");


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
                    /*case R.id.time_item:
                        onTime();
                        return true;*/
                    case R.id.day:
                        onDay();
                        return true;
                }

                return false;
            }
        });

        requestGet=new RequestGet(this);

        initRecyclerView();

    }

    private void onTime() {

        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        View view=LayoutInflater.from(this).inflate(R.layout.time_item_layout,null);
        final LinearLayout linearLayout=view.findViewById(R.id.checkList);
        for(int i=0;i<24;i++)
        {
            String txt;
            String m;
            int time=(i%12==0)?12:i%12;
            m=(i%12==0)?"AM ":"PM ";
            txt=time+":00 "+m+" - "+time+":55 "+m;
            CheckBox cb=new CheckBox(this);
            cb.setChecked(true);
            cb.setText(txt);
            linearLayout.addView(cb);
        }
        Button select,clear,ok;
        select=view.findViewById(R.id.select_all);
        clear=view.findViewById(R.id.clear_all);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<24;i++)
                {
                    CheckBox cb= (CheckBox) linearLayout.getChildAt(i);
                    cb.setChecked(true);
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<24;i++)
                {
                    CheckBox cb= (CheckBox) linearLayout.getChildAt(i);
                    cb.setChecked(false);
                }
            }
        });

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mList.clear();
                for(int i=0;i<24;i++)
                {
                    /*CheckBox cb= (CheckBox) linearLayout.getChildAt(i);
                    if(cb.isChecked()) {
                        for (int j = 0; j < dupList.size(); j++) {
                            if (dupList.get(j).getTimeSlab().size() != 0) {
                                String truthVal = dupList.get(j).getTimeSlab().get(i).getAvailable();
                                if (truthVal.equals("true") && (mList.size() != 0 && !mList.get(mList.size() - 1).equals(dupList.get(j)) || mList.size() == 0)) {
                                    mList.add(dupList.get(j));
                                }
                            }
                        }
                    }
                    */

                }
                doctorAdapter.notifyDataSetChanged();
            }
        });
        dialog.setView(view);
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

    private void onDay()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        String ar[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        dialog.setTitle("Select Day of the Week");
        dialog.setSingleChoiceItems(ar, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedDay=""+(which+1);
                initRecyclerView();
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private void onCity() {
        final AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        String url=AppUtils.HOST_ADDRESS+"/api/doctors/filter/city";
        requestGet.getString(url, new RequestGet.StringResponseListener() {
            @Override
            public void onResponse(String str) {
                String ar[]=C.getStringArray(str);
                View view=LayoutInflater.from(DoctorListActivity.this).inflate(R.layout.city_dialog,null);
                dialog.setView(view);
                final AutoCompleteTextView city=view.findViewById(R.id.city);
                if(ar!=null) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(DoctorListActivity.this, R.layout.simple_text, ar);
                    city.setAdapter(adapter);
                }
                dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setCity(city.getText().toString());
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
            public void onDoctorItemClick(String uid,int position) {
                Intent i=new Intent(DoctorListActivity.this,DoctorViewerActivity.class);
                i.putExtra("Doctor",uid);
                i.putExtra("cs_uid",getIntent().getStringExtra("cs_uid"));
                i.putExtra("comment",getIntent().getStringExtra("comment"));
                i.putExtra("patient_name",patient_name);
                i.putExtra("patient_age",patient_age);
                i.putExtra("day",position);
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
        String url= AppUtils.HOST_ADDRESS+"/api/doctors/filter?fee="+getFees()+"&department="+dept;

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
                        if(checkFilters(doctor)) {
                            mList.add(doctor);
                        }
                        doctorAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean checkFilters(Doctor doctor) {

        boolean result=false;
        if(selectedDay==null||isPresent(doctor.getTimeSlab())||selectedDay.equals(doctor.getTimeSlab().substring(0,1))) {
            if (Integer.parseInt(doctor.getFee()) <= Integer.parseInt(getFees())) {
                if (getCity()==null||(getCity()!=null&&getCity().equals(""))) {
                    result = true;
                } else {
                    if (doctor.getAddress().getCity()!=null&&doctor.getAddress().getCity().equals(getCity())) {
                        result = true;
                    }
                }
            }
        }

        return result;

    }

    private boolean isPresent(String timeSlab) {
        String ar[]= C.getStringArray(timeSlab);
        boolean res=false;
        for(int i=0;i<ar.length;i++)
        {
            if(ar[i].substring(0,1).equals(selectedDay))
            {
                res=true;
                break;
            }

        }
        return res;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
