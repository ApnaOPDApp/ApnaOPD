package com.knstech.apnaopd.Patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.knstech.apnaopd.GenModelClasses.User.Department;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Patient.ADAPTORS.DepartmentAdapter;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.C;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Listeners.DepatmentClickListener;
import com.knstech.apnaopd.Utils.MyConnectionTester;
import com.knstech.apnaopd.Utils.UIUpdater;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentActivity extends AppCompatActivity {
    public static final int IMG_REQUEST =2 ;
    private int choice;
    private View selectedCS;
    private RelativeLayout rootLayout;
    public static int state=1;
    private String[] deptAr;
    private View deptView;
    private String feeStr;
    private RecyclerView deptList;
    private LinearLayoutManager linearLayoutManager;
    private List<Department> mList;
    private DepartmentAdapter adapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        toolbar = (Toolbar)findViewById(R.id.p_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Select Department");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        final MyConnectionTester connnection = new MyConnectionTester();

        if(!connnection.isConnected(DoctorAppointmentActivity.this)){
            connnection.buildDialog(DoctorAppointmentActivity.this).show();
        }



        rootLayout=findViewById(R.id.rootLayout);

        deptView=LayoutInflater.from(this).inflate(R.layout.dept_select_layout,null);
        rootLayout.addView(deptView);
        getArrayFromServer();


        deptList=findViewById(R.id.deptList);
        populateRecyclerView();



    }

    private void populateRecyclerView() {

        mList=new ArrayList<>();
        adapter=new DepartmentAdapter(mList, getApplicationContext(), new DepatmentClickListener() {
            @Override
            public void onClick(Department department) {
                toggle();
                choice=Integer.parseInt(department.getId());
            }
        });
        linearLayoutManager=new LinearLayoutManager(this);

        deptList.setAdapter(adapter);
        deptList.setHasFixedSize(true);
        deptList.setLayoutManager(linearLayoutManager);


    }

    public String getFeeStr()
    {
        return feeStr;
    }
    private void toggle() {
        if(state==1)
        {
            state=2;
            getSupportActionBar().setTitle("Fill Details");
            rootLayout.removeAllViews();
            updateUI();
        }
        else
        {
            state=1;
            getSupportActionBar().setTitle("Departments");
            rootLayout.removeAllViews();
            rootLayout.addView(deptView);
        }

    }

    @Override
    public void onBackPressed() {
        if(state==2) {
            toggle();
        }
        else {
            finish();
        }
    }


    private void updateUI() {
        String csGenUrl= AppUtils.HOST_ADDRESS+"/api/casesheets";
        RequestGet requestGet=new RequestGet(this);
        requestGet.getJSONObject(csGenUrl, new RequestGet.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject object) {
                switch (choice)
                {
                    case C.CARDIO:
                        UIUpdater.updateCardio(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.EAR:
                        UIUpdater.updateEar(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.EYE:
                        UIUpdater.updateEye(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.GENITO:
                        UIUpdater.updateGenito(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    case C.NEURO:
                        UIUpdater.updateNeuro(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);
                        break;
                    default:
                        UIUpdater.updateGeneral(DoctorAppointmentActivity.this,rootLayout,selectedCS,object);

                }
            }
        });



    }


    public void getArrayFromServer() {

        RequestGet requestGet=new RequestGet(getApplicationContext());
        String url=AppUtils.HOST_ADDRESS+"/api/casesheets/departments";
        requestGet.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray){

                deptList.setBackground(null);



                 for(int i=0;i<jsonArray.length();i++)
                 {
                     JSONObject obj= null;
                     try {

                         obj = jsonArray.getJSONObject(i);
                         Department dept=new Department();
                         dept.setId(obj.getString("id"));
                         dept.setName(obj.getString("name"));
                         dept.setDescription(obj.getString("desc"));
                         dept.setImage_link(obj.getString("image_link"));
                         mList.add(dept);
                         adapter.notifyDataSetChanged();
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }

            }
        });

    }

    private String path;
    private String upload_url=AppUtils.HOST_ADDRESS+"/api/uploadReport";
    private String response_url;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==UIUpdater.IMG)
        {
            if(resultCode==RESULT_OK)
            {

            }
        }


        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data!=null) {

            Uri uri = data.getData();

            try {
                final ProgressDialog mProgress=new ProgressDialog(DoctorAppointmentActivity.this);
                mProgress.setTitle("Uploading");
                mProgress.setMessage("Please wait while the picture is being uploaded");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();
                path= (String) getPathFromURI(data.getData());
                File file = new File(path);
                Future uploading = Ion.with(DoctorAppointmentActivity.this)
                        .load(upload_url)
                        .setMultipartFile("report",file)
                        .setMultipartParameter("gid", UserAuth.getmUser(DoctorAppointmentActivity.this).getGid())
                        .asString()
                        .withResponse()
                        .setCallback(new FutureCallback<Response<String>>() {
                            @Override
                            public void onCompleted(Exception e, com.koushikdutta.ion.Response<String> result) {

                                try {
                                    mProgress.dismiss();
                                    response_url = result.getResult();
                                    UIUpdater.setUrl(response_url);

                                } catch (Exception e1) {
                                    mProgress.dismiss();
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

    public String getDepartment() {

        return ""+mList.get(choice).getName();
    }



    public int getChoice() {
        return choice;
    }
}
