package com.example.webonise.assetmanager.Activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.webonise.assetmanager.Adapter.FilterCategoryAdapter;
import com.example.webonise.assetmanager.Adapter.FilterSubcategoryAdapter;
import com.example.webonise.assetmanager.Listener.RecyclerItemListener;
import com.example.webonise.assetmanager.Model.Category;
import com.example.webonise.assetmanager.Model.DeviceDetail;
import com.example.webonise.assetmanager.Model.Filter;
import com.example.webonise.assetmanager.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.progress;

public class FilterActivity extends AppCompatActivity {

    private CheckBox checkBox;
    View.OnClickListener checkBoxListener;
    private RecyclerView recyclerView, recyclerViewSubCategory;
    private FilterCategoryAdapter filterCategoryAdapter;
    private FilterSubcategoryAdapter filterSubcategoryAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<String> typeList, typeDeviceList, typeBrandList, typeStatusList;
    private String[] typeArray, filterDevice, filterBrand, filterStatus;
    private List<Filter> filterListDevice;
    private List<Filter> filterListBrand;
    private List<Category> categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        addToRecyclerView();
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }



    private void addToRecyclerView() {
        typeArray = getResources().getStringArray(R.array.filterCategory);
        filterDevice = getResources().getStringArray(R.array.filterDevice);
        filterBrand = getResources().getStringArray(R.array.filterBrand);
        filterStatus = getResources().getStringArray(R.array.filterStatus);

        typeList = Arrays.asList(typeArray);
        typeDeviceList = Arrays.asList(filterDevice);
        typeBrandList = Arrays.asList(filterBrand);
        typeStatusList = Arrays.asList(filterStatus);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerCategory);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        filterCategoryAdapter = new FilterCategoryAdapter(getCategories(typeList), getApplicationContext());
        recyclerView.setAdapter(filterCategoryAdapter);

        recyclerViewSubCategory = (RecyclerView) findViewById(R.id.recyclerSubCategory);

        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), new RecyclerItemListener.ClickListener() {
            public void onClick(View view, int position) {

                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerViewSubCategory.setLayoutManager(layoutManager);
                if (position == 0) {
                    filterSubcategoryAdapter = new FilterSubcategoryAdapter(categories.get(position).getFilters(), getApplicationContext());
                    recyclerViewSubCategory.setAdapter(filterSubcategoryAdapter);
                } else if (position == 1) {
                    filterSubcategoryAdapter = new FilterSubcategoryAdapter(categories.get(position).getFilters(), getApplicationContext());
                    recyclerViewSubCategory.setAdapter(filterSubcategoryAdapter);
                } else if (position == 2) {
                    filterSubcategoryAdapter = new FilterSubcategoryAdapter(categories.get(position).getFilters(), getApplicationContext());
                    recyclerViewSubCategory.setAdapter(filterSubcategoryAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Another category", Toast.LENGTH_LONG).show();
                }
            }
        }));
    }

    private List<Category> getCategories(List<String> typeList) {
        categories = new ArrayList<>();
        Category category;
        List<List<String>> filterData = new ArrayList<>();
        for (int i = 0; i < typeList.size(); i++) {
            filterData.add(typeDeviceList);
            filterData.add(typeBrandList);
            filterData.add(typeStatusList);
        }
        for (int i = 0; i < typeList.size(); i++) {
            category = new Category();
            List<Filter> filters = getFilterList(filterData.get(i));
            category.setFilters(filters);
            category.setCategoryName(typeList.get(i));
            categories.add(category);
        }
        return categories;
    }

   /* private List<Filter> getStatusType(List<String> typeStatusList) {
        List<Filter> filterList = new ArrayList<>();
        for (int i = 0; i < typeStatusList.size(); i++) {
            filterList.add(new Filter(typeStatusList.get(i)));
        }
        return filterList;
    }

    private List<Filter> getBrandType(List<String> typeBrandList) {
        filterListBrand = new ArrayList<>();
        for (int i = 0; i < typeBrandList.size(); i++) {
            filterListBrand.add(new Filter(typeBrandList.get(i)));
        }
        return filterListBrand;
    }*/

    private List<Filter> getFilterList(List<String> typeDeviceList) {
        filterListDevice = new ArrayList<>();
        for (int i = 0; i < typeDeviceList.size(); i++) {
            filterListDevice.add(new Filter(typeDeviceList.get(i)));
        }
        return filterListDevice;
    }

//
//
//    private void onCheckboxClick() {
//        checkBox=(CheckBox)findViewById(R.id.checkBox);
//        switch (checkBox.getId()){
//            case 1:
//                Toast.makeText(getApplicationContext(),"Checked",Toast.LENGTH_LONG).show();
//        }
//    }
//

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
