package com.knstech.apnaopd.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.knstech.apnaopd.AddNewProfileActivity;
import com.knstech.apnaopd.DrawersUtil.DrawerUtil;
import com.knstech.apnaopd.R;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;            //  Varibale for toolbar
    private RelativeLayout medicine;    //  Java object of card Order Medicine
    private RelativeLayout appointment; //  Java Object of card Book Appointmnent
    private TextView desc2;             //  Java object of description text in card Order medicine
    private TextView desc1;
    private RelativeLayout appointmentViewer;//  Java object of descriptionof text in card book appointment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);     // Using butter knife to bind views

       /*  Intregating toolbar into the Activity */

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        toolbar.setTitle("APNAOPD Home");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        /* Intregating Drawer in the Home Activity  */

        DrawerUtil.getDrawer(this,toolbar);

        // Linking XML Layout with java objects

        desc1 = findViewById(R.id.desc_1);
        desc2 = findViewById(R.id.desc_2);
        medicine =(RelativeLayout)findViewById(R.id.cv2);
        appointment=(RelativeLayout) findViewById(R.id.cv1);
        appointmentViewer=findViewById(R.id.cv3);


        // --------------- Click Listener on Card Medicine Listener ---------------------

        desc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* redirecting to Medicine Activity  */

                startActivity(new Intent(HomeActivity.this,MedicineActivity.class));
            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* redirecting to Medicine Activity  */

                startActivity(new Intent(HomeActivity.this,MedicineActivity.class));
            }
        });

        appointmentViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,PAppointmentViewerActivity.class));
            }
        });

        //------------------- Click Listener on Card Book Appointment ---------------------------

        desc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* redirecting to Doctors Appointment Activity */

                startActivity(new Intent(HomeActivity.this,DoctorAppointmentActivity.class));
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* redirecting to Doctors Appointment Activity */

                startActivity(new Intent(HomeActivity.this,DoctorAppointmentActivity.class));
            }
        });
    }

    //------------------------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.patient,menu);
        return true;
    }

    /* Inflating signout button in menu of home activity  */
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
        if(id == R.id.addNew)
        {
            startActivity(new Intent(HomeActivity.this, AddNewProfileActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
