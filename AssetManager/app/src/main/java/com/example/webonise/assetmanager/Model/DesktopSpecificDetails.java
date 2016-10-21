package com.example.webonise.assetmanager.Model;

public class DesktopSpecificDetails extends CommonAssetDetailModel {
    private String processor;
    private String ramTotalSize;
    private String ramSize;
    private String ramCount;
    private String hdd;
    private String macWifi;
    private String macLan;
    private String operatingSystem;
    private String activationKey;
    private String osVersion;

    public DesktopSpecificDetails() {
        super();
    }

    public String getRamTotalSize() {
        return ramTotalSize;
    }

    public void setRamTotalSize(String ramTotalSize) {
        this.ramTotalSize = ramTotalSize;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getRamCount() {
        return ramCount;
    }

    public void setRamCount(String ramCount) {
        this.ramCount = ramCount;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getMacWifi() {
        return macWifi;
    }

    public void setMacWifi(String macWifi) {
        this.macWifi = macWifi;
    }

    public String getMacLan() {
        return macLan;
    }

    public void setMacLan(String macLan) {
        this.macLan = macLan;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
