package com.knstech.apnaopd.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class NetworkStringLoader {
    Context context;
    TextView display;

    public NetworkStringLoader(Context context, TextView display) {

        this.context = context;
        this.display = display;
    }

    public void requestString(String url){

        // TAG used to cancel the request
        String tag_string_req = "string_req";
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        display.setText(response);
                        progressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        display.setText(error.toString());
                        progressDialog.hide();
                    }
                }
        );

        // ADDING REQUEST TO REQUEST QUEUE
        VolleySingleton.getmInstance().addToRequestQueue(stringRequest,tag_string_req);

    }
}
