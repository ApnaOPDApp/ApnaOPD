
package com.knstech.apnaopd.GenModalClasses.User;


import com.google.gson.Gson;

public class IsSupplier {


    private String type;

    private String _default;

    public static IsSupplier parseFromJson(String json)
    {
        Gson gson=new Gson();
        return gson.fromJson(json,IsSupplier.class);
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
