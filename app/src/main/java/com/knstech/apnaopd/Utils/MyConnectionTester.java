package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyConnectionTester {

    public Context context;

    public MyConnectionTester(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo !=null && activeNetworkInfo.isConnected();
    }

}
