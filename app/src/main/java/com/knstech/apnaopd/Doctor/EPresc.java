package com.knstech.apnaopd.Doctor;

import com.knstech.apnaopd.GenModelClasses.User.Medicine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EPresc {
    private List<Medicine> medicineList;
    private String comment;
    private String id;
    private String appointment_id;

    public static EPresc parseFromJson(String json)
    {
        EPresc presc=null;
        try {
            presc=new EPresc();
            JSONObject obj=new JSONObject(json);
            if(obj.has("medicines"))
            {
                List<Medicine> list=new ArrayList<>();
                JSONArray array=obj.getJSONArray("medicines");
                for(int i=0;i<array.length();i++)
                {
                    JSONObject medObj=array.getJSONObject(i);
                    Medicine med=Medicine.parseFromJson(medObj.toString());
                    list.add(med);
                }
                presc.setMedicineList(list);
            }
            if(obj.has("comment"))
            {
                presc.setComment(obj.getString("comment"));
            }
            if(obj.has("_id"))
            {
                presc.setId(obj.getString("_id"));
            }
            if(obj.has("appointment_id"))
            {
                presc.setAppointment_id(obj.getString("appointment_id"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return presc;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }
}
