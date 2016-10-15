package com.example.webonise.assetmanager.Activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.webonise.assetmanager.Adapter.DeviceDetailAdapter;
import com.example.webonise.assetmanager.Model.DeviceDetail;
import com.example.webonise.assetmanager.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssetManagerActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {

    private List<DeviceDetail> deviceList;
    private RecyclerView recyclerView;
    private DeviceDetailAdapter deviceDetailAdapter;
    public final static String toastConnection = "No internet Connection, Please Connect to Network";
    private ProgressDialog progress;
    private boolean connected;
    private JSONObject jsonObject;
    private String data = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "\"assetId\":12,\n" +
            "      \"deviceType\": 1,\n" +
            "      \"deviceName\": \"MacBook\",\n" +
            "      \"tag\": \"WL001\",\n" +
            "      \"brand\": \"MacBook Pro\",\n" +
            "      \"serialNumber\": \"JIG46958VJHV74HJJ38\",\n" +
            "      \"owner\": 1,\n" +
            "      \"ownerName\": \"Webonise\",\n" +
            "      \"status\": 1,\n" +
            "      \"notes\": \"General multiline notes\",\n" +
            "      \"additionalInfo\": {\n" +
            "        \"processor\": \"I3 2330m 2.20GHz\",\n" +
            "        \"ram\": [\n" +
            "          {\n" +
            "            \"capacity\": 8,\n" +
            "            \"quantity\": 1\n" +
            "          },\n" +
            "          {\n" +
            "            \"capacity\": 4,\n" +
            "            \"quantity\": 1\n" +
            "          }\n" +
            "        ],\n" +
            "        \"hdd\": 500,\n" +
            "        \"macWiFi\": \"AC:62:19:4D:3A:12\",\n" +
            "        \"macLan\": \"FC:21:29:D4:A3:35\",\n" +
            "        \"OSType\": 1,\n" +
            "        \"OSName\": \"Mac OS\",\n" +
            "        \"OSVersion\": \"Sierra\"\n" +
            "      },\n" +
            "      \"assignmentDetails\": {\n" +
            "        \"emailId\": \"sandeep.rathore@weboniselab.com\",\n" +
            "        \"userType\": 1,\n" +
            "        \"assignmentType\": 1,\n" +
            "        \"dateOfAssignment\": 1476075116870\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "\"assetId\":13,\n" +
            "      \"deviceType\": 6,\n" +
            "      \"deviceName\": \"Keyboard\",\n" +
            "      \"tag\": \"WL809\",\n" +
            "      \"brand\": \"Logitech\",\n" +
            "      \"serialNumber\": \"H8757JFJHVB865HJH7799\",\n" +
            "      \"owner\": 1,\n" +
            "      \"ownerName\": \"Webonise\",\n" +
            "      \"status\": 1,\n" +
            "      \"notes\": \"General multiline notes\",\n" +
            "      \"additionalInfo\": {\n" +
            "        \"wired\": true\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "\"assetId\":14,\n" +
            "      \"deviceType\": 8,\n" +
            "      \"deviceName\": \"Monitor\",\n" +
            "      \"tag\": \"WL752\",\n" +
            "      \"brand\": \"Dell\",\n" +
            "      \"serialNumber\": \"765VJJHGU768GHIYG76859\",\n" +
            "      \"owner\": 1,\n" +
            "      \"ownerName\": \"Webonise\",\n" +
            "      \"status\": 1,\n" +
            "      \"notes\": \"General multiline notes\",\n" +
            "      \"additionalInfo\": null\n" +
            "    },\n" +
            "    {\n" +
            "\"assetId\":15,\n" +
            "      \"deviceType\": 11,\n" +
            "      \"deviceName\": \"Dongle\",\n" +
            "      \"tag\": \"WL734\",\n" +
            "      \"brand\": \"Micromax\",\n" +
            "      \"serialNumber\": \"AIOEUVCMSH7867BVVJHJAS\",\n" +
            "      \"owner\": 1,\n" +
            "      \"ownerName\": \"Webonise\",\n" +
            "      \"status\": 1,\n" +
            "      \"notes\": \"General multiline notes\",\n" +
            "      \"additionalInfo\": {\n" +
            "        \"simBrand\": \"Vodafone\",\n" +
            "        \"simNumber\": \"123423782736923\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_manager);
        initViews();

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        addDataToView();

    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        deviceList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        deviceDetailAdapter = new DeviceDetailAdapter(deviceList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(deviceDetailAdapter);

       /* recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), new RecyclerItemListener.ClickListener() {
            public void onClick(View view, int position) {
                Intent intentDetail = new Intent(AssetManagerActivity.this, DeviceSpecificationActivity.class);
                startActivity(intentDetail);
            }
        }));*/
//        displayData();
    }

/*    private void displayData() {

        DeviceDetail deviceDetail = new DeviceDetail();
        for (int i = 0; i < 10; i++) {
            deviceDetail.setBrand("Brand");
            deviceDetail.setTag("WL176");
            deviceDetail.setEmailId("Sonali Sulgadle");
            deviceDetail.setDate("01-08-2016");
            deviceDetail.setStatus("Assign");
            deviceList.add(deviceDetail);

            deviceDetailAdapter.notifyDataSetChanged();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.actionSearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionFilter:
                Intent intentFilter = new Intent(this, FilterActivity.class);
                startActivity(intentFilter);
                break;
            case R.id.actionChangePassword:
                Intent intentChangePassword = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentChangePassword);
                break;
            case R.id.actionLogout:
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intentType = new Intent(this, AssetTypeSelectorActivity.class);
        startActivity(intentType);
        return;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }


    private void addDataToView() {
//        progress.show();
        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), toastConnection, Toast.LENGTH_LONG).show();
        } else {
            try {
                jsonObject = new JSONObject(data);
                JSONArray data = jsonObject.getJSONArray("data");
                parseJson(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    private void parseJson(Object response) {
        Gson gson = new Gson();
        DeviceDetail[] deviceDetails = gson.fromJson(response.toString(), DeviceDetail[].class);
        for (DeviceDetail device : deviceDetails) {
            device.setBrand(device.getBrand());
            device.setTag(device.getTag());
            device.setEmailId(device.getEmailId());
            device.setDate(device.getDate());
            if (device.getStatus().equals("1")){
                device.setStatus("Assigned");
            }
            if (device.getStatus().equals("0")){
                device.setStatus("Unassigned");
            }
//            device.setStatus(device.getStatus());
            deviceList.add(device);

            deviceDetailAdapter.notifyDataSetChanged();
        }
    }
}
