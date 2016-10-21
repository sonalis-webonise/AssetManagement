package com.example.webonise.assetmanager.Model;

import java.util.List;

public class CategoryModel {
    private List<FilterModel> filters;
    private String categoryName;

    public CategoryModel(String name) {
        this.categoryName=name;
    }

    public CategoryModel() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<FilterModel> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterModel> filters) {
        this.filters = filters;
    }
}
