// This will be used to download JSON object from specified URL.

package com.knstech.apnaopd.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class NetworkJSONLoader {
    Context context;
    TextView display;

    public NetworkJSONLoader(Context context, TextView display) {
        this.context = context;
        this.display = display;
    }

    public void requestJSON(String url){
        // TAG IS USED TO CANCEL THE REQUEST
        String tag_json_obj = "json_obj_req";
        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        display.setText(response.toString());
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

        //Adding request to request queue;
        VolleySingleton.getmInstance().addToRequestQueue(jsonObjectRequest,tag_json_obj);
    }
}
