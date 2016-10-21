package com.example.webonise.assetmanager.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ResponseData extends RealmObject {
    private RealmList<DeviceDetail> data;

    public RealmList<DeviceDetail> getData() {
        return data;
    }

    public void setData(RealmList<DeviceDetail> data) {
        this.data = data;
    }
}
