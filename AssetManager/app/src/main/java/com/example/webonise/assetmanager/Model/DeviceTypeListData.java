package com.example.webonise.assetmanager.Model;

/**
 * Created by webonise on 5/10/16.
 */

/**
 * DeviceTypeListData contains the data for displaying in ListView
 */
public class DeviceTypeListData {
    private String deviceName;
    private int imageName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }
}
