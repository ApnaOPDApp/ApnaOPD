
package com.knstech.apnaopd.GenModalClasses.Doctor;

import java.util.List;


public class Doctor {

    private String name;

    private String email;

    private String gid;

    private String phoneNumber;

    private String officeNumber;

    private Address address;

    private String degree;

    private String speciality;

    private String fee;

    private String pin;

    private String regNumber;

    private String certiLink;

    private String department;

    private List<Visiting> visiting = null;

    private List<History> history = null;

    private List<TimeSlab> timeSlab = null;

    public String getName() {
        return name;
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

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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

    public List<Visiting> getVisiting() {
        return visiting;
    }

    public void setVisiting(List<Visiting> visiting) {
        this.visiting = visiting;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<TimeSlab> getTimeSlab() {
        return timeSlab;
    }

    public void setTimeSlab(List<TimeSlab> timeSlab) {
        this.timeSlab = timeSlab;
    }

}
