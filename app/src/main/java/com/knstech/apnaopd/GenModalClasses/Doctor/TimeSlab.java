
package com.knstech.apnaopd.GenModalClasses.Doctor;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TimeSlab {


    private String available;

    public String getSl_no() {
        return sl_no;
    }

    public void setSl_no(String sl_no) {
        this.sl_no = sl_no;
    }

    public String getPatients_per() {
        return patients_per;
    }

    public void setPatients_per(String patients_per) {
        this.patients_per = patients_per;
    }

    private String sl_no;

    private String patients_per;

    public static List<TimeSlab> parsefromJson(String json) {
        List<TimeSlab> list = new ArrayList<>();
        try {
                Gson gson = new Gson();
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    TimeSlab timeSlab = gson.fromJson(array.get(i).toString(), TimeSlab.class);
                    list.add(timeSlab);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }



}
