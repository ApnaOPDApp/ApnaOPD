
package com.knstech.apnaopd.GenModalClasses.User;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Address {


    private String full_name;

    private String house_no;
    private String locality;

    private String city;
    private String landmark;

    private String state;

    private String pincode;

    private String phone_number;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHouse_no() {
        return house_no;
    }
    public static List<Address> parseFromJson(String json)
    {
        List<Address> list=new ArrayList<>();
        try {
            Gson gson=new Gson();
            JSONArray array=new JSONArray(json);
            for(int i=0;i<array.length();i++)
            {
                Address address=gson.fromJson(array.get(i).toString(),Address.class);
                list.add(address);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }


    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
