
package com.knstech.apnaopd.GenModelClasses.User;

import org.json.JSONException;
import org.json.JSONObject;

public class User {


    private String name;

    private String email;

    private String imageUrl;

    private String gid;

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public boolean isRetailer() {
        return isRetailer;
    }

    public void setRetailer(boolean retailer) {
        isRetailer = retailer;
    }

    public boolean isWholesaler() {
        return isWholesaler;
    }

    public void setWholesaler(boolean wholesaler) {
        isWholesaler = wholesaler;
    }

    public boolean isManufacturer() {
        return isManufacturer;
    }

    public void setManufacturer(boolean manufacturer) {
        isManufacturer = manufacturer;
    }

    public boolean isSupplier() {
        return isSupplier;
    }

    public void setSupplier(boolean supplier) {
        isSupplier = supplier;
    }

    private boolean isDoctor;

    private boolean isRetailer;

    private boolean isWholesaler;

    private boolean isManufacturer;

    private boolean isSupplier;

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


            if(object.has("isDoctor")) {
                boolean isDoctor = Boolean.parseBoolean(object.getString("isDoctor"));
                setDoctor(isDoctor);
            }
            if(object.has("isManufacturer")) {
                boolean isManufacturer = Boolean.parseBoolean(object.getString("isManufacturer"));
                setManufacturer(isManufacturer);
            }
            if(object.has("isRetaiIsWholesaler.parseFromJson(ler")) {
                boolean isRetailer = Boolean.parseBoolean(object.getString("isRetailer"));
                setRetailer(isRetailer);
            }
            if(object.has("isSupplier") ){
                boolean isSupplier = Boolean.parseBoolean(object.getString("isSupplier"));
                setSupplier(isSupplier);
            }
            if(object.has("isWholesaler"))
            {
                boolean isWholesaler= Boolean.parseBoolean(object.getString("isWholesaler"));
                setWholesaler(isWholesaler);
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


}
