package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.knstech.apnaopd.Patient.ADAPTORS.AddressGetAdaptor;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.GenModelClasses.User.Address;
import com.knstech.apnaopd.Profile.AddressActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPost;
import com.knstech.apnaopd.Utils.Listeners.AddressClickedListener;
import com.knstech.apnaopd.Utils.MyConnectionTester;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MedicineActivity extends AppCompatActivity {


    private Toolbar toolbar;                                                       // for intregating toolbar in medicine activity
    private RelativeLayout med_activity_layout;
    private Button add_address;                                                    //  button to send user to add address activity
    private Button btn_submit;                                                     // to to submit response of patient (Upload prescrition, address, comment )
    private Button uploadPrescription;                                             // button to open file chooser and this button will upload the image to server
    private final int IMG_REQUEST =1;
    private String prescription_image_url;
    private RecyclerView addressView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdaptor;
    private List<Address> addressPojoList;
    private Address selectedAddress;
    private EditText comment;
    private String path;
    private String filter = "0";
    private String url = AppUtils.HOST_ADDRESS+"/api/orders/"+filter;
   // private String url1=AppUtils.HOST_ADDRESS+"/api/users/"+AppUtils.USER_GID;
    private String addressURL = AppUtils.HOST_ADDRESS+"/api/users/address/"+AppUtils.USER_GID;
    private String pic_url = AppUtils.HOST_ADDRESS+"/api/uploadPrescription";
    private String cmt;
    private Spinner e_prescription;
    private List<Address> listAddr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        /* Toolbar implementation in medicine activity */

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Pharmacy");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(MedicineActivity.this)){
            connnection.buildDialog(MedicineActivity.this).show();
        }


        /*Configuring ION library for uploading image to server  */

        Ion.getDefault(this).configure().setLogging("ion-smple", Log.DEBUG);


        /* Linking java object with XML  */

        med_activity_layout=(RelativeLayout)findViewById(R.id.med_activity_layout);
        addressView =(RecyclerView)findViewById(R.id.med_scroll);
        uploadPrescription = (Button)findViewById(R.id.btn_upload_prescription);
        comment = (EditText)findViewById(R.id.editText_cmt);
        btn_submit=(Button)findViewById(R.id.med_btn_submit);
        add_address = (Button)findViewById(R.id.med_btn_add_add);
        e_prescription=(Spinner)findViewById(R.id.e_prescription);

        /*Adding address button if address is not alreay present */

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicineActivity.this,AddressActivity.class));
            }
        });


        /* Uploading image prescription on server and getting url as response */

        uploadPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);

            }

        });

        /*  Sending values to database (image url, address, comment )*/

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cmt = comment.getText().toString();         // getting text of comment field

                /*mapping database with java objects */

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
                params.put("photo_prescription_link",prescription_image_url);
                params.put("patient_gid",AppUtils.USER_GID);
                params.put("comment",cmt);

                JSONObject obj = new JSONObject(params);
                JSONArray array=new JSONArray();
                array.put(obj);
                RequestPost request = new RequestPost(getApplicationContext());

                    request.sendJSONArray(url,array, new RequestPost.ArrayResponseListener() {
                        @Override
                        public void onResponse(JSONArray array) {
                            Toast.makeText(MedicineActivity.this, array.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });


                Snackbar snackbar=Snackbar.make(med_activity_layout,"Check Your Quotations for more information about the retailers near you",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });


        /* Method to populate the Medical Activity Layout  */
        populateRecyclerView();


   }

//-----------------------------------------------------------------------------------------------------------------------------------


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

        RequestGet getRequest=new RequestGet(this);
        getRequest.getJSONArray(addressURL, new RequestGet.JSONArrayResponseListener() {
            @Override

            public void onResponse(JSONArray obj) {

                       listAddr=Address.parseFromJson(obj.toString());
                        for(int j=0;j<listAddr.size();j++)
                        {

                            if(listAddr.get(j)!=null) {
                                addressPojoList.add(listAddr.get(j));

                                mAdaptor.notifyDataSetChanged();
                            }


                        }

        }
    });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null) {

            Uri uri = data.getData();

                try {
                  path= (String) getPathFromURI(data.getData());
                    File file = new File(path);
                    Future uploading = Ion.with(MedicineActivity.this)
                            .load(pic_url)
                            .setMultipartFile("prescription",file)
                            .setMultipartParameter("gid",AppUtils.USER_GID)
                            .asString()
                            .withResponse()
                            .setCallback(new FutureCallback<com.koushikdutta.ion.Response<String>>() {
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

                    e.printStackTrace();
                }
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){

            case 1 :
                        filter = "0";
                break;
            case 2:
                        filter = "1";
                break;

        }


        return super.onOptionsItemSelected(item);
    }

// method to get path of image from Uri

    private String getPathFromURI(Uri contentUri){

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),contentUri,proj,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int coloumn_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(coloumn_index);
        return cursor.getString(coloumn_index);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MedicineActivity.this,HomeActivity.class));
        finish();
    }
}
