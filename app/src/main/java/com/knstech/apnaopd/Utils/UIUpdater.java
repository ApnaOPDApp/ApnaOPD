package com.knstech.apnaopd.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.knstech.apnaopd.Patient.DoctorAppointmentActivity;
import com.knstech.apnaopd.Patient.DoctorListActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestPost;

import java.util.HashMap;
import java.util.Map;

public class UIUpdater {
    public static final int IMG = 1;
    private static String url;

    public static void updateCardio(final Context mContext, RelativeLayout rootLayout, View selectedCS) {
        final EditText pulse,comment;
        final Spinner neckVeins,chestPain,respiration,rhythm,bleeding,condition;
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collect response
                String bleedResp,rhythmResp,chestResp,respResp,neckResp,condResp;
                String pulseResp,commentResp;

                bleedResp=bleeding.getSelectedItem().toString();
                rhythmResp=rhythm.getSelectedItem().toString();
                chestResp=chestPain.getSelectedItem().toString();
                respResp=respiration.getSelectedItem().toString();
                neckResp=neckVeins.getSelectedItem().toString();
                condResp=condition.getSelectedItem().toString();

                pulseResp=pulse.getText().toString();
                commentResp=comment.getText().toString();

                //put response in map
                Map<String,String> map=new HashMap<>();
                map.put("Bleeding",bleedResp);
                map.put("Chest Pain",chestResp);
                map.put("Rhythm",rhythmResp);
                map.put("Respiration",respResp);
                map.put("Neck Pain",neckResp);
                map.put("Condition",condResp);
                map.put("Pulse",pulseResp);
                map.put("Comment",commentResp);

                sendResponse(mContext);
            }
        });


    }

    public static void updateEar(final Context mContext, RelativeLayout rootLayout, View selectedCS) {

        final Spinner condition,other,misc;
        Button upload,submit;
        final EditText comment;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collect Response
                String condResp,otherResp,miscResp;
                String commentResp;

                condResp=condition.getSelectedItem().toString();
                otherResp=other.getSelectedItem().toString();
                miscResp=misc.getSelectedItem().toString();

                commentResp=comment.getText().toString();

                //put in map
                Map<String,String> map=new HashMap<>();
                map.put("Condition",condResp);
                map.put("Other",otherResp);
                map.put("Miscellaneous",miscResp);
                map.put("Comment",commentResp);
                
                sendResponse(mContext);
            }
        });


    }

    public static void updateEye(final Context mContext, RelativeLayout rootLayout, View selectedCS) {

        final Spinner condition,other,misc;
        final EditText comment;
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collect Response
                String condResp,otherResp,miscResp;
                String commentResp;

                condResp=condition.getSelectedItem().toString();
                otherResp=other.getSelectedItem().toString();
                miscResp=misc.getSelectedItem().toString();

                commentResp=comment.getText().toString();

                Map<String,String> map=new HashMap<>();
                map.put("Condition",condResp);
                map.put("Other",otherResp);
                map.put("Miscellaneous",miscResp);
                map.put("Comment",commentResp);

                sendResponse(mContext);
            }
        });
    }

    public static void updateGenito(final Context mContext, RelativeLayout rootLayout, View selectedCS) {

        final Spinner survey,genito,other;
        Button upload,submit;
        final EditText comment;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collect response
                String surveyResp,genitoResp,otherResp;
                String commentResp;

                surveyResp=survey.getSelectedItem().toString();
                genitoResp=genito.getSelectedItem().toString();
                otherResp=other.getSelectedItem().toString();

                commentResp=comment.getText().toString();

                Map<String,String> map=new HashMap<>();
                map.put("General Survey",surveyResp);
                map.put("Genitourinary",genitoResp);
                map.put("Other",otherResp);
                map.put("Comment",commentResp);

                sendResponse(mContext);
            }
        });
    }

    public static void updateNeuro(final Context mContext, RelativeLayout rootLayout, View selectedCS) {

        final Spinner sensation,behavioural,condition,other,misc;
        Button submit;
        final EditText comment;

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

        //init button
        submit=selectedCS.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Collect Response
                String sensationResp,behaviouralResp,condResp,otherResp,miscResp;
                String commentResp;

                sensationResp=sensation.getSelectedItem().toString();
                behaviouralResp=behavioural.getSelectedItem().toString();
                condResp=condition.getSelectedItem().toString();
                otherResp=other.getSelectedItem().toString();
                miscResp=misc.getSelectedItem().toString();

                commentResp=comment.getText().toString();

                Map<String,String> map=new HashMap<>();
                map.put("Sensational Abnormality",sensationResp);
                map.put("Conditional Abnormality",condResp);
                map.put("Behavioural Abnormality",behaviouralResp);
                map.put("Other",otherResp);
                map.put("Miscellaneous",miscResp);
                map.put("Comment",commentResp);

                sendResponse(mContext);
            }
        });


    }

    public static void updateGeneral(final Context mContext, RelativeLayout rootLayout, View selectedCS) {

        final EditText problem,accident,fever,comment;
        final Spinner habit,habitat,emotional,pain,site,vomit;
        Button upload,submit;

        final AppCompatActivity activity=(AppCompatActivity)mContext;

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collect data
                /*String habitResp,habitatResp,emotionalResp,painResp,vomitResp;
                String commentResp,problemResp,accResp,feverResp;

                habitResp=habit.getSelectedItem().toString();
                habitatResp=habitat.getSelectedItem().toString();
                emotionalResp=emotional.getSelectedItem().toString();
                painResp=pain.getSelectedItem().toString();
                vomitResp=vomit.getSelectedItem().toString();

                commentResp=comment.getText().toString();
                problemResp=problem.getText().toString();
                accResp=accident.getText().toString();
                feverResp=fever.getText().toString();

                //put in map
                Map<String,String> map=new HashMap<>();
                map.put("Habit",habitResp);
                map.put("Habitat",habitatResp);
                map.put("Emotional Status",emotionalResp);
                map.put("Pain",painResp);
                map.put("Vomiting",vomitResp);
                map.put("Comment",commentResp);
                map.put("Problem",problemResp);
                map.put("Accident",accResp);
                map.put("Fever",feverResp);

                sendResponse(map,mContext);*/
                //activity.startActivity(new Intent(activity,DoctorListActivity.class));
                sendResponse(mContext);
            }
        });


    }
    public void uploadReport(Button upload, final AppCompatActivity activity)
    {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                activity.startActivityForResult(i,IMG);
            }
        });
    }
    private static void sendResponse( final Context mContext) {

        final String fee,dept;
        final DoctorAppointmentActivity activity = (DoctorAppointmentActivity) mContext;

        //get filters
        fee=activity.getFeeStr();
        dept=activity.getDepartment();

        //start list activity
        Intent i = new Intent(activity, DoctorListActivity.class);
        i.putExtra("Fee",fee);
        i.putExtra("Department",dept);
        activity.startActivity(i);
        activity.finish();

    }

}
