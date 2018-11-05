package com.knstech.apnaopd.GenModalClasses.Doctor;

import android.content.Context;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.Utils.Connections.RequestGet;

import org.json.JSONObject;

public class DoctorAuth {
    private static Doctor mDoctor;
    private String url= AppUtils.HOST_ADDRESS+"/api/doctors/"+ UserAuth.getmUser().getGid();

    public static Doctor getmDoctor() {
        return mDoctor;
    }
    public void signInDoctor(Context mContext)
    {
        RequestGet getRequest= new RequestGet(mContext);
        getRequest.getJSONObject(url, new RequestGet.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject object) {
                mDoctor.parseFromJson(object.toString());
            }
        });
    }
}
