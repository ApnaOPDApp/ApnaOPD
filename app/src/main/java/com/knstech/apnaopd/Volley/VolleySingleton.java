package com.knstech.apnaopd.Volley;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton extends Application {
    public static final String TAG=VolleySingleton.class.getSimpleName();
    private RequestQueue mRrequestQueue;
    private  static VolleySingleton mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

    }
    public static  synchronized VolleySingleton getmInstance(){
        return mInstance;
    }

    public RequestQueue getmRrequestQueue() {
        if(mRrequestQueue == null){
            mRrequestQueue = Volley.newRequestQueue(getApplicationContext());

        }
        return mRrequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag){
        // set the default tag if the tag is empty
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getmRrequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getmRrequestQueue().add(req);

    }

    public void cancelPendingRequest(Object tag){
        if(mRrequestQueue !=null )
            mRrequestQueue.cancelAll(tag);
    }
}
