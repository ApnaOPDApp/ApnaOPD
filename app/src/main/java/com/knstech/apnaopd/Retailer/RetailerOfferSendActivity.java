package com.knstech.apnaopd.Retailer;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestPut;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;


import org.json.JSONObject;

import java.io.File;
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
    private double total_offer_price;
    private int IMG_REQUEST = 1;
    private String path;
    private String pic_url = AppUtils.HOST_ADDRESS+"/api/uploadQuotation";
    private String prescription_image_url = null;



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

        String image_url=AppUtils.HOST_ADDRESS+getIntent().getStringExtra("image_url");


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

                     String m = medicine.getText().toString().split(":")[1];
                     String p = price.getText().toString().split(":")[1];
                     String o = oprice.getText().toString().split(":")[1];
                     String dp= dper.getText().toString().split(":")[1];
                     String d = dday.getText().toString().split(":")[1];
                     double op=Double.valueOf(o);
                     total_offer_price+=op;

                     map.put("medicine",m);
                     map.put("price",p);
                     map.put("offered_price",o);
                     map.put("dosage_per",dp);
                     map.put("dosage_day",d);
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
                        map.put("delivery_charge",deliveryCharge);
                        map.put("delivery_time",deliveryTime);
                        map.put("retailer_gid",AppUtils.RET_GID);
                        map.put("quotation",listOfDrugs);
                        map.put("quotation_link",prescription_image_url);
                        map.put("total_price",total_offer_price+"");
                        Map params=new HashMap();

                        String _id = getIntent().getStringExtra("_id");
                //        params.put("patient_gid",getIntent().getStringExtra("pid"));
                        params.put("offers",map);
                        JSONObject obj=new JSONObject(params);

                        String url=AppUtils.HOST_ADDRESS+"/api/orders/"+_id;
                        RequestPut put=new RequestPut(getApplicationContext());
                        put.putJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                                    @Override
                                    public void onResponse(JSONObject object) {
                                        Toast.makeText(RetailerOfferSendActivity.this, object.toString(), Toast.LENGTH_LONG).show();
                                    }
                                },
                                new RequestPut.JSONObjectErrorListener() {
                                    @Override
                                    public void onError(VolleyError error) {
                                        Toast.makeText(RetailerOfferSendActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_patient_drawer,menu);
        return true;
    }

    /* Inflating signout button in menu of home activity  */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.upload_bill) {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,IMG_REQUEST);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null) {

            Uri uri = data.getData();

            try {

                path= (String) getPathFromURI(data.getData());
                File file = new File(path);
                Future uploading = Ion.with(RetailerOfferSendActivity.this)
                        .load(pic_url)
                        .setMultipartFile("prescription",file)
                        .setMultipartParameter("gid",AppUtils.USER_GID)
                        .asString()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<String>>() {
                            @Override
                            public void onCompleted(Exception e, com.koushikdutta.ion.Response<String> result) {

                                try {
                                    prescription_image_url = result.getResult();

                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                            }
                        });

            } catch (Exception e) {
                Toast.makeText(this, "Null Value", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }

    private String getPathFromURI(Uri contentUri){

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),contentUri,proj,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int coloumn_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(coloumn_index);
        return cursor.getString(coloumn_index);
    }



}
