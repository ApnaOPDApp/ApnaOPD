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
import android.transition.ChangeBounds;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.R;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.util.ArrayList;
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
    private LinearLayout linearLayout,offer_list_ll;
    private int i=0;
    private ImageView presc_img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_offer_send);

       toolbar = (Toolbar) findViewById(R.id.t_toolbar);
        toolbar.setTitle("Send Offer");
        setSupportActionBar(toolbar);

        final Typeface custom_fonts = Typeface.createFromAsset(getAssets(),"font/Roboto-Black.ttf");

        listOfDrugs=new ArrayList<>();
        price = (EditText)findViewById(R.id.price);
        offer_price=(EditText)findViewById(R.id.offer_price);
        dosage_day = (EditText)findViewById(R.id.dosage_day);
        dosage_per=(EditText)findViewById(R.id.dosage_per);
        drug_name=(EditText)findViewById(R.id.drug_name);
        finish=(Button)findViewById(R.id.btn_finish);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.next_values);
        linearLayout=(LinearLayout)findViewById(R.id.ll_show_data);
        offer_list_ll=(LinearLayout)findViewById(R.id.offer_ll);
        presc_img = (ImageView)findViewById(R.id.pres_image);

        final ViewGroup tranitionContainer = (ViewGroup) findViewById(R.id.transiton_container);

        TransitionManager.beginDelayedTransition(tranitionContainer,new TransitionSet()
            .addTransition(new com.transitionseverywhere.ChangeBounds())
                .addTransition(new ChangeImageTransform())
        );

        ViewGroup.LayoutParams params = presc_img.getLayoutParams();
        boolean expanded = false;
        params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT:
                ViewGroup.LayoutParams.WRAP_CONTENT;
        presc_img.setLayoutParams(params);

        presc_img.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP:ImageView.ScaleType.FIT_CENTER);


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

                final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.offer_list_single_layout,null);
                TextView tv1 = view.findViewById(R.id.offer_list_tv);
                Button btn = view.findViewById(R.id.btn_offer_delete);
                tv1.setTypeface(custom_fonts);
                tv1.setGravity(Gravity.CENTER);

                linearLayout.addView(view);


                tv1.setText("Drug Name: "+strDrugName+"\nPrice: "+strPrice+"\nOffer Price: "+strOfferPrice+"\nDosage Per Day: "+dosagePer+"\nNo of Days: "+dosageDay+"\n");

                Map<String,String> map = new HashMap<>();
                map.put("id",i++ +"");
                final String id=i+++"";
                map.put("price",strPrice);
                map.put("offered_price",strOfferPrice);
                map.put("medicine",strDrugName);
                map.put("dosage_per",dosagePer);
                map.put("dosage_day",dosageDay);

                listOfDrugs.add(map);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        linearLayout.removeView(view);
                        Toast.makeText(RetailerOfferSendActivity.this, id, Toast.LENGTH_SHORT).show();

                    }
                });


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

                        listOfDrugs.add(map);

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
