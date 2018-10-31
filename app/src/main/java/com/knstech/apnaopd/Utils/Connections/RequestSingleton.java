package com.knstech.apnaopd.Utils.Connections;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestSingleton {
    private RequestQueue queue;

    private static RequestSingleton ourInstance;
    private Context mContext;

    public static RequestSingleton getInstance(Context mContext) {
        if(ourInstance==null)
        {
            ourInstance=new RequestSingleton(mContext);
        }
        return ourInstance;

    }

    private RequestSingleton(Context mContext) {
        this.mContext = mContext;
        queue=getQueue();
    }

    public RequestQueue getQueue() {
        if(queue==null)
        {
            queue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return queue;
    }
    public <T> void addToQueue(Request<T> request)
    {
        getQueue().add(request);
    }
}
