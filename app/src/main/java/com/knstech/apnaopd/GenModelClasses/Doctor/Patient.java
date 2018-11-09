package com.knstech.apnaopd.GenModelClasses.Doctor;

import org.json.JSONException;
import org.json.JSONObject;

public class Patient {
    private String time_slab;

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
    private String patient_name;
    private String patient_age;
    private String doctor_gid;
    private String casesheet_uid;
    private String doctor_name;
    private String doctor_image;
    private String department;
    private String fee;
    private String status;
    private String appointment_id;

    public static Patient parseFromJson(String json)
    {
        try {
            Patient patient=new Patient();
            JSONObject obj=new JSONObject(json);
            if(obj.has("patient_age"))
            {
                patient.setPatient_age(obj.getString("patient_age"));
            }
            if(obj.has("casesheet_uid"))
            {
                patient.setCasesheet_uid(obj.getString("casesheet_uid"));
            }
            if(obj.has("_id"))
            {
                patient.setAppointment_id(obj.getString("_id"));
            }
            if(obj.has("status"))
            {
                patient.setStatus(obj.getString("status"));
            }
            if(obj.has("doctor_name"))
            {
                patient.setDoctor_name(obj.getString("doctor_name"));
            }
            if(obj.has("patient_name"))
            {
                patient.setPatient_name(obj.getString("patient_name"));
            }
            if(obj.has("patient_gid"))
            {
                patient.setPatient_gid(obj.getString("patient_gid"));
            }
            if(obj.has("doctor_gid"))
            {
                patient.setDoctor_gid(obj.getString("doctor_gid"));
            }
            if(obj.has("fee"))
            {
                patient.setFee(obj.getString("fee"));
            }
            if(obj.has("department"))
            {
                patient.setDepartment(obj.getString("department"));
            }
            if(obj.has("doctor_image"))
            {
                patient.setDoctor_image(obj.getString("doctor_image"));
            }
            return patient;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getDoctor_gid() {
        return doctor_gid;
    }

    public void setDoctor_gid(String doctor_gid) {
        this.doctor_gid = doctor_gid;
    }

    public String getTime_slab() {
        return time_slab;
    }

    public void setTime_slab(String time_slab) {
        this.time_slab = time_slab;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_image() {
        return doctor_image;
    }

    public void setDoctor_image(String doctor_image) {
        this.doctor_image = doctor_image;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }
}
