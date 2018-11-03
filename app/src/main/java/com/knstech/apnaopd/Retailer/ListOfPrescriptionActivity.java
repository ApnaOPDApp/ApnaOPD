package com.knstech.apnaopd.Retailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

import com.google.gson.Gson;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.Address;
import com.knstech.apnaopd.GenModalClasses.User.PojoUploadPrescription;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Volley.NetworkJSONLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ListOfPrescriptionActivity extends AppCompatActivity {

    private RecyclerView retailer_prescription_list;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdaptor;
    private List<PojoUploadPrescription> data;
    String url= AppUtils.HOST_ADDRESS+"/api/presc_list/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prescription);

        retailer_prescription_list = (RecyclerView) findViewById(R.id.retailer_list_prescription);
        retailer_prescription_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        retailer_prescription_list.setLayoutManager(layoutManager);
        mAdaptor = new RetailerPrescriptionAdaptor(getApplicationContext(),data);
        retailer_prescription_list.setAdapter(mAdaptor);
        populateView();

    }

    private void populateView() {

        RequestGet requestGet=new RequestGet(this);
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject obj=jsonArray.getJSONObject(i);
                        PojoUploadPrescription pup=new PojoUploadPrescription();
                        data.add(pup);
                        pup.setPatient_gid(obj.getString("patient_gid"));
                        pup.setPrescription_link(obj.getString("prescription_link"));
                        pup.setComment(obj.getString("comment"));
                        Gson gson=new Gson();
                        Address address=gson.fromJson(obj.toString(),Address.class);
                        pup.setAddress(address);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdaptor.notifyDataSetChanged();
            }
        });

    }
}
