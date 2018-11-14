package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.knstech.apnaopd.Utils.MyConnectionTester;

public class RequestDelete {
    private Context mContext;

    public RequestDelete(Context mContext)
    {

        this.mContext = mContext;
    }
    public void requestDelete(String url, final OnDeleteListener mListener)
    {
        StringRequest request=new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                mListener.onDelete();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                new MyConnectionTester().buildDialog2(mContext);
            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }
    public interface OnDeleteListener
    {
        void onDelete();
    }
}
