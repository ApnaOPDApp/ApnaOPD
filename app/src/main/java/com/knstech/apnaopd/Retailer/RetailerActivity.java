package com.knstech.apnaopd.Retailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.knstech.apnaopd.DrawerUtil;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class RetailerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);

        ButterKnife.bind(this);
       Toolbar toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Retailer Home");

        DrawerUtil.getDrawer(this,toolbar);
    }
}
