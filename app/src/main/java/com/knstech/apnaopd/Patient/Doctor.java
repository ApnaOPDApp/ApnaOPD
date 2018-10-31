package com.knstech.apnaopd.Patient;

public class Doctor {
    public Doctor()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Doctor(String name, String fee, String speciality, String registrationNo,String availability) {
        this.name = name;
        this.fee = fee;
        this.speciality = speciality;
        this.registrationNo = registrationNo;
        this.availability = availability;
    }

    private String name;
    private String fee;
    private String speciality;
    private String registrationNo;

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    private String availability;

}
