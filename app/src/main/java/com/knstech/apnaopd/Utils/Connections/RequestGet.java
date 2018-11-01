package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class RequestGet {
    private Context mContext;

    public interface JSONArrayResponseListener{
        public void onResponse(JSONArray jsonArray);
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
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(jsonArrayRequest);

    }
}
