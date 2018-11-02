
package com.knstech.apnaopd.GenModalClasses.Doctor;


import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Visiting {


    private String patientGid;

    private String casesheetUid;
    
    public static List<Visiting> parsefromJson(String json)
    {
        List<Visiting> list=new ArrayList<>();
        try {
            Gson gson=new Gson();
            JSONArray array=new JSONArray(json);
            for(int i=0;i<array.length();i++)
            {
                Visiting visiting=gson.fromJson(array.get(i).toString(),Visiting.class);
                list.add(visiting);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String getPatientGid() {
        return patientGid;
    }

    public void setPatientGid(String patientGid) {
        this.patientGid = patientGid;
    }

    public String getCasesheetUid() {
        return casesheetUid;
    }

    public void setCasesheetUid(String casesheetUid) {
        this.casesheetUid = casesheetUid;
    }

}
