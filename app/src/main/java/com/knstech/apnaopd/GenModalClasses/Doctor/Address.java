
package com.knstech.apnaopd.GenModalClasses.Doctor;


import com.google.gson.Gson;

public class Address {



    private String house_no;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    private String full_name;

    private String landmark;

    private String locality;

    private String city;

    private String state;

    private String pincode;

    private String phoneNumber;

    public static Address parseFromString(String json)
    {
        Gson gson=new Gson();
        return gson.fromJson(json,Address.class);
    }

    public String getHouseLane() {
        return house_no;
    }

    public void setHouseLane(String houseLane) {
        this.house_no = houseLane;
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
