
package com.knstech.apnaopd.GenModalClasses.User;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Address {

    private String houseLane;
    private String locality;

    private String city;

    private String district;

    private String state;

    private String pincode;

    private String phoneNumber;

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

    public String getHouseLane() {
        return houseLane;
    }

    public void setHouseLane(String houseLane) {
        this.houseLane = houseLane;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
