package com.knstech.apnaopd.GenModalClasses.Retailer;

public class Offer {
    public String getPatient_gid() {
        return patient_gid;
    }

    public void setPatient_gid(String patient_gid) {
        this.patient_gid = patient_gid;
    }

    public String getRetailer_gid() {
        return retailer_gid;
    }

    public void setRetailer_gid(String retailer_gid) {
        this.retailer_gid = retailer_gid;
    }

    public String getEoffer() {
        return eoffer;
    }

    public void setEoffer(String eoffer) {
        this.eoffer = eoffer;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        this.delivery_charge = delivery_charge;
    }
    public String getDosage_day() {
        return dosage_day;
    }

    public void setDosage_day(String dosage_day) {
        this.dosage_day = dosage_day;
    }

    public String getDosage_per() {
        return dosage_per;
    }

    public void setDosage_per(String dosage_per) {
        this.dosage_per = dosage_per;
    }

    private String patient_gid;
    private String retailer_gid;
    private String eoffer;
    private String address_id;
    private String delivery_time;
    private String delivery_charge;
    private String dosage_day;
    private String dosage_per;
}
