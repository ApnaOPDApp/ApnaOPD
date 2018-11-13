package com.knstech.apnaopd.GenModelClasses.User;

import com.google.gson.Gson;

public class Medicine {

    private String medicine_name;
    private String dosage_per;
    private String dosage_day;
    private String _id;
    private String before_after_meal;
    private String types;

    public static Medicine parseFromJson(String json)
    {
        return ((new Gson()).fromJson(json,Medicine.class));
    }

    public String getType() {
        return types;
    }

    public void setType(String type) {
        this.types = type;
    }


    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDosage_per() {
        return dosage_per;
    }

    public void setDosage_per(String dosage_per) {
        this.dosage_per = dosage_per;
    }

    public String getDosage_day() {
        return dosage_day;
    }

    public void setDosage_day(String dosage_day) {
        this.dosage_day = dosage_day;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBefor_after_meal() {
        return before_after_meal;
    }

    public void setBefor_after_meal(String befor_after_meal) {
        this.before_after_meal = befor_after_meal;
    }
}
