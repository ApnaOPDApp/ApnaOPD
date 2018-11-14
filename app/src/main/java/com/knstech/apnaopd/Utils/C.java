package com.knstech.apnaopd.Utils;

public class C {
    public static final int CARDIO=1;
    public static final int GENITO=4;
    public static final int EAR=3;
    public static final int EYE=2;
    public static final int NEURO=5;
    public static final int GENERAL=6;
    public static String getValue(String str)
    {
        switch (str)
        {
            case "title":return "Title :";
            case "time" :return "Time :";
            case "department": return "Department :";
            case "pulse" : return "Pulse :";
            case "rhythm" : return "Rhythm";
            case "neckveins" : return "Neck Veins :";
            case "chestpain" : return "Chest Pain :";
            case "respiration" :return "Respiration";
            case "cardio_condition":
            case "eye_condition":
            case "ear_condition":
            case "neuro_condition" : return "Condition :";
            case "bleeding" :return "Bleeding :";
            case "general_survey":return  "General Survey";
            case "genito_urinary":return "Genitourinary";
            case "genito_other":
            case "eye_other":
            case "ear_other":
            case "neuro_other":return "Other :";
            case "eye_misc":
            case "ear_misc":
            case "neuro_misc":return "Miscellaneous :";
            case "habit":return "Habit :";
            case "habitat":return "Habitat :";
            case "emotional_status": return "Emotional Status :";
            case "pain":return "Pain :";
            case "site_of_problem":return "Site of Problem :";
            case "vomiting":return "Vomiting :";
            case "problem_string":return "Problem :";
            case "accident":return "Accident :";
            case "fever":return "Fever :";
        }
        return "";
    }
    public static String setName(int name) {
        switch (name)
        {
            case 1:return "Morning";
            case 2:return "Afternoon";
            case 3:return "Evening";
            case 4:return "Night";
            default: return "";
        }
    }

    public static String setDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek)
        {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default:return "";
        }
    }

    public static String getTime(String s) {

        int i=s.charAt(1)-'0';
        if(s.charAt(0)-'0'==1)
        {
            int time=i+5;
            return time+":00 AM - "+time+":55 AM";
        }
        else if(s.charAt(0)-'0'==2)
        {
            int time=((11+i)%12==0)?12:((11+i)%12);
            return time+":00 PM - "+time+":55 PM";
        }
        else if(s.charAt(0)-'0'==3)
        {
            int time=i+5;
            return time+":00 PM - "+time+":55 PM";
        }
        else
        {
            int time=((11+i)%12==0)?12:((11+i)%12);
            return time+":00 AM - "+time+":55 AM";
        }
    }
    public static String[] getStringArray(String str) {
        String ar[];
        if(str.length()!=2)
        {
            String s=str.substring(2,str.length()-2);
            ar=s.split("\",\"");
        }
        else
        {
            ar=null;
        }
        return ar;
    }

    public static String getTimeCode(String string) {

        String res="";
        String time=string.substring(0,string.indexOf(":"));
        int isAm=string.indexOf("AM");
        int timeVal=Integer.parseInt(time);
        if(timeVal>=6&&timeVal<12)
        {
            if(isAm!=-1)
            {
                res="1"+(timeVal-5);
            }
            else
            {
                res="3"+(timeVal-5);
            }
        }
        else
        {
            if(isAm!=-1)
            {
                res="4"+((timeVal)%12+1);
            }
            else
            {
                res="2"+((timeVal)%12+1);
            }
        }
        return res;

    }

    public static String getDateAndTime(String time_slab) {

        int day=time_slab.charAt(0)-'0';
        String time=time_slab.substring(1,3);

        return setDayOfWeek(day)+"\n"+getTime(time);

    }
}
