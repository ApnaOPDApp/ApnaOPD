
package com.knstech.apnaopd.GenModalClasses.User;

import java.util.List;

public class User {


    private String name;

    private String email;

    private String imageUrl;

    private String gid;

    private List<Address> address = null;

    private List<Casesheet> casesheet = null;

    private IsDoctor isDoctor;

    private IsRetailer isRetailer;

    private IsWholesaler isWholesaler;

    private IsManufacturer isManufacturer;

    private IsSupplier isSupplier;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Casesheet> getCasesheet() {
        return casesheet;
    }

    public void setCasesheet(List<Casesheet> casesheet) {
        this.casesheet = casesheet;
    }

    public IsDoctor getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(IsDoctor isDoctor) {
        this.isDoctor = isDoctor;
    }

    public IsRetailer getIsRetailer() {
        return isRetailer;
    }

    public void setIsRetailer(IsRetailer isRetailer) {
        this.isRetailer = isRetailer;
    }

    public IsWholesaler getIsWholesaler() {
        return isWholesaler;
    }

    public void setIsWholesaler(IsWholesaler isWholesaler) {
        this.isWholesaler = isWholesaler;
    }

    public IsManufacturer getIsManufacturer() {
        return isManufacturer;
    }

    public void setIsManufacturer(IsManufacturer isManufacturer) {
        this.isManufacturer = isManufacturer;
    }

    public IsSupplier getIsSupplier() {
        return isSupplier;
    }

    public void setIsSupplier(IsSupplier isSupplier) {
        this.isSupplier = isSupplier;
    }

}
