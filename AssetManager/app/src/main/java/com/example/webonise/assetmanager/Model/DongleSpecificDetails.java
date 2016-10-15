package com.example.webonise.assetmanager.Model;

/**
 * Created by webonise on 13/10/16.
 */

public class DongleSpecificDetails extends CommonAssetDetails {
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
