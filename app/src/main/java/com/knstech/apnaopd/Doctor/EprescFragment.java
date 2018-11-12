package com.knstech.apnaopd.Doctor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.GenModelClasses.User.Medicine;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EprescFragment extends DialogFragment{
    private View view;
    private LinearLayout linearLayout;
    private EditText comment;
    private Button addBtn;
    private String[] ar;
    private String patient_gid,doctor_gid;
    private String casesheet_uid,doctor_name;
    private String appointment_id;
    private Patient removeView;
    private List<Patient> mList;
    private AppointmentViewerAdapter adapter;

    public EprescFragment()
    {

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        view= LayoutInflater.from(getActivity()).inflate(R.layout.epresc_layout,null);
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());

        linearLayout=view.findViewById(R.id.medicineLinLayout);
        addBtn=view.findViewById(R.id.addBtn);
        RequestGet get=new RequestGet(getContext());
        String url= AppUtils.HOST_ADDRESS+"/api/medicines/";
        get.getJSONArray(url, new RequestGet.JSONArrayResponseListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                ar=new String[jsonArray.length()];
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {

                        JSONObject obj=jsonArray.getJSONObject(i);
                        ar[i]=obj.getString("brand_name");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAddButtonFunc();
                    }
                });
            }
        });

        dialog.setView(view);
        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Medicine> list=new ArrayList<>();
                for(int i=0;i<linearLayout.getChildCount();i++)
                {
                    View view=linearLayout.getChildAt(i);
                    AutoCompleteTextView edt=view.findViewById(R.id.medicine);
                    EditText dper,dday;
                    RadioButton before,after;
                    before=view.findViewById(R.id.before);
                    after=view.findViewById(R.id.after);
                    dper=view.findViewById(R.id.dper);
                    EditText type=view.findViewById(R.id.type);
                    String before_after_str=(after.isChecked())?"1":"0";
                    dday=view.findViewById(R.id.dday);
                    if(!TextUtils.isEmpty(edt.getText())) {
                        Medicine med=new Medicine();
                        med.setName(edt.getText().toString());
                        med.setDday(dday.getText().toString());
                        med.setDper(dper.getText().toString());
                        med.setType(type.getText().toString());
                        med.setBefor_after(before_after_str);
                        list.add(med);
                    }


                }
                EditText comment=view.findViewById(R.id.comment);
                String commentStr=comment.getText().toString();
                String url=AppUtils.HOST_ADDRESS+"/api/appointments/status/doctor/"+appointment_id+"/"+doctor_gid;
                Map map=new HashMap();
                List<Map> array=new ArrayList<>();
                for(int i=0;i<list.size();i++)
                {
                    Map medMap=new HashMap();
                    medMap.put("medicine_name",list.get(i).getName());
                    medMap.put("dosage_day",list.get(i).getDday());
                    medMap.put("dosage_per",list.get(i).getDper());
                    medMap.put("type",list.get(i).getType());
                    medMap.put("before_after_meal",list.get(i).getBefor_after());
                    array.add(medMap);
                }
                map.put("medicines",array);
                map.put("comment",commentStr);
                map.put("patient_gid",patient_gid);
                map.put("casesheet_uid",casesheet_uid);
                map.put("doctor_gid",doctor_gid);
                map.put("doctor_name",doctor_name);
                map.put("appointment_id",appointment_id);
                JSONObject obj=new JSONObject(map);
                RequestPut put=new RequestPut(getActivity());
                put.putJSONObject(url, obj, new RequestPut.JSONObjectResponseListener() {
                    @Override
                    public void onResponse(JSONObject object) {
                        mList.remove(removeView);
                        adapter.notifyDataSetChanged();
                    }
                });


            }
        });

        return dialog.create();
    }

    private void addLayout() {

        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.medicine_item_layout,null);
        Button minus=view.findViewById(R.id.minus);
        RadioButton after=view.findViewById(R.id.after);
        after.setChecked(true);
        AutoCompleteTextView edt=view.findViewById(R.id.medicine);

        ArrayAdapter adapter=new ArrayAdapter(getActivity(),R.layout.simple_text,ar);
        edt.setAdapter(adapter);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(view);
            }
        });
        linearLayout.addView(view);
    }
    void setAddButtonFunc()
    {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout();
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public String getPatient_gid() {
        return patient_gid;
    }

    public void setPatient_gid(String patient_gid) {
        this.patient_gid = patient_gid;
    }

    public String getDoctor_gid() {
        return doctor_gid;
    }

    public void setDoctor_gid(String doctor_gid) {
        this.doctor_gid = doctor_gid;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setCasesheet_uid(String casesheet_uid) {
        this.casesheet_uid = casesheet_uid;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setRemoveView(Patient removeView) {
        this.removeView = removeView;
    }

    public void setAdapter(AppointmentViewerAdapter adapter) {
        this.adapter = adapter;
    }

    public void setmList(List<Patient> mList) {
        this.mList = mList;
    }
}
