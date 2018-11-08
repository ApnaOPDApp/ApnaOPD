package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.knstech.apnaopd.BecomeDoctorActivity;
import com.knstech.apnaopd.DrawerUtil;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView medicine;
    private CardView appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("ApnaOpd Home");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        DrawerUtil.getDrawer(this,toolbar);

        medicine =(CardView)findViewById(R.id.cv_3);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MedicineActivity.class));
            }
        });
        appointment=findViewById(R.id.c1);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,DoctorAppointmentActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.patient,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.signout) {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.server_client_id))
                    .requestEmail()
                    .build();

            //init signIn client
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut();

            startActivity(new Intent(HomeActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            finish();

            return true;
        }
        else if(R.id.becomeDoc==id)
        {
            Intent i=new Intent(HomeActivity.this, BecomeDoctorActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
