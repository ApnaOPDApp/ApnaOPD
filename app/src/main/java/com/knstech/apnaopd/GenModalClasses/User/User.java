
package com.knstech.apnaopd.GenModalClasses.User;

import org.json.JSONException;
import org.json.JSONObject;

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

    public User parseFromJson(String json)
    {
        try {

            JSONObject object=new JSONObject(json);

            setName(object.getString("name"));
            if(object.has("image_url"))
                setImageUrl(object.getString("image_url"));
            if(object.has("email"))
                setEmail(object.getString("email"));
            if(object.has("gid"))
                setGid(object.getString("gid"));

            List<Address> addresses=Address.parseFromJson(object.getString("address"));
            List<Casesheet> casesheets=Casesheet.parseFromJson(object.getString("casesheet"));
            IsDoctor isDoctor=IsDoctor.parseFromJson(object.getString("is_doctor"));
            IsManufacturer isManufacturer=IsManufacturer.parseFromJson(object.getString("is_manufacturer"));
            IsRetailer isRetailer=IsRetailer.parseFromJson(object.getString("is_retailer"));
            IsSupplier isSupplier=IsSupplier.parseFromJson(object.getString("is_supplier"));
            IsWholesaler isWholesaler=IsWholesaler.parseFromJson(object.getString("is_wholesaler"));

            setAddress(addresses);
            setCasesheet(casesheets);
            setIsDoctor(isDoctor);
            setIsManufacturer(isManufacturer);
            setIsRetailer(isRetailer);
            setIsSupplier(isSupplier);
            setIsWholesaler(isWholesaler);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

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
