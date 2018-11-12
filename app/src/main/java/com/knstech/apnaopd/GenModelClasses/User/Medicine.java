package com.knstech.apnaopd.GenModelClasses.User;

import com.google.gson.Gson;

public class Medicine {

    private String name;
    private String dper;
    private String dday;
    private String befor_after;
    private String type;

    public static Medicine parseFromJson(String json)
    {
        return ((new Gson()).fromJson(json,Medicine.class));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDper() {
        return dper;
    }

    public void setDper(String dper) {
        this.dper = dper;
    }

    public String getDday() {
        return dday;
    }

    public void setDday(String dday) {
        this.dday = dday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBefor_after() {
        return befor_after;
    }

    public void setBefor_after(String befor_after) {
        this.befor_after = befor_after;
    }
}
