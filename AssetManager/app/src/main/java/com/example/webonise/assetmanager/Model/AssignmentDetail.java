package com.example.webonise.assetmanager.Model;

import io.realm.RealmObject;

public class AssignmentDetail extends RealmObject {
    private String emailId;
    private long dateOfAssignment;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getDateOfAssignment() {
        return dateOfAssignment;
    }

    public void setDateOfAssignment(long dateOfAssignment) {
        this.dateOfAssignment = dateOfAssignment;
    }
}
