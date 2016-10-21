package com.example.webonise.assetmanager.Model;

import java.util.List;

public class DataModel {
    private String assetId,deviceType,tag,brand,serialNumber,owner,ownerName,status,notes;
    private List<AdditionalInfo> additionalInfoList;

    public String getAssetId() {

        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<AdditionalInfo> getAdditionalInfoList() {
        return additionalInfoList;
    }

    public void setAdditionalInfoList(List<AdditionalInfo> additionalInfoList) {
        this.additionalInfoList = additionalInfoList;
    }
}
