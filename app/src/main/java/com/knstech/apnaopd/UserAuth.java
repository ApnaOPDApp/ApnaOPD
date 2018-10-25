package com.knstech.apnaopd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pankaj Vaghela on 25-10-2018.
 */
public class UserAuth {

    private static User mUser;

    private interface SignInCompleteListener{

        void onComplete();

    }


    public UserAuth(){}

    public UserAuth getInstance(){
         return ApnaOPDApp.getUserAuth();
    }

    public User getCurrentUser() {
        return mUser;
    }


    public void signInGoogle(final Context context, final GoogleSignInAccount gAcc){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
//        String url ="https://google.com";
        String url ="http://192.168.43.193:3000/api/users/"+gAcc.getId();

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

                        if(response.equals("null")){
                           signUpGoogle(context,gAcc);
                        }else{

                            //opens patient activity
                            Intent i=new Intent(context,PatientActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);


                            context.startActivity(i);
                            AppCompatActivity activity=(AppCompatActivity)context;
                            activity.finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void signUpGoogle(final Context context, final GoogleSignInAccount gAcc){

        String url ="http://192.168.43.193:3000/api/users";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> user = new HashMap<String, String>();
                user.put("gid", gAcc.getId());
                user.put("name",gAcc.getDisplayName());
                user.put("email",gAcc.getEmail());
                user.put("imageUrl",gAcc.getPhotoUrl()+"");
                return user;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }




}
