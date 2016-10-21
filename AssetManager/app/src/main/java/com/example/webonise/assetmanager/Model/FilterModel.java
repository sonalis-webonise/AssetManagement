package com.example.webonise.assetmanager.Model;

public class FilterModel {
    private boolean isSelected;
    private String name;

    public FilterModel() {
    }

    public FilterModel(String name) {
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
