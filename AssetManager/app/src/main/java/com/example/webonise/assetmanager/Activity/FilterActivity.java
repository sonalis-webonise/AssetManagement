package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.webonise.assetmanager.Adapter.FilterCategoryAdapter;
import com.example.webonise.assetmanager.Adapter.FilterSubcategoryAdapter;
import com.example.webonise.assetmanager.Listener.RecyclerItemListener;
import com.example.webonise.assetmanager.Model.CategoryModel;
import com.example.webonise.assetmanager.Model.FilterModel;
import com.example.webonise.assetmanager.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerViewSubCategory;
    private FilterCategoryAdapter filterCategoryAdapter;
    private FilterSubcategoryAdapter filterSubcategoryAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<String> typeList;
    private String[] typeArray;
    private List<CategoryModel> categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        addToRecyclerView();
    }

    private void addToRecyclerView() {
        typeArray = getResources().getStringArray(R.array.filterCategory);
        typeList = Arrays.asList(typeArray);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCategory);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        filterCategoryAdapter = new FilterCategoryAdapter(getCategories(typeList), getApplicationContext());
        recyclerView.setAdapter(filterCategoryAdapter);

        recyclerViewSubCategory = (RecyclerView) findViewById(R.id.recyclerSubCategory);

        onFilterSelection(0);
        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), new RecyclerItemListener.ClickListener() {
            public void onClick(View view, int position) {
                onFilterSelection(position);
            }
        }));
    }

    private void onFilterSelection(int position) {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSubCategory.setLayoutManager(layoutManager);
        filterSubcategoryAdapter = new FilterSubcategoryAdapter(categories.get(position).getFilters(), getApplicationContext());
        recyclerViewSubCategory.setAdapter(filterSubcategoryAdapter);
    }

    private List<CategoryModel> getCategories(List<String> typeList) {
        categories = new ArrayList<>();
        CategoryModel category;
        List<Integer> filterData = new ArrayList<>();
        filterData.add(R.array.filterDevice);
        filterData.add(R.array.filterBrand);
        filterData.add(R.array.filterStatus);

        for (int i = 0; i < typeList.size(); i++) {
            category = new CategoryModel();
            List<FilterModel> filters = getFilterList(filterData.get(i));
            category.setFilters(filters);
            category.setCategoryName(typeList.get(i));
            categories.add(category);
        }
        return categories;
    }

    private List<FilterModel> getFilterList(Integer arrayId) {
        String[] filters = getResources().getStringArray(arrayId);
        List<FilterModel> filterData = new ArrayList<>();
        for (int i = 0; i < filters.length; i++) {
            filterData.add(new FilterModel(filters[i]));
        }
        return filterData;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentAsset = new Intent(this, AssetManagerActivity.class);
        startActivity(intentAsset);
        return super.onOptionsItemSelected(item);
    }
}
