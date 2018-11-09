package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

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
    public interface ArrayResponseListener{
        void onResponse(JSONArray array);
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
    public void sendJSON(String url, JSONObject obj, final PostResponseListener listener, final PostErrorListener errorListener)
    {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onError();
            }
        });

        RequestSingleton.getInstance(mContext).addToQueue(request);

    }
    public void sendJSONArray(String url, JSONArray array, final ArrayResponseListener listener)
    {
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.POST, url, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(arrayRequest);
    }
}
