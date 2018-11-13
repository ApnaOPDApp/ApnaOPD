package com.knstech.apnaopd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestPost;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNewProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText become_name,become_regNo,become_email,dept;
    private RadioButton bDoctor,bRetailer;
    private Button submit;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_profile);

        toolbar = findViewById(R.id.p_toolbar);
        toolbar.setTitle("Add new profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        become_email=findViewById(R.id.become_email);
        become_name=findViewById(R.id.become_name);
        become_regNo=findViewById(R.id.become_regNo);


        bDoctor=findViewById(R.id.bDoctor);
        bRetailer=findViewById(R.id.bRetailer);

        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String become_nameStr,become_emailStr,become_regNoStr,deptStr;
                String bType;

                become_nameStr=become_name.getText().toString();
                become_emailStr=become_email.getText().toString();
                become_regNoStr=become_regNo.getText().toString();
                bType=(bDoctor.isChecked())?"Doctor":(bRetailer.isChecked())?"Retailer":"";
                if(!bType.equals(""))
                {
                    Map map=new HashMap();
                    map.put("name",become_nameStr);
                    map.put("gid",UserAuth.getmUser(AddNewProfileActivity.this).getGid());
                    map.put("email",become_emailStr);
                    map.put("becomeDoctor",(bType.equals("Doctor"))?true:false);
                    map.put("becomeRetailer",(bType.equals("Retailer"))?true:false);
                    map.put("verifyId",become_regNoStr);

                    //map.put("verifyCerti",);
                    JSONObject obj=new JSONObject(map);
                    String url=AppUtils.HOST_ADDRESS+"/api/becomes/" ;
                    RequestPost put=new RequestPost(AddNewProfileActivity.this);
                    put.postJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                        @Override
                        public void onResponse(JSONObject object) {
                            Toast.makeText(AddNewProfileActivity.this, "Your response has been submitted. Please wait for 24 hours for confirmation from admin.", Toast.LENGTH_LONG).show();

                            finish();
                        }
                    });
                }
            }
        });


    }
}
