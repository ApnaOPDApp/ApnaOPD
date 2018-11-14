package com.knstech.apnaopd.Pathologist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.knstech.apnaopd.DrawersUtil.DrawerUtil;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class PathologistActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathologist);
        ButterKnife.bind(this);     // Using butter knife to bind views
        overridePendingTransition(0,0);

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("Pathologist Home");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        DrawerUtil.getDrawer(this,toolbar);




        c1 = (RelativeLayout)findViewById(R.id.pat_cv1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PathologistActivity.this, "This function is under development. Please check for new update", Toast.LENGTH_LONG).show();
            }
        });
    }
}
