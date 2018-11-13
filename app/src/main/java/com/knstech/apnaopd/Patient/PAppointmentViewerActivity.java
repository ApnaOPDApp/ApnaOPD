package com.knstech.apnaopd.Patient;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.knstech.apnaopd.Patient.ADAPTORS.AppointmentPagerAdapter;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PAppointmentViewerActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager pager;
    private AppointmentPagerAdapter adapter;
    private List<Patient> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pappointment_viewer);

        RequestGet requestGet=new RequestGet(getApplicationContext());
        String url= AppUtils.HOST_ADDRESS+"/api/appointments/patient/"+ UserAuth.getmUser(PAppointmentViewerActivity.this).getGid();
        mList=new ArrayList<>();

        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject obj=jsonArray.getJSONObject(i);
                        Patient patient=Patient.parseFromJson(obj.toString());
                        mList.add(patient);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                pager=findViewById(R.id.viewPager);
                tabLayout=findViewById(R.id.tabLayout);
                adapter=new AppointmentPagerAdapter(getSupportFragmentManager());
                pager.setAdapter(adapter);
                tabLayout.setupWithViewPager(pager);

            }
        });






    }

    public List<Patient> getmList() {
        return mList;
    }

    public void setmList(List<Patient> mList) {
        this.mList = mList;
    }
}
