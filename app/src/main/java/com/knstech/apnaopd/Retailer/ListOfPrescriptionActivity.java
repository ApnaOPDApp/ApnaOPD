package com.knstech.apnaopd.Retailer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.Address;
import com.knstech.apnaopd.GenModalClasses.User.PojoUploadPrescription;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestDelete;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.OnDeclineClickedListener;
import com.knstech.apnaopd.Volley.NetworkJSONLoader;
import com.knstech.apnaopd.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfPrescriptionActivity extends AppCompatActivity {

    private RecyclerView preListView;
    private RecyclerView.LayoutManager layoutManager;
    private RetailerPrescriptionAdaptor mAdaptor;
    private List<PojoUploadPrescription> data;
    private  String url = AppUtils.HOST_ADDRESS+"/api/prescriptions/ret2/";
    private Toolbar toolbar;
    private String json_pres_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prescription);


        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("List of Prescriptions");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        preListView = (RecyclerView) findViewById(R.id.retailer_list_prescription);
        preListView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        preListView.setLayoutManager(layoutManager);
        data = new ArrayList<>();
        mAdaptor = new RetailerPrescriptionAdaptor(getApplicationContext(), data, this, new OnDeclineClickedListener() {
            @Override
            public void onDecline(String id) {
                String url=AppUtils.HOST_ADDRESS+"/api/retailers/presc_list/ret2/"+id;
                RequestDelete requestDelete=new RequestDelete(ListOfPrescriptionActivity.this);
                requestDelete.requestDelete(url, new RequestDelete.OnDeleteListener() {
                    @Override
                    public void onDelete() {
                        Toast.makeText(ListOfPrescriptionActivity.this, "Prescription Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        preListView.setAdapter(mAdaptor);


        json_pres_id = getIntent().getStringExtra("id array");

            try {
                JSONArray pres_id = new JSONArray(json_pres_id);
                for(int j=0;j<pres_id.length();j++){
                    JSONObject obj = pres_id.getJSONObject(j);
                    final String pid = obj.getString("prescription_id");

                    RequestGet request = new RequestGet(this);
                    request.getJSONObject(url+pid, new RequestGet.JSONObjectResponseListener() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                            Gson gson = new Gson();
                            PojoUploadPrescription uploadPrescription = gson.fromJson(jsonObject.toString(), PojoUploadPrescription.class);
                            uploadPrescription.setPrescription_id(pid);
                            data.add(uploadPrescription);
                            mAdaptor.notifyDataSetChanged();
                        }
                    });
                }


            } catch (JSONException e1) {
                e1.printStackTrace();
            }




    }

}
