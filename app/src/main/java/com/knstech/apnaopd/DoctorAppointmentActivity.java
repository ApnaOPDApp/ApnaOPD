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
                updateCardio();
                break;
            /*case C.EAR:
                updateEar();
                break;
            case C.EYE:
                updateEye();
                break;
            case C.GENITO:
                updateGenito():
                break;
            case C.NEURO:
                updateNeuro();

            default:
                updateGeneral();*/

        }
    }

    private void updateCardio() {
        EditText pulse;
        Spinner neckVeins,chestPain,respiration,rhythm,bleeding,condition;
        Button fileUpload,submit;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(this).inflate(R.layout.cardio_cs,null);
        rootLayout.addView(selectedCS);

        //init editText
        pulse=selectedCS.findViewById(R.id.pulse);

        //init spinners
        neckVeins=selectedCS.findViewById(R.id.neckVeins);
        chestPain=selectedCS.findViewById(R.id.chestPain);
        respiration=selectedCS.findViewById(R.id.respiration);
        rhythm=selectedCS.findViewById(R.id.rhythm);
        bleeding=selectedCS.findViewById(R.id.bleeding);
        condition=selectedCS.findViewById(R.id.condition);

        //init buttons
        fileUpload=selectedCS.findViewById(R.id.upload);
        submit=selectedCS.findViewById(R.id.submit);

        String bleedAr[]=getResources().getStringArray(R.array.bleed_cardio);
        String rhythmAr[]=getResources().getStringArray(R.array.rhythm_cardio);
        String chestPainAr[]=getResources().getStringArray(R.array.chest_cardio);
        String respAr[]=getResources().getStringArray(R.array.resp_cardio);
        String neckAr[]=getResources().getStringArray(R.array.neck_cardio);
        String conditionAr[]=getResources().getStringArray(R.array.condition_cardio);
        //setAdapters
        AdapterUtil.setSpinnerAdapter(neckVeins,neckAr,this);
        AdapterUtil.setSpinnerAdapter(chestPain,chestPainAr,this);
        AdapterUtil.setSpinnerAdapter(respiration,respAr,this);
        AdapterUtil.setSpinnerAdapter(rhythm,rhythmAr,this);
        AdapterUtil.setSpinnerAdapter(bleeding,bleedAr,this);
        AdapterUtil.setSpinnerAdapter(condition,conditionAr,this);

        //init buttons
        submit=findViewById(R.id.submit);
        fileUpload=findViewById(R.id.upload);



    }

}
