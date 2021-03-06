package com.exadel.training.controller.model.Omission;

import com.exadel.training.model.Omission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 07.08.2015.
 */
public class OmissionGETModel {


    private String reason;

    private boolean isOmission;

    public OmissionGETModel() {
    }

    public OmissionGETModel(String reason, boolean isOmission) {
        this.reason = reason;
        this.isOmission = isOmission;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isOmission() {
        return isOmission;
    }

    public void setIsOmission(boolean isOmission) {
        this.isOmission = isOmission;
    }

    public static OmissionGETModel parseOmission(Omission omission) {
        return new OmissionGETModel(omission.getReason(), omission.isOmission());
    }

    public static List<OmissionGETModel> parseOmissionList(List<Omission> omissions) {
        List<OmissionGETModel> omissionGETModels = new ArrayList<OmissionGETModel>();
        for(Omission omission: omissions) {
            omissionGETModels.add(parseOmission(omission));
        }
        return omissionGETModels;
    }
}
