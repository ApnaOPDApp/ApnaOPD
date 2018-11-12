package com.knstech.apnaopd;

import com.knstech.apnaopd.GenModelClasses.User.UserAuth;


public class ApnaOPDApp {

    private static UserAuth mUserAuth;

    public static UserAuth getUserAuth() {
        if(mUserAuth==null) mUserAuth = new UserAuth();
        return mUserAuth;
    }
}
