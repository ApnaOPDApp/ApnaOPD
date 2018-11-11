package com.knstech.apnaopd.Retailer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestDelete;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.OnDeclineClickedListener;
import com.knstech.apnaopd.GenModelClasses.User.PojoUploadPrescription;

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
    private  String url = AppUtils.HOST_ADDRESS+"/api/retailers/orders/"+AppUtils.RET_GID;
    private Toolbar toolbar;
 //   private String json_pres_id;

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
                String url=AppUtils.HOST_ADDRESS+"/api/retailers/orders/"+AppUtils.RET_GID;
                RequestDelete requestDelete=new RequestDelete(ListOfPrescriptionActivity.this);
                requestDelete.requestDelete(url, new RequestDelete.OnDeleteListener() {
                    @Override
                    public void onDelete() {
                        Toast.makeText(ListOfPrescriptionActivity.this, "Prescription Deleted Successful!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        preListView.setAdapter(mAdaptor);


            try {

                RequestGet request = new RequestGet(this);
                request.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                PojoUploadPrescription uploadPrescription = PojoUploadPrescription.parseFromJson(jsonObject);
                                data.add(uploadPrescription);
                                mAdaptor.notifyDataSetChanged();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


            } catch (Exception e1) {
                e1.printStackTrace();
            }




    }

}
