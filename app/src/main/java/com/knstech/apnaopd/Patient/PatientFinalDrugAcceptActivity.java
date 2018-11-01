package com.knstech.apnaopd.Patient;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.knstech.apnaopd.R;

public class PatientFinalDrugAcceptActivity extends AppCompatActivity {

    private TableLayout offer;
    private Button accept_offer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_final_drug_accept);
        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Drug Description");

        offer = (TableLayout)findViewById(R.id.patient_table);

        TableRow tbrow0 = new TableRow(this);

        TextView tv00=new TextView(this);
        tv00.setText(" sl.no   ");

        tv00.setTextColor(Color.BLUE);
        tbrow0.addView(tv00);

        TextView tv0=new TextView(this);
        tv0.setText(" Drug Name   ");
        tv0.setTextColor(Color.BLUE);
        tbrow0.addView(tv0);

        TextView tv1=new TextView(this);
        tv1.setText(" Price   ");
        tv1.setTextColor(Color.BLUE);
        tbrow0.addView(tv1);

        TextView tv2=new TextView(this);
        tv2.setText(" Offer Price   ");
        tv2.setTextColor(Color.BLUE);
        tbrow0.addView(tv2);

        TextView tv3=new TextView(this);
        tv3.setText(" Discount");
        tv3.setTextColor(Color.BLUE);
        tbrow0.addView(tv3);
        offer.addView(tbrow0);

        accept_offer = (Button)findViewById(R.id.accept_offer);
        accept_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientFinalDrugAcceptActivity.this, "Send the user name to retailer confiremd list", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
