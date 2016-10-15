package com.example.webonise.assetmanager.Model;

import java.util.List;

/**
 * Created by webonise on 14/10/16.
 */

public class Category{
    private List<Filter> filters;
    private String categoryName;

    public Category(String name) {
        this.categoryName=name;
    }

    public Category() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
