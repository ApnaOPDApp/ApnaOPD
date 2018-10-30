package com.knstech.apnaopd.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.BitmapToString;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MedicineActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private CardView btn_submit;
    private RelativeLayout med_activity_layout;
    private LinearLayout uploadPrescription;
    private final int IMG_REQUEST =1;
    private Bitmap bitmap;
    private  String url = AppUtils.HOST_ADDRESS+"/api/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        med_activity_layout=(RelativeLayout)findViewById(R.id.med_activity_layout);

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Pharmacy");
        btn_submit=(CardView)findViewById(R.id.med_btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar=Snackbar.make(med_activity_layout,"Check Your Quotations for more information about the retailers near you",Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });

        uploadPrescription = (LinearLayout)findViewById(R.id.upload_prescription);
        uploadPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri path = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // UPLOAD THE IMAGE
            final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.hide();

                            // JASON OBJECT RESPONSE
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String res = jsonObject.getString("response");
                                Toast.makeText(MedicineActivity.this, res, Toast.LENGTH_SHORT).show();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MedicineActivity.this,error.toString(), Toast.LENGTH_SHORT).show();

                            progressDialog.hide();
                        }
                    }

            )
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<>();
                    params.put("Name","NAME OF PRESCRIPTION OF USER");
                    params.put("IMAGE OF PRESCRIPTION",new BitmapToString().imageToString(bitmap));


                    return params;
                }
            };

            VolleySingleton.getmInstance().addToRequestQueue(stringRequest);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.medicine_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
