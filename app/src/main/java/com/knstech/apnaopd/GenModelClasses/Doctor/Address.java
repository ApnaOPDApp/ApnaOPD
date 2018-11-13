
package com.knstech.apnaopd.GenModelClasses.Doctor;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Address implements Parcelable {



    private String house_no;

    private String full_name;

    private String landmark;

    private String locality;

    private String city;

    private String state;

    private String pincode;

    private String phoneNumber;

    public Address(Parcel source) {
        this.house_no = source.readString();
        this.full_name = source.readString();
        this.landmark = source.readString();
        this.locality = source.readString();
        this.city = source.readString();
        this.state = source.readString();
        this.pincode = source.readString();
        this.phoneNumber = source.readString();
    }

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



    public static List<Address> parseFromJson(String json)
    {
        List<Address> list=new ArrayList<>();
        try {
            JSONArray array=new JSONArray(json);
            for(int i=0;i<array.length();i++)
            {
                Address address=(new Gson()).fromJson(array.getJSONObject(i).toString(),Address.class);
                list.add(address);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.house_no);
        dest.writeString(this.pincode);
        dest.writeString(this.full_name);
        dest.writeString(this.locality);
        dest.writeString(this.landmark);
        dest.writeString(this.state);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.city);
    }
    public Address()
    {

    }
    public static final Parcelable.Creator<Address> CREATOR=new Parcelable.Creator<Address>(){

        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
    @Override
    public String toString(){
        String str=(new Gson()).toJson(this,Address.class).toString();
        return str;
    }
}
