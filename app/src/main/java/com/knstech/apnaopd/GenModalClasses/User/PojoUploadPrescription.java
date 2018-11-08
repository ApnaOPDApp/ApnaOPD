package com.knstech.apnaopd.GenModalClasses.User;

import org.json.JSONArray;

import java.io.Serializable;

public class PojoUploadPrescription {

    private String patient_gid;
    private String photo_prescription_link;
    private String comment;
    private Address address;
    private String profile_image;
    private String Prescription_id;

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


    public String getPrescription_id() {
        return Prescription_id;
    }

    public void setPrescription_id(String prescription_id) {
        Prescription_id = prescription_id;
    }

    public String getPhoto_prescription_link() {
        return photo_prescription_link;
    }

    public void setPhoto_prescription_link(String photo_prescription_link) {
        this.photo_prescription_link = photo_prescription_link;
    }
}
