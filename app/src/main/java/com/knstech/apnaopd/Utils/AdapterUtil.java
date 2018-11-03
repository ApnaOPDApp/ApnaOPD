package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.knstech.apnaopd.R;

public class AdapterUtil {
    public static void setSpinnerAdapter(Spinner spinner, String ar[], final Context mContext)
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext, R.layout.spinner_item,ar);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }
}
