package com.knstech.apnaopd.GenModalClasses.Doctor;

import com.google.gson.Gson;

class Patient {
    private String Time;
    private String Patient_gid;
    private String Casesheet_uid;

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

    public String getPatient_gid() {
        return Patient_gid;
    }

    public void setPatient_gid(String patient_gid) {
        Patient_gid = patient_gid;
    }

    public String getCasesheet_uid() {
        return Casesheet_uid;
    }

    public void setCasesheet_uid(String casesheet_uid) {
        Casesheet_uid = casesheet_uid;
    }
}
