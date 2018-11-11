package com.knstech.apnaopd.GenModelClasses.Retailer;

import com.google.gson.Gson;
import com.knstech.apnaopd.GenModelClasses.User.Qutotation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Offers {

    private String retailer_gid;
    private List<Qutotation> quotation;
    private String total_price;
    private String quotation_link;

    public List<Qutotation> getQuotation() {
        return quotation;
    }

    public void setQuotation(List<Qutotation> quotation) {
        this.quotation = quotation;
    }

    private String delivery_time;
    private String  delivery_charge;

    public static Offers parseFromJson(JSONObject json)
    {
        Offers pup=new Offers();

        try {
            if(json.has("quotation")) {

                List<Qutotation> qutotation = null;
                qutotation = Qutotation.parseFromJson(json.getJSONArray("quotation").toString());
                pup.setQuotation(qutotation);

            }
            if(json.has("retailer_gid"))
            {
                pup.setRetailer_gid(json.getString("retailer_gid"));
            }
            if(json.has("quotation_link"))
            {
                pup.setQuotation_link(json.getString("quotation_link"));
            }
            if(json.has("delivery_charge"))
            {
                pup.setDelivery_charge(json.getString("delivery_charge"));
            }
            if(json.has("delivery_time"))
            {
                pup.setDelivery_time(json.getString("delivery_time"));
            }
            if(json.has("total_price"))
            {
                pup.setTotal_price(json.getString("total_price"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pup;

    }


    public String getRetailer_gid() {
        return retailer_gid;
    }

    public void setRetailer_gid(String retailer_gid) {
        this.retailer_gid = retailer_gid;
    }


    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getQuotation_link() {
        return quotation_link;
    }

    public void setQuotation_link(String quotation_link) {
        this.quotation_link = quotation_link;
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
}
