package com.knstech.apnaopd.GenModelClasses.Retailer;

import com.google.gson.Gson;
import com.knstech.apnaopd.GenModelClasses.User.Address;
import com.knstech.apnaopd.GenModelClasses.User.Qutotation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PojoConfirmedOrder {

    private String profile_image;
    private Address address;
    private String photo_prescription_link;
    private List<Offers> offers;
    private String status;
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public static PojoConfirmedOrder parseFromJson(JSONObject json)
    {
        PojoConfirmedOrder pup=new PojoConfirmedOrder();

        try {
            if(json.has("address")) {

                Address address = null;
                try {
                    address = (new Gson()).fromJson(json.get("address").toString(), Address.class);
                    pup.setAddress(address);

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
            if(json.has("offers")) {
                List<Offers> offersList=new ArrayList<>();
                JSONArray array=json.getJSONArray("offers");
                for(int i=0;i<array.length();i++)
                {
                    JSONObject object=array.getJSONObject(i);
                    Offers offers;
                    offers=Offers.parseFromJson(object);
                    offersList.add(offers);
                }
                pup.setOffers(offersList);

            }

            if(json.has("profile_image"))
            {
                pup.setProfile_image(json.getString("profile_image"));
            }
            if(json.has("status"))
            {
                pup.setStatus(json.getString("status"));
            }
            if(json.has("photo_prescription_link"))
            {
                pup.setPhoto_prescription_link(json.getString("photo_prescription_link"));
            }

            if(json.has("_id")){
                pup.set_id(json.getString("_id"));
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return pup;

    }


    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoto_prescription_link() {
        return photo_prescription_link;
    }

    public void setPhoto_prescription_link(String photo_prescription_link) {
        this.photo_prescription_link = photo_prescription_link;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Offers> getOffers() {
        return offers;
    }

    public void setOffers(List<Offers> offers) {
        this.offers = offers;
    }
}
