
package com.knstech.apnaopd.GenModalClasses.Doctor;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TimeSlab {


    private String available;

    private String sl;

    private String Persons;

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


    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getPersons() {
        return Persons;
    }

    public void setPersons(String persons) {
        Persons = persons;
    }
}
