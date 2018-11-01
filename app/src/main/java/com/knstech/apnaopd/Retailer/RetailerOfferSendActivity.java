package com.knstech.apnaopd.Retailer;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.knstech.apnaopd.R;

public class RetailerOfferSendActivity extends AppCompatActivity {

    private int i=0;
    private TableLayout offer;
    private FloatingActionButton floatingActionButton;
    private EditText price,offer_price,drug_name;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_offer_send);

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Send Offer");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);


        offer = (TableLayout)findViewById(R.id.table);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.send_offer_btn);


        TableRow tbrow0 = new TableRow(this);

        TextView tv00=new TextView(this);
        tv00.setText(" sl.no   ");

        tv00.setTextColor(Color.RED);
        tbrow0.addView(tv00);

        TextView tv0=new TextView(this);
        tv0.setText(" Drug Name   ");
        tv0.setTextColor(Color.RED);
        tbrow0.addView(tv0);

        TextView tv1=new TextView(this);
        tv1.setText(" Price   ");
        tv1.setTextColor(Color.RED);
        tbrow0.addView(tv1);

        TextView tv2=new TextView(this);
        tv2.setText(" Offer Price   ");
        tv2.setTextColor(Color.RED);
        tbrow0.addView(tv2);

        TextView tv3=new TextView(this);
        tv3.setText(" Discount");
        tv3.setTextColor(Color.RED);
        tbrow0.addView(tv3);

        offer.addView(tbrow0);
        Button s= findViewById(R.id.set_val);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initial();
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RetailerOfferSendActivity.this, "We have to send the information to the patient in quotations", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initial(){


            price = (EditText)findViewById(R.id.price);
            offer_price = (EditText)findViewById(R.id.offer_price);
            drug_name = (EditText)findViewById(R.id.drug_name);
            double p1 = Double.parseDouble(price.getText().toString()+"0")/10;
            double o1=Double.parseDouble(offer_price.getText().toString()+"0")/10;
            double dis = (p1-o1)*100/p1;

            ++i;

            TableRow tb= new TableRow(this);

            TextView t1v = new TextView(this);
            t1v.setText(""+i);
            t1v.setGravity(Gravity.CENTER);
            tb.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(drug_name.getText().toString());
            t2v.setGravity(Gravity.CENTER);
            tb.addView(t2v);


            TextView t3v = new TextView(this);
            t3v.setText(price.getText().toString());
            t3v.setGravity(Gravity.CENTER);
            tb.addView(t3v);


            TextView t4v = new TextView(this);
            t4v.setText(offer_price.getText().toString());
            t4v.setGravity(Gravity.CENTER);
            tb.addView(t4v);


            TextView t5v = new TextView(this);
            t5v.setText(""+dis);
            t5v.setGravity(Gravity.CENTER);
            tb.addView(t5v);

            offer.addView(tb);
            price.setText("");
            offer_price.setText("");
            drug_name.setText("");


    }
}
