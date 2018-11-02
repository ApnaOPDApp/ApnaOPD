// This will be used to download JSON object from specified URL.

package com.knstech.apnaopd.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class NetworkJSONLoader {
    Context context;
    JSONObject res;

    public NetworkJSONLoader(Context context) {
        this.context = context;
    }

    public JSONObject requestJSON(String url){
        // TAG IS USED TO CANCEL THE REQUEST
        String tag_json_obj = "json_obj_req";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        res= response;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

                    }
                }

        );

        //Adding request to request queue;
        VolleySingleton.getmInstance().addToRequestQueue(jsonObjectRequest,tag_json_obj);

        return res;
    }
}
