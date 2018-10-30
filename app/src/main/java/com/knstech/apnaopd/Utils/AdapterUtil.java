package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.knstech.apnaopd.R;

public class AdapterUtil {
    public static void setSpinnerAdapter(Spinner spinner, String ar[], final Context mContext)
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext, R.layout.spinner_item,ar){
            @Override
            public boolean isEnabled(int position) {
                if(position==0)
                {
                    return false;
                }
                return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getDropDownView(position,convertView,parent);
                TextView tv= (TextView) view;
                if(position==0) {
                    tv.setTextColor(mContext.getResources().getColor(R.color.black_app));
                }
                else
                {
                    tv.setTextColor(mContext.getResources().getColor(R.color.black));
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }
}
