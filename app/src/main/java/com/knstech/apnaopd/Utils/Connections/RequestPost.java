package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Map;

public class RequestPost {
    private Context mContext;
    public RequestPost(Context mContext)
    {

        this.mContext = mContext;
    }
    public interface PostResponseListener
    {
        public void onResponse();
    }
    public  interface PostErrorListener{
        public void onError();
    }
    public void sendJSON(String url, final Map<String,String> params, final PostResponseListener listener, final PostErrorListener errorListener)
    {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onError();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        RequestSingleton.getInstance(mContext).addToQueue(request);

    }
}
