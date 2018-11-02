
package com.knstech.apnaopd.GenModalClasses.Doctor;


import com.google.gson.Gson;

public class Address {


    private String houseLane;

    private String locality;

    private String city;

    private String district;

    private String state;

    private String pincode;

    private String phoneNumber;

    public static Address parseFromString(String json)
    {
        Gson gson=new Gson();
        return gson.fromJson(json,Address.class);
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
