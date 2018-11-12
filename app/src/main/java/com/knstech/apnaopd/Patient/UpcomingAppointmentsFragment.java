package com.knstech.apnaopd.Patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.Patient.ADAPTORS.UpcomingAppointmentsAdapter;
import com.knstech.apnaopd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingAppointmentsFragment extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private UpcomingAppointmentsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Patient> mList;

    public UpcomingAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_upcoming_appointments, container, false);

        recyclerView=view.findViewById(R.id.recView);
        initRecycler();



        return view;
    }

    private void initRecycler() {

        mList=new ArrayList<>();
        adapter=new UpcomingAppointmentsAdapter(mList,getActivity());
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        populateRecycler();

    }

    private void populateRecycler() {

        PAppointmentViewerActivity activity= (PAppointmentViewerActivity) getActivity();
        List<Patient> suList=activity.getmList();
        for(int i=0;i<suList.size();i++)
        {
            Patient patient=suList.get(i);
            if(!patient.getStatus().equals("2"))
            {
                mList.add(patient);
                adapter.notifyDataSetChanged();
            }
        }



    }

}
