package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import com.knstech.apnaopd.R;

public class MyConnectionTester {

    public boolean isConnected(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo !=null && activeNetworkInfo.isConnectedOrConnecting()){
            NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile !=null && mobile.isConnectedOrConnecting())||(wifi !=null && wifi.isConnectedOrConnecting())){
                return  true;
            }
        }

         return  false;
    }

    public AlertDialog.Builder buildDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You Don't have Internet Connection. Please check and try again !");
        builder.setIcon(R.drawable.surajsir);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               dialog.dismiss();
            }
        });

        return builder;
    }
    public AlertDialog.Builder buildDialog2(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("OOPS !!! Sorry ");
        builder.setMessage("Server is under maintainance");
        builder.setIcon(R.drawable.p7);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        return builder;
    }

}
