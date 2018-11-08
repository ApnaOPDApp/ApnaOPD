package com.knstech.apnaopd.Utils.Listeners;

import com.knstech.apnaopd.GenModalClasses.Doctor.DayOfWeek;
import com.knstech.apnaopd.GenModalClasses.Doctor.TimeOfDay;

public interface OnDayClickListener {
    void onClick(DayOfWeek timeOfDay);
}
