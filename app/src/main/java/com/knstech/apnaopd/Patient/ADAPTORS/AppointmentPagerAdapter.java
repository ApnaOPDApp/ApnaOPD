package com.knstech.apnaopd.Patient.ADAPTORS;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.knstech.apnaopd.Patient.PreviousAppointmentsFragment;
import com.knstech.apnaopd.Patient.UpcomingAppointmentsFragment;

public class AppointmentPagerAdapter extends FragmentPagerAdapter{
    public AppointmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                UpcomingAppointmentsFragment upcoming=new UpcomingAppointmentsFragment();
                return upcoming;
            case 1:
                PreviousAppointmentsFragment previous=new PreviousAppointmentsFragment();
                return previous;
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0: return "Upcoming";
            case 1: return "Previous";
            default :return "";
        }
    }
}
