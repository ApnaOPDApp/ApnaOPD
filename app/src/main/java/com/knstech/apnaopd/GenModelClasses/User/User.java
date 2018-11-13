
package com.knstech.apnaopd.GenModelClasses.User;

import org.json.JSONException;
import org.json.JSONObject;

public class User {


    private String name;

    private String email;

    private String imageUrl;

    private String gid;

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


            if(object.has("is_doctor")) {
                IsDoctor isDoctor = IsDoctor.parseFromJson(object.getString("is_doctor"));
                setIsDoctor(isDoctor);
            }
            if(object.has("is_manufacturer")) {
                IsManufacturer isManufacturer = IsManufacturer.parseFromJson(object.getString("is_manufacturer"));
                setIsManufacturer(isManufacturer);
            }
            if(object.has("is_retailer")) {
                IsRetailer isRetailer = IsRetailer.parseFromJson(object.getString("is_retailer"));
                setIsRetailer(isRetailer);
            }
            if(object.has("is_supplier") ){
                IsSupplier isSupplier = IsSupplier.parseFromJson(object.getString("is_supplier"));
                setIsSupplier(isSupplier);
            }
            if(object.has("is_wholesaler"))
            {
                IsWholesaler isWholesaler=IsWholesaler.parseFromJson(object.getString("is_wholesaler"));
                setIsWholesaler(isWholesaler);
            }










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
