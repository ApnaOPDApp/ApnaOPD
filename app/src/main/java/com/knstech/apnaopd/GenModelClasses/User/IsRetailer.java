
package com.knstech.apnaopd.GenModelClasses.User;


import com.google.gson.Gson;

public class IsRetailer {


    private String type;

    private String _default;

    public static IsRetailer parseFromJson(String json)
    {
        Gson gson=new Gson();
        return gson.fromJson(json,IsRetailer.class);
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

}
