package com.knstech.apnaopd.GenModalClasses.Doctor;

import com.google.gson.Gson;

public class Patient {
    private String Time;
    private String comment;

    public String getPatient_gid() {
        return patient_gid;
    }

    public void setPatient_gid(String patient_gid) {
        this.patient_gid = patient_gid;
    }

    public String getCasesheet_uid() {
        return casesheet_uid;
    }

    public void setCasesheet_uid(String casesheet_uid) {
        this.casesheet_uid = casesheet_uid;
    }

    private String patient_gid;
    private String casesheet_uid;

    public static Patient parseFromJson(String json)
    {
        Gson gson=new Gson();
        return gson.fromJson(json,Patient.class);
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
