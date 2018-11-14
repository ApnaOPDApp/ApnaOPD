package com.knstech.apnaopd;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Patient.MedicineActivity;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestPost;
import com.knstech.apnaopd.Utils.Connections.RequestPut;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AddNewProfileActivity extends AppCompatActivity {

    private static final int IMG_REQUEST = 1;
    private Toolbar toolbar;
    private EditText become_name,become_regNo,become_email,dept;
    private RadioButton bDoctor,bRetailer,bPathologist;
    private Button submit,uploadDoc;


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
        bPathologist=findViewById(R.id.bPathologist);

        submit=findViewById(R.id.submit);
        uploadDoc = findViewById(R.id.upload_documents);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String become_nameStr,become_emailStr,become_regNoStr,deptStr;
                String bType;

                become_nameStr=become_name.getText().toString();
                become_emailStr=become_email.getText().toString();
                become_regNoStr=become_regNo.getText().toString();
                bType=(bDoctor.isChecked())?"Doctor":((bRetailer.isChecked())?"Retailer":(bPathologist.isChecked())?"Pathologist":"");
                if(!bType.equals(""))
                {
                    Map map=new HashMap();
                    map.put("name",become_nameStr);
                    map.put("gid",UserAuth.getmUser(AddNewProfileActivity.this).getGid());
                    map.put("email",become_emailStr);
                    map.put("becomeDoctor",(bType.equals("Doctor"))?true:false);
                    map.put("becomeRetailer",(bType.equals("Retailer"))?true:false);
                    map.put("becomePathologist",(bType.equals("Pathologist"))?true:false);
                    map.put("verifyId",become_regNoStr);
                    map.put("verifyCerti",response);

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


        uploadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*,application/*");
                intent.setAction(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,IMG_REQUEST);

            }
        });
    }

    private String response;
    private String doc_url = AppUtils.HOST_ADDRESS+"/api/uploadCerti";
    private String path;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null) {

            Uri uri = data.getData();

            try {
                path= (String) getPathFromURI(data.getData());
                File file = new File(path);
                Future uploading = Ion.with(AddNewProfileActivity.this)
                        .load(doc_url)
                        .setMultipartFile("certificate",file)
                        .setMultipartParameter("gid",UserAuth.getmUser(AddNewProfileActivity.this).getGid())
                        .asString()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<String>>() {
                            @Override
                            public void onCompleted(Exception e, com.koushikdutta.ion.Response<String> result) {

                                try {
                                    response = result.getResult();

                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                            }
                        });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }
    private String getPathFromURI(Uri contentUri){

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),contentUri,proj,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int coloumn_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(coloumn_index);
        return cursor.getString(coloumn_index);
    }
}
