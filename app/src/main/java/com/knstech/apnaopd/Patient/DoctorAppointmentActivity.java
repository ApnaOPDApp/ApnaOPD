package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.DepatmentClickListener;
import com.knstech.apnaopd.Utils.UIUpdater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentActivity extends AppCompatActivity {
    private int choice;
    private View selectedCS;
    private RelativeLayout rootLayout;
    public static int state=1;
    private String[] deptAr;
    private View deptView;
    private String feeStr;
    private RecyclerView deptList;
    private LinearLayoutManager linearLayoutManager;
    private List<Department> mList;
    private DepartmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);
        rootLayout=findViewById(R.id.rootLayout);

        deptView=LayoutInflater.from(this).inflate(R.layout.dept_select_layout,null);
        rootLayout.addView(deptView);
        getArrayFromServer();


        deptList=findViewById(R.id.deptList);
        populateRecyclerView();



    }

    private void populateRecyclerView() {

        mList=new ArrayList<>();
        adapter=new DepartmentAdapter(mList, getApplicationContext(), new DepatmentClickListener() {
            @Override
            public void onClick(Department department) {
                toggle();
                choice=Integer.parseInt(department.getId());
            }
        });
        linearLayoutManager=new LinearLayoutManager(this);

        deptList.setAdapter(adapter);
        deptList.setHasFixedSize(true);
        deptList.setLayoutManager(linearLayoutManager);


    }

    public String getFeeStr()
    {
        return feeStr;
    }
    private void toggle() {
        if(state==1)
        {
            state=2;
            rootLayout.removeAllViews();
            updateUI();
        }
        else
        {
            state=1;
            rootLayout.removeAllViews();
            rootLayout.addView(deptView);
        }

    }

    @Override
    public void onBackPressed() {
        if(state==2) {
            toggle();
        }
        else {
            finish();
        }
    }


    private void updateUI() {
        String csGenUrl= AppUtils.HOST_ADDRESS+"/api/casesheets";
        RequestGet requestGet=new RequestGet(this);
        requestGet.getJSONObject(csGenUrl, new RequestGet.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject object) {
                switch (choice)
                {
                    case C.CARDIO:
                        UIUpdater.updateCardio(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.EAR:
                        UIUpdater.updateEar(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.EYE:
                        UIUpdater.updateEye(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.GENITO:
                        UIUpdater.updateGenito(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.NEURO:
                        UIUpdater.updateNeuro(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    default:
                        UIUpdater.updateGeneral(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);

                }
            }
        });



    }


    public void getArrayFromServer() {

        RequestGet requestGet=new RequestGet(getApplicationContext());
        String url="https://jsonplaceholder.typicode.com/posts";
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray){
                 for(int i=0;i<jsonArray.length();i++)
                 {
                     JSONObject obj= null;
                     try {

                         obj = jsonArray.getJSONObject(i);
                         Department dept=new Department();
                         dept.setId(obj.getString("id"));
                         dept.setName(obj.getString("id"));
                         mList.add(dept);
                         adapter.notifyDataSetChanged();
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==UIUpdater.IMG)
        {
            if(resultCode==RESULT_OK)
            {

            }
        }
    }

    public String getDepartment() {

        return ""+choice;
    }

    public int getChoice() {
        return choice;
    }
}
