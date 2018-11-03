package com.knstech.apnaopd.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.JsonObject;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.BitmapToString;
import com.knstech.apnaopd.GenModalClasses.User.Address;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.AddressClickedListener;
import com.knstech.apnaopd.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private CardView btn_submit;
    private RelativeLayout med_activity_layout;
    private LinearLayout uploadPrescription;
    private final int IMG_REQUEST =1;
    private Bitmap bitmap;
    private RecyclerView addressView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdaptor;
    private List<Address> addressPojoList;
    private Address selectedAddress;

    private  String url = AppUtils.HOST_ADDRESS+"/api/prescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        med_activity_layout=(RelativeLayout)findViewById(R.id.med_activity_layout);
        addressView =(RecyclerView)findViewById(R.id.med_scroll);



        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Pharmacy");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

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


        populateRecyclerView();


    }

    private void populateRecyclerView() {

        addressView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        addressView.setLayoutManager(layoutManager);
        mAdaptor = new AddressGetAdaptor(addressPojoList, getApplicationContext(), new AddressClickedListener() {
            @Override
            public void onAddressChecked(Address address) {
                selectedAddress=address;
            }
        });
        addressView.setAdapter(mAdaptor);

        RequestGet getRequest=new RequestGet(this);
        getRequest.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {

                    try {
                        JSONObject object= (JSONObject) jsonArray.get(i);
                        Address address= (Address) Address.parseFromJson(object.getJSONObject("address").toString());
                        addressPojoList.add(address);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdaptor.notifyDataSetChanged();
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
           /* final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("Uploading...");
            progressDialog.show();*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                          //  progressDialog.hide();

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

                           // progressDialog.hide();
                        }
                    }

            )
            {


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<>();
                    params.put("address_id","2");
                    params.put("prescription_link",new BitmapToString().imageToString(bitmap));


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
