package com.knstech.apnaopd.Profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.knstech.apnaopd.GenModelClasses.User.User;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.R;

public class ProfileActivity extends AppCompatActivity {

    TextView name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);



        User user=UserAuth.getmUser(this);

        name.setText(user.getName());
        email.setText(user.getEmail());


    }
}
