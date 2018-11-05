package com.knstech.apnaopd.Utils;

public class C {
    public static final int CARDIO=1;
    public static final int GENITO=2;
    public static final int EAR=3;
    public static final int EYE=4;
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

}
