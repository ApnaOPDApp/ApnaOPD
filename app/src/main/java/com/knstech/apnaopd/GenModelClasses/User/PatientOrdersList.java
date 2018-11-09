package com.knstech.apnaopd.GenModelClasses.User;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.knstech.apnaopd.GenModalClasses.User.Qutotation;

public class PatientOrdersList {

    private String retailer_gid;
    private String quotation_link;
    private String delivery_time;
    private String delivery_charge;

    public List<Qutotation> getQutotation() {
        return qutotation;
    }

    public void setQutotation(List<Qutotation> qutotation) {
        this.qutotation = qutotation;
    }

    private List<Qutotation> qutotation;

    public static PatientOrdersList parseFromJson(JSONObject json)
    {
       PatientOrdersList pup=new PatientOrdersList();

        try {
            if(json.has("quotation")) {
                List<Qutotation> qutotations=new ArrayList<>();
                JSONArray array=json.getJSONArray("quotation");
                for(int i=0;i<array.length();i++)
                {
                    JSONObject object=array.getJSONObject(i);
                    Qutotation q=(new Gson()).fromJson(object.toString(),Qutotation.class);
                    qutotations.add(q);
                }
                pup.setQutotation(qutotations);
            }
            if(json.has("retailer_gid"))
            {
                pup.setRetailer_gid(json.getString("retailer_gid"));
            }
            if(json.has("quotation_link"))
            {
                pup.setQuotation_link(json.getString("quotation_link"));
            }
            if(json.has("delivery_time"))
            {
                pup.setDelivery_time(json.getString("delivery_time"));
            }
            if(json.has("delivery_charge"))
            {
                pup.setDelivery_charge(json.getString("delivery_charge"));
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
