package com.knstech.apnaopd.GenModalClasses.User;

import org.json.JSONArray;

public class PojoUploadPrescription {

    private String patient_gid;
    private String prescription_link;
    private String comment;
    private Address address;

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

    public String getPrescription_link() {
        return prescription_link;
    }

    public void setPrescription_link(String prescription_link) {
        this.prescription_link = prescription_link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
