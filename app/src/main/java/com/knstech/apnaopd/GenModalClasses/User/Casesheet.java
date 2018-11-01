
package com.knstech.apnaopd.GenModalClasses.User;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Casesheet {


    private String department;
    private String pulse;

    private String rhythm;

    private String neckveins;
    private String chestpain;
    private String respiration;
    private String cardioCondition;

    private String generalSurvery;

    private String genitoUrinary;

    private String genitoOther;

    private String eyeCondition;
    private String eyeOther;
    private String eyeMisc;
    private String earCondition;
    private String earOther;
    private String earMisc;
    private String neuroCondition;
    private String neuroOther;
    private String neuroMisc;
    private String behaviour;
    private String sensationAbnormality;
    private String habit;

    private String habitat;
    private String emotionalStatus;

    private String pain;

    private String siteOfProblem;

    private String vomiting;

    private String problemString;

    private String accident;

    private String fever;

    private String comment;

    public static List<Casesheet> parseFromJson(String json)
    {
        List<Casesheet> list=new ArrayList<>();
        try {
            Gson gson=new Gson();
            JSONArray array=new JSONArray(json);
            for(int i=0;i<array.length();i++)
            {
                Casesheet casesheet=gson.fromJson(array.get(i).toString(),Casesheet.class);
                list.add(casesheet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getRhythm() {
        return rhythm;
    }

    public void setRhythm(String rhythm) {
        this.rhythm = rhythm;
    }

    public String getNeckveins() {
        return neckveins;
    }

    public void setNeckveins(String neckveins) {
        this.neckveins = neckveins;
    }

    public String getChestpain() {
        return chestpain;
    }

    public void setChestpain(String chestpain) {
        this.chestpain = chestpain;
    }

    public String getRespiration() {
        return respiration;
    }

    public void setRespiration(String respiration) {
        this.respiration = respiration;
    }

    public String getCardioCondition() {
        return cardioCondition;
    }

    public void setCardioCondition(String cardioCondition) {
        this.cardioCondition = cardioCondition;
    }

    public String getGeneralSurvery() {
        return generalSurvery;
    }

    public void setGeneralSurvery(String generalSurvery) {
        this.generalSurvery = generalSurvery;
    }

    public String getGenitoUrinary() {
        return genitoUrinary;
    }

    public void setGenitoUrinary(String genitoUrinary) {
        this.genitoUrinary = genitoUrinary;
    }

    public String getGenitoOther() {
        return genitoOther;
    }

    public void setGenitoOther(String genitoOther) {
        this.genitoOther = genitoOther;
    }

    public String getEyeCondition() {
        return eyeCondition;
    }

    public void setEyeCondition(String eyeCondition) {
        this.eyeCondition = eyeCondition;
    }

    public String getEyeOther() {
        return eyeOther;
    }

    public void setEyeOther(String eyeOther) {
        this.eyeOther = eyeOther;
    }

    public String getEyeMisc() {
        return eyeMisc;
    }

    public void setEyeMisc(String eyeMisc) {
        this.eyeMisc = eyeMisc;
    }

    public String getEarCondition() {
        return earCondition;
    }

    public void setEarCondition(String earCondition) {
        this.earCondition = earCondition;
    }

    public String getEarOther() {
        return earOther;
    }

    public void setEarOther(String earOther) {
        this.earOther = earOther;
    }

    public String getEarMisc() {
        return earMisc;
    }

    public void setEarMisc(String earMisc) {
        this.earMisc = earMisc;
    }

    public String getNeuroCondition() {
        return neuroCondition;
    }

    public void setNeuroCondition(String neuroCondition) {
        this.neuroCondition = neuroCondition;
    }

    public String getNeuroOther() {
        return neuroOther;
    }

    public void setNeuroOther(String neuroOther) {
        this.neuroOther = neuroOther;
    }

    public String getNeuroMisc() {
        return neuroMisc;
    }

    public void setNeuroMisc(String neuroMisc) {
        this.neuroMisc = neuroMisc;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getSensationAbnormality() {
        return sensationAbnormality;
    }

    public void setSensationAbnormality(String sensationAbnormality) {
        this.sensationAbnormality = sensationAbnormality;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getEmotionalStatus() {
        return emotionalStatus;
    }

    public void setEmotionalStatus(String emotionalStatus) {
        this.emotionalStatus = emotionalStatus;
    }

    public String getPain() {
        return pain;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }

    public String getSiteOfProblem() {
        return siteOfProblem;
    }

    public void setSiteOfProblem(String siteOfProblem) {
        this.siteOfProblem = siteOfProblem;
    }

    public String getVomiting() {
        return vomiting;
    }

    public void setVomiting(String vomiting) {
        this.vomiting = vomiting;
    }

    public String getProblemString() {
        return problemString;
    }

    public void setProblemString(String problemString) {
        this.problemString = problemString;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
