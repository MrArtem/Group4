package com.exadel.training.controller.model.Omission;

/**
 * Created by asd on 28.07.2015.
 */
public class OmissionADDModel {

    private String userLogin;

    private String trainingName;

    private String date;

    private boolean isOmission;

    private String reason;

    public OmissionADDModel() {
    }

    public OmissionADDModel(String userLogin, String trainingName, String date, boolean isOmission, String reason) {
        this.userLogin = userLogin;
        this.trainingName = trainingName;
        this.date = date;
        this.isOmission = isOmission;
        this.reason = reason;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOmission() {
        return isOmission;
    }

    public void setIsOmission(boolean isOmission) {
        this.isOmission = isOmission;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
