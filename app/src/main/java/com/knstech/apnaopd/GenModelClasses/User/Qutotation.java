package com.knstech.apnaopd.GenModelClasses.User;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Qutotation {

    private String medicine;
    private String price;
    private String offered_price;
    private String dosage_per;
    private String dosage_day;




    public static List<Qutotation> parseFromJson(String json)
    {
        List<Qutotation> list=new ArrayList<>();
        try {
            Gson gson=new Gson();
            JSONArray array=new JSONArray(json);
            for(int i=0;i<array.length();i++)
            {
                if(array.get(i)!=null) {
                    Qutotation qutotation = gson.fromJson(array.get(i).toString(),Qutotation.class);
                    list.add(qutotation);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }


    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffered_price() {
        return offered_price;
    }

    public void setOffered_price(String offered_price) {
        this.offered_price = offered_price;
    }

    public String getDosage_per() {
        return dosage_per;
    }

    public void setDosage_per(String dosage_per) {
        this.dosage_per = dosage_per;
    }

    public String getDosage_day() {
        return dosage_day;
    }

    public void setDosage_day(String dosage_day) {
        this.dosage_day = dosage_day;
    }
}
