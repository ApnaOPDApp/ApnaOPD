package com.knstech.apnaopd.Patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.knstech.apnaopd.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorProfileFragment extends Fragment {


    private View v;
    private EditText degree,work,speciality,exp,fee;
    private Button save;
    private Spinner spinner;

    public DoctorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_doctor_profile, container, false);

        degree=v.findViewById(R.id.degree);
        work=v.findViewById(R.id.work);
        speciality=v.findViewById(R.id.speciality);
        exp=v.findViewById(R.id.exp);
        fee=v.findViewById(R.id.fee);
        save=v.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(fee.getText())&&!TextUtils.isEmpty(degree.getText())&&!TextUtils.isEmpty(exp.getText()))
                {
                    if(!TextUtils.isEmpty(speciality.getText())&&!TextUtils.isEmpty(work.getText()))
                    {
                        String degStr,workStr,expStr,feeStr,specStr;
                        degStr=degree.getText().toString();
                        workStr=work.getText().toString();
                        expStr=exp.getText().toString();
                        feeStr=fee.getText().toString();
                        specStr=speciality.getText().toString();
                        degree.setText("");
                        work.setText("");
                        exp.setText("");
                        fee.setText("");
                        speciality.setText("");
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Empty Fields!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Empty Fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }

}
