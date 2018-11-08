package com.knstech.apnaopd.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.knstech.apnaopd.AppUtils;
import com.knstech.apnaopd.GenModalClasses.User.UserAuth;
import com.knstech.apnaopd.Patient.DoctorAppointmentActivity;
import com.knstech.apnaopd.Patient.DoctorListActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Utils.Connections.RequestPut;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UIUpdater {
    public static final int IMG = 1;
    private static String url= AppUtils.HOST_ADDRESS+"/api/casesheets/"+ UserAuth.getmUser().getGid();
    private static String fee;
    private static String dept;
    private static String commentResp;

    public static void updateCardio(final Context mContext, RelativeLayout rootLayout, View selectedCS, JSONObject object) {
        final EditText pulse,comment,title;
        final Spinner neckVeins,chestPain,respiration,rhythm,bleeding,condition;
        Button fileUpload,submit;

        try {

            AppCompatActivity activity=(AppCompatActivity)mContext;

            //update view
            rootLayout.removeView(selectedCS);
            selectedCS= LayoutInflater.from(activity).inflate(R.layout.cardio_cs,null);
            rootLayout.addView(selectedCS);

            //init editText
            pulse=selectedCS.findViewById(R.id.pulse);
            comment=selectedCS.findViewById(R.id.comment);
            title=selectedCS.findViewById(R.id.title);

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

            String bleedAr[]=getArrayFromJSON(object.getJSONArray("bleed_cardio"));
            String rhythmAr[]=getArrayFromJSON(object.getJSONArray("rhythm_cardio"));
            String chestPainAr[]=getArrayFromJSON(object.getJSONArray("chest_cardio"));
            String respAr[]=getArrayFromJSON(object.getJSONArray("resp_cardio"));
            String neckAr[]=getArrayFromJSON(object.getJSONArray("neck_cardio"));
            String conditionAr[]=getArrayFromJSON(object.getJSONArray("condition_cardio"));
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
                    String pulseResp,titleResp;

                    bleedResp=""+bleeding.getSelectedItemPosition();
                    rhythmResp=""+rhythm.getSelectedItemPosition();
                    chestResp=""+chestPain.getSelectedItemPosition();
                    respResp=""+respiration.getSelectedItemPosition();
                    neckResp=""+neckVeins.getSelectedItemPosition();
                    condResp=""+condition.getSelectedItemPosition();

                    pulseResp=pulse.getText().toString();
                    commentResp=comment.getText().toString();
                    titleResp=title.getText().toString();

                    //put response in map
                    Map<String,String> map=new HashMap<>();
                    map.put("bleeding",bleedResp);
                    map.put("chestpain",chestResp);
                    map.put("rhythm",rhythmResp);
                    map.put("respiration",respResp);
                    map.put("neckveins",neckResp);
                    map.put("cardio_condition",condResp);
                    map.put("pulse",pulseResp);
                    map.put("comment",commentResp);
                    map.put("title",titleResp);

                    sendResponse(map, mContext);
                }
            });





        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private static String[] getArrayFromJSON(JSONArray array) {
        String ar[]=new String[array.length()];

        for(int i=0;i<ar.length;i++)
        {
            try {
                JSONObject obj=array.getJSONObject(i);
                ar[i]=obj.getString("title");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return ar;
    }

    public static void updateEar(final Context mContext, RelativeLayout rootLayout, View selectedCS, JSONObject object) {

        final Spinner condition,other,misc;
        Button upload,submit;
        final EditText comment,title;

        try {



            AppCompatActivity activity=(AppCompatActivity)mContext;

            //update view
            rootLayout.removeView(selectedCS);
            selectedCS= LayoutInflater.from(activity).inflate(R.layout.ear_cs,null);
            rootLayout.addView(selectedCS);

            //init editText
            comment=selectedCS.findViewById(R.id.comment);
            title=selectedCS.findViewById(R.id.title);

            //init spinner
            condition=selectedCS.findViewById(R.id.condition);
            other=selectedCS.findViewById(R.id.other);
            misc=selectedCS.findViewById(R.id.misc);

            //init button
            upload=selectedCS.findViewById(R.id.upload);
            submit=selectedCS.findViewById(R.id.submit);

            //init string arrays
            String conditionAr[]=getArrayFromJSON(object.getJSONArray("condition_ear"));
            String otherAr[]=getArrayFromJSON(object.getJSONArray("other_ear"));
            String miscAr[]=getArrayFromJSON(object.getJSONArray("misc_ear"));

            AdapterUtil.setSpinnerAdapter(condition,conditionAr,activity);
            AdapterUtil.setSpinnerAdapter(other,otherAr,activity);
            AdapterUtil.setSpinnerAdapter(misc,miscAr,activity);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //collect Response
                    String condResp,otherResp,miscResp;
                    String titleResp;

                    condResp=""+condition.getSelectedItemPosition();
                    otherResp=""+other.getSelectedItemPosition();
                    miscResp=""+misc.getSelectedItemPosition();

                    commentResp=comment.getText().toString();
                    titleResp=title.getText().toString();


                    Map<String,String> map=new HashMap<>();
                    map.put("ear_condition",condResp);
                    map.put("ear_other",otherResp);
                    map.put("ear_misc",miscResp);
                    map.put("comment",commentResp);
                    map.put("title",titleResp);

                    sendResponse(map, mContext);
                }
            });




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static void updateEye(final Context mContext, RelativeLayout rootLayout, View selectedCS, JSONObject object) {

        final Spinner condition,other,misc;
        final EditText comment,title;
        Button upload,submit;


        try {


            AppCompatActivity activity=(AppCompatActivity)mContext;

            //update view
            rootLayout.removeView(selectedCS);
            selectedCS= LayoutInflater.from(activity).inflate(R.layout.eye_cs,null);
            rootLayout.addView(selectedCS);

            //init editText
            comment=selectedCS.findViewById(R.id.comment);
            title=selectedCS.findViewById(R.id.title);

            //init spinner
            condition=selectedCS.findViewById(R.id.condition);
            other=selectedCS.findViewById(R.id.other);
            misc=selectedCS.findViewById(R.id.misc);

            //init button
            upload=selectedCS.findViewById(R.id.upload);
            submit=selectedCS.findViewById(R.id.submit);

            //init arrays
            String conditionAr[]=getArrayFromJSON(object.getJSONArray("condition_eye"));
            String otherAr[]=getArrayFromJSON(object.getJSONArray("other_eye"));
            String miscAr[]=getArrayFromJSON(object.getJSONArray("misc_eye"));

            //set adapter
            AdapterUtil.setSpinnerAdapter(condition,conditionAr,activity);
            AdapterUtil.setSpinnerAdapter(other,otherAr,activity);
            AdapterUtil.setSpinnerAdapter(misc,miscAr,activity);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //collect Response
                    String condResp,otherResp,miscResp;
                    String titleResp;

                    condResp=""+condition.getSelectedItemPosition();
                    otherResp=""+other.getSelectedItemPosition();
                    miscResp=""+misc.getSelectedItemPosition();

                    commentResp=comment.getText().toString();
                    titleResp=title.getText().toString();

                    Map<String,String> map=new HashMap<>();
                    map.put("eye_condition",condResp);
                    map.put("eye_other",otherResp);
                    map.put("eye_misc",miscResp);
                    map.put("comment",commentResp);
                    map.put("title",titleResp);

                    sendResponse(map, mContext);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static void updateGenito(final Context mContext, RelativeLayout rootLayout, View selectedCS, JSONObject object) {

        final Spinner survey,genito,other;
        Button upload,submit;
        final EditText comment,title;

        try {


            AppCompatActivity activity=(AppCompatActivity)mContext;

            //update view
            rootLayout.removeView(selectedCS);
            selectedCS= LayoutInflater.from(activity).inflate(R.layout.genito_cs,null);
            rootLayout.addView(selectedCS);


            //init editText
            comment=selectedCS.findViewById(R.id.comment);
            title=selectedCS.findViewById(R.id.title);

            //init spinner
            survey=selectedCS.findViewById(R.id.survey);
            genito=selectedCS.findViewById(R.id.genitourinary);
            other=selectedCS.findViewById(R.id.other);

            //init button
            upload=selectedCS.findViewById(R.id.upload);
            submit=selectedCS.findViewById(R.id.submit);

            //init array
            String surveyAr[]=getArrayFromJSON(object.getJSONArray("general_genito"));
            String genitoAr[]=getArrayFromJSON(object.getJSONArray("genito_genito"));
            String otherAr[]=getArrayFromJSON(object.getJSONArray("other_genito"));

            //set adapter
            AdapterUtil.setSpinnerAdapter(survey,surveyAr,activity);
            AdapterUtil.setSpinnerAdapter(genito,genitoAr,activity);
            AdapterUtil.setSpinnerAdapter(other,otherAr,activity);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //collect response
                    String surveyResp,genitoResp,otherResp;
                    String titleResp;

                    surveyResp=""+survey.getSelectedItemPosition();
                    genitoResp=""+genito.getSelectedItemPosition();
                    otherResp=""+other.getSelectedItemPosition();

                    commentResp=comment.getText().toString();
                    titleResp=title.getText().toString();

                    Map<String,String> map=new HashMap<>();
                    map.put("general_survey",surveyResp);
                    map.put("genito_urinary",genitoResp);
                    map.put("genito_other",otherResp);
                    map.put("comment",commentResp);
                    map.put("title",titleResp);

                    sendResponse(map, mContext);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static void updateNeuro(final Context mContext, RelativeLayout rootLayout, View selectedCS, JSONObject object) {

        final Spinner sensation,behavioural,condition,other,misc;
        Button submit;
        final EditText comment,title;


        try {



            AppCompatActivity activity=(AppCompatActivity)mContext;

            //update view
            rootLayout.removeView(selectedCS);
            selectedCS= LayoutInflater.from(activity).inflate(R.layout.neuro_cs,null);
            rootLayout.addView(selectedCS);


            //init editText
            comment=selectedCS.findViewById(R.id.comment);
            title=selectedCS.findViewById(R.id.title);

            //init spinner
            sensation=selectedCS.findViewById(R.id.sensation);
            condition=selectedCS.findViewById(R.id.condition);
            behavioural=selectedCS.findViewById(R.id.behavioural);
            other=selectedCS.findViewById(R.id.other);
            misc=selectedCS.findViewById(R.id.misc);

            //init array
            String sensationAr[]=getArrayFromJSON(object.getJSONArray("sensation_neuro"));
            String behaviouralAr[]=getArrayFromJSON(object.getJSONArray("behavioural_neuro"));
            String conditionAr[]=getArrayFromJSON(object.getJSONArray("condition_neuro"));
            String otherAr[]=getArrayFromJSON(object.getJSONArray("other_neuro"));
            String miscAr[]=getArrayFromJSON(object.getJSONArray("misc_neuro"));

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
                    String titleResp;

                    sensationResp=""+sensation.getSelectedItemPosition();
                    behaviouralResp=""+behavioural.getSelectedItemPosition();
                    condResp=""+condition.getSelectedItemPosition();
                    otherResp=""+other.getSelectedItemPosition();
                    miscResp=""+misc.getSelectedItemPosition();

                    commentResp=comment.getText().toString();
                    titleResp=title.getText().toString();

                    Map<String,String> map=new HashMap<>();
                    map.put("sensation_abnormality",sensationResp);
                    map.put("neuro_conditional",condResp);
                    map.put("behaviour",behaviouralResp);
                    map.put("neuro_other",otherResp);
                    map.put("neuro_misc",miscResp);
                    map.put("comment",commentResp);
                    map.put("title",titleResp);

                    sendResponse(map, mContext);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    public static void updateGeneral(final Context mContext, RelativeLayout rootLayout, View selectedCS, JSONObject object) {

        final EditText problem,accident,fever,comment,title;
        final Spinner habit,habitat,emotional,pain,site,vomit;
        Button upload,submit;

        try {



            final AppCompatActivity activity=(AppCompatActivity)mContext;

            //update view
            rootLayout.removeView(selectedCS);
            selectedCS= LayoutInflater.from(activity).inflate(R.layout.general_cs,null);
            rootLayout.addView(selectedCS);


            //init editText
            comment=selectedCS.findViewById(R.id.comment);
            problem=selectedCS.findViewById(R.id.problem);
            accident=selectedCS.findViewById(R.id.accident);
            fever=selectedCS.findViewById(R.id.fever);
            title=selectedCS.findViewById(R.id.title);

            //init button
            upload=selectedCS.findViewById(R.id.upload);
            submit=selectedCS.findViewById(R.id.submit);

            //init spinner
            habit=selectedCS.findViewById(R.id.habit);
            habitat=selectedCS.findViewById(R.id.habitat);
            emotional=selectedCS.findViewById(R.id.emotional);
            pain=selectedCS.findViewById(R.id.pain);
            vomit=selectedCS.findViewById(R.id.vomit);
            site=selectedCS.findViewById(R.id.site);

            //init array
            String habitAr[]=getArrayFromJSON(object.getJSONArray("habit_gen"));
            String habitatAr[]=getArrayFromJSON(object.getJSONArray("habitat_gen"));
            String emotionalAr[]=getArrayFromJSON(object.getJSONArray("emotional_gen"));
            String painAr[]=getArrayFromJSON(object.getJSONArray("pain_gen"));
            String vomitAr[]=getArrayFromJSON(object.getJSONArray("vomit_gen"));
            String siteAr[]=getArrayFromJSON(object.getJSONArray("site_gen"));

            //set adapter
            AdapterUtil.setSpinnerAdapter(habit,habitAr,activity);
            AdapterUtil.setSpinnerAdapter(habitat,habitatAr,activity);
            AdapterUtil.setSpinnerAdapter(emotional,emotionalAr,activity);
            AdapterUtil.setSpinnerAdapter(pain,painAr,activity);
            AdapterUtil.setSpinnerAdapter(vomit,vomitAr,activity);
            AdapterUtil.setSpinnerAdapter(site,siteAr,activity);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //collect data
                String habitResp,habitatResp,emotionalResp,painResp,vomitResp,siteResp;
                String problemResp,accResp,feverResp,titleResp;

                habitResp=""+habit.getSelectedItemPosition();
                habitatResp=""+habitat.getSelectedItemPosition();
                emotionalResp=""+emotional.getSelectedItemPosition();
                painResp=""+pain.getSelectedItemPosition();
                vomitResp=""+vomit.getSelectedItemPosition();
                siteResp=""+site.getSelectedItemPosition();
                titleResp=title.getText().toString();

                commentResp=comment.getText().toString();
                problemResp=problem.getText().toString();
                accResp=accident.getText().toString();
                feverResp=fever.getText().toString();

                //put in map
                Map<String,String> map=new HashMap<>();
                map.put("habit",habitResp);
                map.put("habitat",habitatResp);
                map.put("emotional_status",emotionalResp);
                map.put("pain",painResp);
                map.put("vomiting",vomitResp);
                map.put("comment",commentResp);
                map.put("problem_string",problemResp);
                map.put("site_of_problem",siteResp);
                map.put("accident",accResp);
                map.put("fever",feverResp);
                map.put("title",titleResp);

                sendResponse(map,mContext);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }




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
    private static void sendResponse(Map<String, String> map, final Context mContext) {

        final DoctorAppointmentActivity activity = (DoctorAppointmentActivity) mContext;
        map.put("department",""+activity.getChoice());
        RequestPut requestPost=new RequestPut(mContext);
        Map map1=new HashMap();
        map1.put("casesheet",map);
        JSONObject jsonObject=new JSONObject(map1);
        requestPost.putJSONObject(url, jsonObject, new RequestPut.JSONObjectResponseListener() {
            @Override
            public void onResponse(JSONObject object) {
                try {

                    String cs_uid=object.getString("_id");
                    //get filters
                    fee=activity.getFeeStr();
                    dept=activity.getDepartment();

                    //start list activity
                    Intent i = new Intent(activity, DoctorListActivity.class);
                    i.putExtra("Fee",fee);
                    i.putExtra("Department",dept);
                    i.putExtra("cs_uid",cs_uid);
                    i.putExtra("comment",commentResp);
                    activity.startActivity(i);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

}
