package com.exadel.training.controller.model.Omission;

import com.exadel.training.model.Omission;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 22.07.2015.
 */
public class JournalOmissionByUserLogin {

    private String traingName;
    private String date;
    private Boolean isOmission;

    public JournalOmissionByUserLogin(){
    }

    public String getTraingName() {
        return traingName;
    }

    public void setTraingName(String traingName) {
        this.traingName = traingName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        this.date = sdf.format(date);
    }

    public Boolean getIsOmission() {
        return isOmission;
    }

    public void setIsOmission(Boolean isOmission) {
        this.isOmission = isOmission;
    }

    public static JournalOmissionByUserLogin parseJournalOmissionByUserLogin(Omission omission) {
        JournalOmissionByUserLogin journalOmissionByUserLogin = new JournalOmissionByUserLogin();
        journalOmissionByUserLogin.setDate(omission.getTraining().getDateTime());
        journalOmissionByUserLogin.setIsOmission(omission.isOmission());
        journalOmissionByUserLogin.setTraingName(omission.getTraining().getName());

        return journalOmissionByUserLogin;
    }
}
