package com.knstech.apnaopd.Retailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.knstech.apnaopd.R;

public class ListOfPrescriptionActivity extends AppCompatActivity {

    private RecyclerView retailer_prescription_list;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prescription);

        retailer_prescription_list = (RecyclerView) findViewById(R.id.retailer_list_prescription);


    }
}
