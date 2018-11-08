package com.knstech.apnaopd.Utils;

import com.knstech.apnaopd.GenModalClasses.Doctor.TimeSlab;

import java.util.List;

public interface OnTimeOfDayClickListener {
    public void OnClick(int i, TimeSlabAdapter timeSlabAdapter, List<TimeSlab> list);
}
