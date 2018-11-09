package com.knstech.apnaopd.GenModalClasses.Retailer;

import android.widget.ScrollView;

import com.knstech.apnaopd.GenModalClasses.Doctor.Patient;

import org.json.JSONException;
import org.json.JSONObject;

public class Order {
    private String status;
    private String comment;
    private String orderId;

    public static Order parseFromJson(String jsonStr)
    {
        Order order=new Order();

        try {

            JSONObject obj=new JSONObject(jsonStr);
            if(obj.has("_id"))
            {
                order.setOrderId(obj.getString("_id"));
            }
            if(obj.has("status"))
            {
                order.setStatus(obj.getString("status"));
            }
            if(obj.has("comment"))
            {
                order.setComment(obj.getString("comment"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
