
package com.knstech.apnaopd.GenModalClasses.Doctor;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TimeSlab {


    private String available;

    private String start;

    private String end;

    private String timePer;

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTimePer() {
        return timePer;
    }

    public void setTimePer(String timePer) {
        this.timePer = timePer;
    }

}
