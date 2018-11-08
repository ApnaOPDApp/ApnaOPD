package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.knstech.apnaopd.R;

public class ListOfQuotationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_quotations);

        Toolbar toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Quotations List");

    }
}
