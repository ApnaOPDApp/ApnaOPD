package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.knstech.apnaopd.DoctorAppointmentActivity;
import com.knstech.apnaopd.R;

public class UIUpdater {
    public static void updateCardio(Context mContext, RelativeLayout rootLayout,View selectedCS) {
        EditText pulse,comment;
        Spinner neckVeins,chestPain,respiration,rhythm,bleeding,condition;
        Button fileUpload,submit;

        AppCompatActivity activity=(AppCompatActivity)mContext;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(activity).inflate(R.layout.cardio_cs,null);
        rootLayout.addView(selectedCS);

        //init editText
        pulse=selectedCS.findViewById(R.id.pulse);
        comment=selectedCS.findViewById(R.id.comment);

        //init spinners
        neckVeins=selectedCS.findViewById(R.id.neckVeins);
        chestPain=selectedCS.findViewById(R.id.chestPain);
        respiration=selectedCS.findViewById(R.id.respiration);
        rhythm=selectedCS.findViewById(R.id.rhythm);
        bleeding=selectedCS.findViewById(R.id.bleeding);
        condition=selectedCS.findViewById(R.id.condition);

        //init buttons
        fileUpload=selectedCS.findViewById(R.id.upload);
        submit=selectedCS.findViewById(R.id.submit);

        String bleedAr[]=mContext.getResources().getStringArray(R.array.bleed_cardio);
        String rhythmAr[]=mContext.getResources().getStringArray(R.array.rhythm_cardio);
        String chestPainAr[]=mContext.getResources().getStringArray(R.array.chest_cardio);
        String respAr[]=mContext.getResources().getStringArray(R.array.resp_cardio);
        String neckAr[]=mContext.getResources().getStringArray(R.array.neck_cardio);
        String conditionAr[]=mContext.getResources().getStringArray(R.array.condition_cardio);
        //setAdapters
        AdapterUtil.setSpinnerAdapter(neckVeins,neckAr,activity);
        AdapterUtil.setSpinnerAdapter(chestPain,chestPainAr,activity);
        AdapterUtil.setSpinnerAdapter(respiration,respAr,activity);
        AdapterUtil.setSpinnerAdapter(rhythm,rhythmAr,activity);
        AdapterUtil.setSpinnerAdapter(bleeding,bleedAr,activity);
        AdapterUtil.setSpinnerAdapter(condition,conditionAr,activity);


        //init buttons
        submit=activity.findViewById(R.id.submit);
        fileUpload=activity.findViewById(R.id.upload);



    }

    public static void updateEar(Context mContext,RelativeLayout rootLayout,View selectedCS) {

        Spinner condition,other,misc;
        Button upload,submit;
        EditText comment;

        AppCompatActivity activity=(AppCompatActivity)mContext;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(activity).inflate(R.layout.ear_cs,null);
        rootLayout.addView(selectedCS);

        //init editText
        comment=selectedCS.findViewById(R.id.comment);

        //init spinner
        condition=selectedCS.findViewById(R.id.condition);
        other=selectedCS.findViewById(R.id.other);
        misc=selectedCS.findViewById(R.id.misc);

        //init button
        upload=selectedCS.findViewById(R.id.upload);
        submit=selectedCS.findViewById(R.id.submit);

        //init string arrays
        String conditionAr[]=mContext.getResources().getStringArray(R.array.condition_ear);
        String otherAr[]=mContext.getResources().getStringArray(R.array.other_ear);
        String miscAr[]=mContext.getResources().getStringArray(R.array.misc_ear);

        AdapterUtil.setSpinnerAdapter(condition,conditionAr,activity);
        AdapterUtil.setSpinnerAdapter(other,otherAr,activity);
        AdapterUtil.setSpinnerAdapter(misc,miscAr,activity);



    }

    public static void updateEye(Context mContext, RelativeLayout rootLayout, View selectedCS) {

        Spinner condition,other,misc;
        EditText comment;
        Button upload,submit;

        AppCompatActivity activity=(AppCompatActivity)mContext;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(activity).inflate(R.layout.eye_cs,null);
        rootLayout.addView(selectedCS);

        //init editText
        comment=selectedCS.findViewById(R.id.comment);

        //init spinner
        condition=selectedCS.findViewById(R.id.condition);
        other=selectedCS.findViewById(R.id.other);
        misc=selectedCS.findViewById(R.id.misc);

        //init button
        upload=selectedCS.findViewById(R.id.upload);
        submit=selectedCS.findViewById(R.id.submit);

        //init arrays
        String conditionAr[]=mContext.getResources().getStringArray(R.array.condition_eye);
        String otherAr[]=mContext.getResources().getStringArray(R.array.other_eye);
        String miscAr[]=mContext.getResources().getStringArray(R.array.misc_eye);

        //set adapter
        AdapterUtil.setSpinnerAdapter(condition,conditionAr,activity);
        AdapterUtil.setSpinnerAdapter(other,otherAr,activity);
        AdapterUtil.setSpinnerAdapter(misc,miscAr,activity);
    }

    public static void updateGenito(Context mContext, RelativeLayout rootLayout, View selectedCS) {

        Spinner survey,genito,other;
        Button upload,submit;
        EditText comment;

        AppCompatActivity activity=(AppCompatActivity)mContext;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(activity).inflate(R.layout.genito_cs,null);
        rootLayout.addView(selectedCS);


        //init editText
        comment=selectedCS.findViewById(R.id.comment);

        //init spinner
        survey=selectedCS.findViewById(R.id.survey);
        genito=selectedCS.findViewById(R.id.genitourinary);
        other=selectedCS.findViewById(R.id.other);

        //init button
        upload=selectedCS.findViewById(R.id.upload);
        submit=selectedCS.findViewById(R.id.submit);

        //init array
        String surveyAr[]=mContext.getResources().getStringArray(R.array.general_genito);
        String genitoAr[]=mContext.getResources().getStringArray(R.array.genito_genito);
        String otherAr[]=mContext.getResources().getStringArray(R.array.other_genito);

        //set adapter
        AdapterUtil.setSpinnerAdapter(survey,surveyAr,activity);
        AdapterUtil.setSpinnerAdapter(genito,genitoAr,activity);
        AdapterUtil.setSpinnerAdapter(other,otherAr,activity);

    }

    public static void updateNeuro(Context mContext, RelativeLayout rootLayout, View selectedCS) {

        Spinner sensation,behavioural,condition,other,misc;
        Button submit;
        EditText comment;

        AppCompatActivity activity=(AppCompatActivity)mContext;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(activity).inflate(R.layout.neuro_cs,null);
        rootLayout.addView(selectedCS);


        //init editText
        comment=selectedCS.findViewById(R.id.comment);

        //init spinner
        sensation=selectedCS.findViewById(R.id.sensation);
        condition=selectedCS.findViewById(R.id.condition);
        behavioural=selectedCS.findViewById(R.id.behavioural);
        other=selectedCS.findViewById(R.id.other);
        misc=selectedCS.findViewById(R.id.misc);

        //init array
        String sensationAr[]=mContext.getResources().getStringArray(R.array.sensation_neuro);
        String behaviouralAr[]=mContext.getResources().getStringArray(R.array.behavioural_neuro);
        String conditionAr[]=mContext.getResources().getStringArray(R.array.condition_neuro);
        String otherAr[]=mContext.getResources().getStringArray(R.array.other_neuro);
        String miscAr[]=mContext.getResources().getStringArray(R.array.misc_neuro);

        //set Adapter
        AdapterUtil.setSpinnerAdapter(sensation,sensationAr,activity);
        AdapterUtil.setSpinnerAdapter(behavioural,behaviouralAr,activity);
        AdapterUtil.setSpinnerAdapter(condition,conditionAr,activity);
        AdapterUtil.setSpinnerAdapter(other,otherAr,activity);
        AdapterUtil.setSpinnerAdapter(misc,miscAr,activity);


    }

    public static void updateGeneral(Context mContext, RelativeLayout rootLayout, View selectedCS) {

        EditText problem,accident,fever,comment;
        Spinner habit,habitat,emotional,pain,site,vomit;
        Button upload,submit;

        AppCompatActivity activity=(AppCompatActivity)mContext;

        //update view
        rootLayout.removeView(selectedCS);
        selectedCS= LayoutInflater.from(activity).inflate(R.layout.activity_cscreator,null);
        rootLayout.addView(selectedCS);


        //init editText
        comment=selectedCS.findViewById(R.id.comment);
        problem=selectedCS.findViewById(R.id.problem);
        accident=selectedCS.findViewById(R.id.accident);
        fever=selectedCS.findViewById(R.id.fever);

        //init button
        upload=selectedCS.findViewById(R.id.upload);
        submit=selectedCS.findViewById(R.id.submit);

        //init spinner
        habit=selectedCS.findViewById(R.id.habit);
        habitat=selectedCS.findViewById(R.id.habitat);
        emotional=selectedCS.findViewById(R.id.emotional);
        pain=selectedCS.findViewById(R.id.pain);
        vomit=selectedCS.findViewById(R.id.vomit);

        //init array
        String habitAr[]=mContext.getResources().getStringArray(R.array.habit_gen);
        String habitatAr[]=mContext.getResources().getStringArray(R.array.habitat_gen);
        String emotionalAr[]=mContext.getResources().getStringArray(R.array.emotional_gen);
        String painAr[]=mContext.getResources().getStringArray(R.array.pain_gen);
        String vomitAr[]=mContext.getResources().getStringArray(R.array.vomit_gen);

        //set adapter
        AdapterUtil.setSpinnerAdapter(habit,habitAr,activity);
        AdapterUtil.setSpinnerAdapter(habitat,habitatAr,activity);
        AdapterUtil.setSpinnerAdapter(emotional,emotionalAr,activity);
        AdapterUtil.setSpinnerAdapter(pain,painAr,activity);
        AdapterUtil.setSpinnerAdapter(vomit,vomitAr,activity);


    }
}
