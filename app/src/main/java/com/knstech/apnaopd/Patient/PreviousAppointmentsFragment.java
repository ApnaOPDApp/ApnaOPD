package com.knstech.apnaopd.Patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knstech.apnaopd.GenModelClasses.Doctor.Patient;
import com.knstech.apnaopd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousAppointmentsFragment extends Fragment {


    public PreviousAppointmentsFragment() {
        // Required empty public constructor
    }
    private View view;
    private RecyclerView recyclerView;
    private PreviousAppointmentsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Patient> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_previous_appointments, container, false);

        recyclerView=view.findViewById(R.id.recView);
        initRecyclerView();


        return view;
    }

    private void initRecyclerView() {

        mList=new ArrayList<>();
        adapter=new PreviousAppointmentsAdapter(mList,getActivity());
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        populateRecyclerView();
    }

    private void populateRecyclerView() {

        PAppointmentViewerActivity activity= (PAppointmentViewerActivity) getActivity();
        List<Patient> suList=activity.getmList();
        for (int i=0;i<suList.size();i++)
        {
            Patient patient=suList.get(i);
            if(patient.getStatus().equals("2"))
            {
                mList.add(patient);
                adapter.notifyDataSetChanged();
            }
        }

    }

}
