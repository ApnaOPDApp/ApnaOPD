package com.knstech.apnaopd.GenModelClasses.Doctor;

import android.content.Context;
import android.content.SharedPreferences;

import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONObject;

public class DoctorAuth {
    private static Doctor mDoctor;

    public static Doctor getmDoctor(Context mContext) {
        mDoctor=new Doctor();
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("Users",Context.MODE_PRIVATE);
        String json=sharedPreferences.getString("doctor_json","");
        mDoctor.parseFromJson(json);
        return mDoctor;
    }
    public void signInDoctor(final Context mContext, final OnSignInListener signInListener)
    {
        RequestGet getRequest= new RequestGet(mContext);
        String url=AppUtils.HOST_ADDRESS+"/api/doctors/"+ UserAuth.getmUser(mContext).getGid();
        getRequest.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject object) {
                signInListener.onSignIn();
                getmDoctor(mContext).parseFromJson(object.toString());
                SharedPreferences sharedPreferences=mContext.getSharedPreferences("Users",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("doctor_json",object.toString());
                editor.commit();
            }
        });
    }
    public interface OnSignInListener{
        void onSignIn();
    }
}
