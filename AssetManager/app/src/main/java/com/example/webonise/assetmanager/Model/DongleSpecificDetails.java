package com.example.webonise.assetmanager.Model;

public class DongleSpecificDetails extends CommonAssetDetailModel {
    private String simBrand;

    public String getSimBrand() {
        return simBrand;
    }

    public void setSimBrand(String simBrand) {
        this.simBrand = simBrand;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    private String simNumber;

    public DongleSpecificDetails() {
        super();
    }
}
