package com.knstech.apnaopd;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pankaj Vaghela on 25-10-2018.
 */
public class User {
    private String email;
    private String name;
    private String imageUrl;
    private String gid;
    private boolean isDoctor = false;
    private boolean isRetailer = false;
    private boolean isWholeseller = false;
    private boolean isManufacturer = false;
    private boolean isSupplier = false;

    public User(){}

    public static User parseFromJson(String userJSON){
        User user = new  User();
        try {
            JSONObject json = new JSONObject(userJSON);

            user.setEmail(json.getString("email"));
            user.setName(json.getString("name"));
            user.setImageUrl(json.getString("imageUrl"));
            user.setGid(json.getString("gid"));
            user.setDoctor(json.getBoolean("isSupplier"));
            user.setRetailer(json.getBoolean("isRetailer"));
            user.setWholeseller(json.getBoolean("isWholeseller"));
            user.setManufacturer(json.getBoolean("isManufacturer"));
            user.setSupplier(json.getBoolean("isSupplier"));


        }catch (JSONException e){
            e.printStackTrace();
        }
        return user;
    }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getGid() { return gid; }
    public void setGid(String gid) { this.gid = gid; }

    public boolean isDoctor() { return isDoctor; }
    public void setDoctor(boolean doctor) { isDoctor = doctor; }

    public boolean isRetailer() { return isRetailer; }
    public void setRetailer(boolean retailer) { isRetailer = retailer; }

    public boolean isWholeseller() { return isWholeseller; }
    public void setWholeseller(boolean wholeseller) { isWholeseller = wholeseller; }

    public boolean isManufacturer() { return isManufacturer; }
    public void setManufacturer(boolean manufacturer) { isManufacturer = manufacturer; }

    public boolean isSupplier() { return isSupplier; }
    public void setSupplier(boolean supplier) { isSupplier = supplier; }
}
