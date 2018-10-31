package com.knstech.apnaopd;

import com.knstech.apnaopd.Patient.UserAuth;

/**
 * Created by Pankaj Vaghela on 25-10-2018.
 */
public class ApnaOPDApp {

    private static UserAuth mUserAuth;

    public static UserAuth getUserAuth() {
        if(mUserAuth==null) mUserAuth = new UserAuth();
        return mUserAuth;
    }
}
