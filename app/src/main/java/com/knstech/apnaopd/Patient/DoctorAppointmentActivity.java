package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.AdapterUtil;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
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
    private Button submit;
    private Spinner deptSpinner;
    public static int state=1;
    private String[] deptAr;
    private View deptView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);
        rootLayout=findViewById(R.id.rootLayout);

        deptView=LayoutInflater.from(this).inflate(R.layout.dept_select_layout,null);
        rootLayout.addView(deptView);
        submit=deptView.findViewById(R.id.submit);
        deptSpinner=deptView.findViewById(R.id.deptSpinner);
        getArrayFromServer();

        //set submit functionality
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoice();
                toggle();
            }
        });



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

    public void setChoice()
    {
        choice=deptSpinner.getSelectedItemPosition();
    }

    private void updateUI() {
        switch (choice)
        {
            case C.CARDIO:
                UIUpdater.updateCardio(this,rootLayout,selectedCS);
                break;
            case C.EAR:
                UIUpdater.updateEar(this,rootLayout,selectedCS);
                break;
            case C.EYE:
                UIUpdater.updateEye(this,rootLayout,selectedCS);
                break;
            case C.GENITO:
                UIUpdater.updateGenito(this,rootLayout,selectedCS);
                break;
            case C.NEURO:
                UIUpdater.updateNeuro(this,rootLayout,selectedCS);

            default:
                UIUpdater.updateGeneral(this,rootLayout,selectedCS);

        }
    }


    public void getArrayFromServer() {

        RequestGet requestGet=new RequestGet(getApplicationContext());
        final List<String> list=new ArrayList<>();
        String url="https://jsonplaceholder.typicode.com/posts";
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray){
                 for(int i=0;i<jsonArray.length();i++)
                 {
                     JSONObject obj= null;
                     try {
                         obj = jsonArray.getJSONObject(i);
                         list.add(obj.getString("id"));
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
                 list.add(0,"Select");
                 deptAr=list.toArray(new String[0]);
                 AdapterUtil.setSpinnerAdapter(deptSpinner,deptAr,DoctorAppointmentActivity.this);
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
}
