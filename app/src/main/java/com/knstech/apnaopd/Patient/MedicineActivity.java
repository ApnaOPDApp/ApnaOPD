package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.BitmapToString;
import com.knstech.apnaopd.GenModalClasses.User.Address;
import com.knstech.apnaopd.GenModalClasses.User.User;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.AddressClickedListener;
import com.knstech.apnaopd.Volley.VolleySingleton;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.knstech.apnaopd.GenModalClasses.User.Address.parseFromJson;

public class MedicineActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private CardView btn_submit;
    private RelativeLayout med_activity_layout;
    private LinearLayout uploadPrescription;
    private final int IMG_REQUEST =1;
    private Bitmap bitmap; String str_bitmap;
    private RecyclerView addressView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdaptor;
    private List<Address> addressPojoList;
    private List<Address> usersDetails;
    private Address selectedAddress;
    private EditText comment;

    private  String url = AppUtils.HOST_ADDRESS+"/api/prescriptions";
    String url1=AppUtils.HOST_ADDRESS+"/api/users/1";
    String addressURL = AppUtils.HOST_ADDRESS+"/api/users/address/1";


    private String cmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Pharmacy");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);


        med_activity_layout=(RelativeLayout)findViewById(R.id.med_activity_layout);
        addressView =(RecyclerView)findViewById(R.id.med_scroll);
        uploadPrescription = (LinearLayout)findViewById(R.id.upload_prescription);
        comment = (EditText)findViewById(R.id.editText_cmt);



        btn_submit=(CardView)findViewById(R.id.med_btn_submit);

        //-------------UPLOAD PRESCRIPTION ------------------------------------

        uploadPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cmt = comment.getText().toString();

                Map<String,String> adres = new HashMap<>();
                adres.put("full_name",selectedAddress.getFull_name());
                adres.put("house_no",selectedAddress.getHouse_no());
                adres.put("locality",selectedAddress.getLocality());
                adres.put("landmark",selectedAddress.getLocality());
                adres.put("city",selectedAddress.getCity());
                adres.put("state",selectedAddress.getState());
                adres.put("pincode",selectedAddress.getPincode());
                adres.put("phone_number",selectedAddress.getPhone_number());


                Map params = new HashMap();
                params.put("address",adres);
                params.put("photo_prescription_link","prescription link jae ga");
                params.put("patient_gid","1");
                params.put("comment",cmt);

                JSONObject obj = new JSONObject(params);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,
                        obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MedicineActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MedicineActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                VolleySingleton.getmInstance().addToRequestQueue(request);


                Snackbar snackbar=Snackbar.make(med_activity_layout,"Check Your Quotations for more information about the retailers near you",Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });


        populateRecyclerView();


    }


    List<Address> listAddr;
    private void populateRecyclerView() {

        addressView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        addressView.setLayoutManager(layoutManager);
        addressPojoList = new ArrayList<>();

        mAdaptor = new AddressGetAdaptor(addressPojoList, getApplicationContext(), new AddressClickedListener() {
            @Override
            public void onAddressChecked(Address address) {
                selectedAddress=address;
            }
        });
        addressView.setAdapter(mAdaptor);


       listAddr= new ArrayList<>();
/*
        RequestGet getRequest=new RequestGet(this);
        getRequest.getJSONArray(addressURL, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONObject obj) {
                User user=new User();
                user=user.parseFromJson(obj.toString());

            }
        });
*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null) {
            Uri path = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                str_bitmap = new BitmapToString().imageToString(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

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

    private void showFileChooser(){


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),IMG_REQUEST);

    }


}
