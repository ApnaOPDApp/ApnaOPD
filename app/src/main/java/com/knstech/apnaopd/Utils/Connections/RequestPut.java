package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestPut {
    private Context mContext;

    public RequestPut(Context mContext)
    {

        this.mContext = mContext;
    }
    public void putJSONObject(String url, final Map<String,String> map, final RequestPut.JSONObjectResponseListener mListener)
    {

        Map map1=new HashMap();
        map1.put("casesheet",map);
        JSONObject jsonObject=new JSONObject(map1);

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CASE_SHEET","Chutiyap ho gya");
            }
        }){

        };
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }

    public interface JSONObjectResponseListener {
        void onResponse(JSONObject object);
    }
}
