package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.util.Map;

public class RequestGet {
    private Context mContext;

    public interface JSONArrayResponseListener{
        public void onResponse(JSONArray jsonArray);
    }
    public interface StringResponseListener{
        void onResponse(String response);
    }
    public RequestGet(Context mContext)
    {

        this.mContext = mContext;
    }
    public void getJSONArray(String url, final JSONArrayResponseListener mListener)
    {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mListener.onResponse(response);
                //Toast.makeText(mContext, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(jsonArrayRequest);

    }
    public void getString(String url, final StringResponseListener mListener)
    {
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }
}
