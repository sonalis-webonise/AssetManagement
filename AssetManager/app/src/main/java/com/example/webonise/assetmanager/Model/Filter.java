package com.example.webonise.assetmanager.Model;

/**
 * Created by webonise on 14/10/16.
 */

public class Filter {
    private boolean isSelected;
    private String name;

    public Filter() {
    }

    public Filter(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean setSelected() {
        return false;
    }
}
