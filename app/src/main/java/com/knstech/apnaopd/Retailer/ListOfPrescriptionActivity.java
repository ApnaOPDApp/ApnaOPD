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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ListOfPrescriptionActivity extends AppCompatActivity {

    private RecyclerView preListView;
    private RecyclerView.LayoutManager layoutManager;
    private RetailerPrescriptionAdaptor mAdaptor;
    private List<PojoUploadPrescription> data;
    private  String url = AppUtils.HOST_ADDRESS+"/api/orders/";
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
                String url=AppUtils.HOST_ADDRESS+"/api/retailers/orders/"+AppUtils.RET_GID+"/"+id;
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


        json_pres_id = getIntent().getStringExtra("id array");



            try {

                String pres_id[]= C.getStringArray(json_pres_id);

                for(int j=0;j<pres_id.length;j++){

                    final String pid = pres_id[j];

                    RequestGet request = new RequestGet(this);
                    request.getJSONObject(url+pid, new RequestGet.JSONObjectResponseListener() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                            try{
                                PojoUploadPrescription uploadPrescription = PojoUploadPrescription.parseFromJson(jsonObject);
                                uploadPrescription.setEprescription_id(pid);
                                uploadPrescription.set_id(pid);
                                data.add(uploadPrescription);
                                mAdaptor.notifyDataSetChanged();

                            }
                            catch (Exception e){
                                Toast.makeText(ListOfPrescriptionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }


            } catch (Exception e1) {
                e1.printStackTrace();
            }




    }

}
