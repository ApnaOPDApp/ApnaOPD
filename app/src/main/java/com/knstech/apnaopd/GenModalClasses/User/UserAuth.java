package com.knstech.apnaopd.GenModalClasses.User;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestSingleton;

import java.util.HashMap;
import java.util.Map;

public class UserAuth {
    public UserAuth()
    {
        
    }
    private static User mUser=new User();

    public static User getmUser() {
        if(mUser==null)
        {
            mUser=new User();
        }
        return mUser;
    }

    public interface SignInCompleteListener
    {
        void onComplete();
    }
    public void signInGoogle(final Context mContext, final GoogleSignInAccount account, final SignInCompleteListener listener)
    {
        String url= AppUtils.HOST_ADDRESS+"/api/users/"+account.getId();
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals(""))
                {
                    signUpGoogle(mContext,account,listener);
                }
                else
                {
                    mUser.parseFromJson(response);
                    listener.onComplete();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }
    public void signUpGoogle(final Context mContext, final GoogleSignInAccount account, final SignInCompleteListener listener)
    {
        String url=AppUtils.HOST_ADDRESS+"/api/users/";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mUser.parseFromJson(response);
                listener.onComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("gid",account.getId());
                map.put("name",account.getDisplayName());
                map.put("email",account.getEmail());
                map.put("imageUrl", String.valueOf(account.getPhotoUrl()));
                return map;
            }
        };
        RequestSingleton.getInstance(mContext).addToQueue(request);
    }
}
