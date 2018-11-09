package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestPut {
    private Context mContext;

    public RequestPut(Context mContext)
    {
        this.mContext = mContext;
    }

    public interface JSONArrayResponseListener{
        void onResponse(JSONArray jsonArray);
    }
    public void putJSONObject(String url, JSONObject jsonObject, final RequestPut.JSONObjectResponseListener mListener)
    {

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
    public void putJSONObject(String url, JSONObject jsonObject, final RequestPut.JSONObjectResponseListener mListener, final RequestPut.JSONObjectErrorListener mErrorListener)
    {

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mErrorListener.onError(error);
            }
        }){

        };
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }
    public void putJSONArray(String url, JSONArray array, final JSONArrayResponseListener mListener)
    {
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.PUT, url, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }
    public interface JSONObjectResponseListener {
        void onResponse(JSONObject object);
    }
    public interface JSONObjectErrorListener{
        void onError(VolleyError error);
    }

    public interface OnStringPutResponseListener {
        void onResponse(String str);
    }
}
