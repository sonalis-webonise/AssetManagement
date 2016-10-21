package com.example.webonise.assetmanager.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DeviceDetail extends RealmObject {

    @PrimaryKey
    String assetId;
    private String brand;
    private String tag;
    private int status;
    private String statusValue;
    private AssignmentDetail assignmentDetails;

    public AssignmentDetail getAssignmentDetails() {
        return assignmentDetails;
    }

    public void setAssignmentDetails(AssignmentDetail assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
