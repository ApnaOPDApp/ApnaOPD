package com.knstech.apnaopd.GenModalClasses.User;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class PojoUploadPrescription {

    private String patient_gid;
    private String photo_prescription_link;
    private String comment;
    private Address address;
    private String profile_image;
    private String eprescription_id;
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public static PojoUploadPrescription parseFromJson(JSONObject json)
    {
        PojoUploadPrescription pup=new PojoUploadPrescription();

            try {
                if(json.has("address")) {

                    Address address = (new Gson()).fromJson(json.get("address").toString(), Address.class);
                    pup.setAddress(address);
                }
                if(json.has("patient_gid"))
                {
                    pup.setPatient_gid(json.getString("patient_gid"));
                }
                if(json.has("photo_prescription_link"))
                {
                    pup.setPhoto_prescription_link(json.getString("photo_prescription_link"));
                }
                if(json.has("comment"))
                {
                    pup.setComment(json.getString("comment"));
                }
                if(json.has("profile_image"))
                {
                    pup.setProfile_image(json.getString("profile_image"));
                }
                if(json.has("eprescription_id"))
                {
                    pup.setEprescription_id("eprescription_id");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return pup;

    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public PojoUploadPrescription() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPatient_gid() {
        return patient_gid;
    }

    public void setPatient_gid(String patient_gid) {
        this.patient_gid = patient_gid;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhoto_prescription_link() {
        return photo_prescription_link;
    }

    public void setPhoto_prescription_link(String photo_prescription_link) {
        this.photo_prescription_link = photo_prescription_link;
    }

    public String getEprescription_id() {
        return eprescription_id;
    }

    public void setEprescription_id(String eprescription_id) {
        this.eprescription_id = eprescription_id;
    }
}
