package com.knstech.apnaopd.Retailer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
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

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPost;


import org.json.JSONObject;

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
        presc_img = (ImageView)findViewById(R.id.pres_image);

        String image_url=getIntent().getStringExtra("image_url");
        Uri uri = Uri.parse(image_url);
        if(uri!=null)
            Glide.with(getApplicationContext()).load(uri).into(presc_img);

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

                final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.offer_single_layout,null);
                TextView medicine,price,oprice,dper,dday;
                Button btn=view.findViewById(R.id.btn_offer_delete);
                medicine=view.findViewById(R.id.tv_offer_single_medicine);
                price=view.findViewById(R.id.tv_offer_single_price);
                oprice=view.findViewById(R.id.tv_offer_single_offered_price);
                dper=view.findViewById(R.id.tv_offer_single_dosage_per);
                dday=view.findViewById(R.id.tv_offer_single_dosage_day);
                medicine.setText("Medicine :"+strDrugName);
                price.setText("Price :"+strPrice);
                oprice.setText("Offer Price :"+strOfferPrice);
                dper.setText("Dosage Per Day :"+dosagePer);
                dday.setText("Dosage Days :"+dosageDay);


                linearLayout.addView(view);



                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        linearLayout.removeView(view);

                    }
                });


            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                //populate listOfDrugs
                for(int i=0;i<linearLayout.getChildCount();i++)
                {
                    
                     View view=  linearLayout.getChildAt(i);
                     TextView medicine,price,oprice,dper,dday;
                     medicine=view.findViewById(R.id.tv_offer_single_medicine);
                     price=view.findViewById(R.id.tv_offer_single_price);
                     oprice=view.findViewById(R.id.tv_offer_single_offered_price);
                     dper=view.findViewById(R.id.tv_offer_single_dosage_per);
                     dday=view.findViewById(R.id.tv_offer_single_dosage_day);
                     Map<String,String> map=new HashMap<>();
                     map.put("medicine",medicine.getText().toString());
                     map.put("price",price.getText().toString());
                     map.put("offered_price",oprice.getText().toString());
                     map.put("dosage_per",dper.getText().toString());
                     map.put("dosage_day",dday.getText().toString());
                     listOfDrugs.add(map);
                }
                
                

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
                        Map map = new HashMap();
                        map.put("deliver_charge",deliveryCharge);
                        map.put("deliver_time",deliveryTime);
                        map.put("retailer_gid","ret2");
                        map.put("status","0");
                        map.put("eoffer",listOfDrugs);
                        Map params=new HashMap();
                        params.put("patient_gid",getIntent().getStringExtra("pid"));
                        params.put("offers",map);
                        JSONObject obj=new JSONObject(params);
                        String url=AppUtils.HOST_ADDRESS+"/api/offers/";
                        RequestPost post=new RequestPost(getApplicationContext());
                        post.sendJSON(url, obj, new RequestPost.PostResponseListener() {
                            @Override
                            public void onResponse() {
                                Toast.makeText(RetailerOfferSendActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                            }
                        }, new RequestPost.PostErrorListener() {
                            @Override
                            public void onError() {
                                Toast.makeText(RetailerOfferSendActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        
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
