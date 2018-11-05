package com.knstech.apnaopd.Retailer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.knstech.apnaopd.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetailerOfferSendActivity extends AppCompatActivity {


    private FloatingActionButton floatingActionButton;
    private EditText price,offer_price,drug_name,dosage_per,dosage_day;
    private Toolbar toolbar;
    private Button finish;
    List<Map<String,String>> listOfDrugs;
    private String strPrice,strOfferPrice,strDrugName,dosagePer,dosageDay,deliveryCharge,deliveryTime;
    private LinearLayout linearLayout;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_offer_send);

       toolbar = (Toolbar) findViewById(R.id.t_toolbar);
        toolbar.setTitle("Send Offer");
        setSupportActionBar(toolbar);

        final Typeface custom_fonts = Typeface.createFromAsset(getAssets(),"font/Roboto-Black.ttf");

        price = (EditText)findViewById(R.id.price);
        offer_price=(EditText)findViewById(R.id.offer_price);
        dosage_day = (EditText)findViewById(R.id.dosage_day);
        dosage_per=(EditText)findViewById(R.id.dosage_per);
        drug_name=(EditText)findViewById(R.id.drug_name);
        finish=(Button)findViewById(R.id.btn_finish);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.next_values);
        linearLayout=(LinearLayout)findViewById(R.id.ll_show_data);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strDrugName = drug_name.getText().toString();
                strOfferPrice = offer_price.getText().toString();
                strPrice = price.getText().toString();
                dosageDay=dosage_day.getText().toString();
                dosagePer=dosage_per.getText().toString();

                drug_name.setText("");
                offer_price.setText("");
                price.setText("");
                dosage_day.setText("");
                dosage_per.setText("");

                TextView tv1 = new TextView(RetailerOfferSendActivity.this);
                tv1.setTypeface(custom_fonts);
                tv1.setBackground(getDrawable(R.drawable.edit_text_background2));
                tv1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                tv1.setGravity(Gravity.CENTER);
                linearLayout.addView(tv1);


                tv1.setText("Drug Name: "+strDrugName+"\nPrice: "+strPrice+"\nOffer Price: "+strOfferPrice+"\nDosage Per Day: "+dosagePer+"\nNo of Days: "+dosageDay+"\n");

                Map<String,String> map = new HashMap<>();
                map.put("price",strPrice);
                map.put("offered_price",strOfferPrice);
                map.put("medicine",strDrugName);
                map.put("dosage_per",dosagePer);
                map.put("dosage_day",dosageDay);


            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RetailerOfferSendActivity.this);
                builder.setTitle("Delivery Details");
                LinearLayout layout = new LinearLayout(RetailerOfferSendActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText deliver_Charge = new EditText(RetailerOfferSendActivity.this);
                final EditText delivery_Time = new EditText(RetailerOfferSendActivity.this);
                layout.addView(deliver_Charge);
                layout.addView(delivery_Time);
                builder.setView(layout);
                deliver_Charge.setHint("Delivery Charge");
                delivery_Time.setHint("Delivery Time");

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        deliveryCharge = deliver_Charge.getText().toString();
                        deliveryTime = delivery_Time.getText().toString();
                        Map<String,String> map = new HashMap<>();
                        map.put("deliver_charge",deliveryCharge);
                        map.put("deliver_time",deliveryTime);
                        startActivity(new Intent(RetailerOfferSendActivity.this,RetailerActivity.class));
                        finish();

                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(RetailerOfferSendActivity.this, "Please do provide the deliver charge and time !", Toast.LENGTH_LONG).show();
                    }

                });

                    builder.show();
                }
        });

    }
}
