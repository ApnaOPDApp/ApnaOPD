package com.knstech.apnaopd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.knstech.apnaopd.Utils.AdapterUtil;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.UIUpdater;

public class DoctorAppointmentActivity extends AppCompatActivity {
    private int choice;
    private View selectedCS;
    private RelativeLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);
        rootLayout=findViewById(R.id.rootLayout);
        //choice=getIntent().getIntExtra("department",-1);
        choice=C.CARDIO;
        updateUI();
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



}
