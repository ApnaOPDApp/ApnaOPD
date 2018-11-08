
package com.knstech.apnaopd.GenModalClasses.Doctor;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class Doctor {

    private String name;

    private String email;

    private String gid;

    private String phoneNumber;


    private Address address;

    private String degree;


    private String fee;


    private String regNumber;

    private String certiLink;

    private String department;

    private String[] visiting = null;

    private String[] history = null;

    private List<TimeSlab> timeSlab = null;

    public String getName() {
        return name;
    }

    public boolean equals(Doctor c)
    {
        if(this.gid != c.gid)
        {
            return false;
        }
        return true;
    }

    public Doctor parseFromJson(String json)
    {
        try {
            JSONObject object=new JSONObject(json);
            setName(object.getString("name"));
            if(object.has("degree"))
                setDegree(object.getString("degree"));
            if(object.has("certi_link"))
                setCertiLink(object.getString("certi_link"));
            if(object.has("department"))
                setDepartment(object.getString("department"));
            if(object.has("fee"))
                setFee(object.getString("fee"));
            if(object.has("reg_number"))
                setRegNumber(object.getString("reg_number"));
            if(object.has("phone_number"))
                setPhoneNumber(object.getString("phone_number"));
            if(object.has("email"))
                setEmail(object.getString("email"));
            if(object.has("gid"))
                setGid(object.getString("gid"));
            if(object.has("time_slab"))
                timeSlab=TimeSlab.parsefromJson(object.getString("time_slab"));
            if(object.has("history"))
            {
                history=(new Gson()).fromJson(object.getJSONArray("history").toString(),String[].class);
            }
            if(object.has("visiting"))
            {
                visiting=(new Gson()).fromJson(object.getJSONArray("visiting").toString(),String[].class);
            }
            Address address=new Address();
            if(object.has("office_number"))
                address=address.parseFromString(object.getString("address"));

            setAddress(address);


            return this;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }



    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }


    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getCertiLink() {
        return certiLink;
    }

    public void setCertiLink(String certiLink) {
        this.certiLink = certiLink;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public List<TimeSlab> getTimeSlab() {
        return timeSlab;
    }

    public void setTimeSlab(List<TimeSlab> timeSlab) {
        this.timeSlab = timeSlab;
    }

    public String[] getVisiting() {
        return visiting;
    }

    public void setVisiting(String[] visiting) {
        this.visiting = visiting;
    }

    public String[] getHistory() {
        return history;
    }

    public void setHistory(String[] history) {
        this.history = history;
    }
}
