package com.example.webonise.assetmanager.Activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.webonise.assetmanager.Adapter.DeviceDetailAdapter;
import com.example.webonise.assetmanager.Model.AssignmentDetail;
import com.example.webonise.assetmanager.Model.DeviceDetail;
import com.example.webonise.assetmanager.Model.LoginToken;
import com.example.webonise.assetmanager.Model.ResponseData;
import com.example.webonise.assetmanager.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AssetManagerActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {

    private List<DeviceDetail> deviceList;
    private List<AssignmentDetail> assignList;
    private RecyclerView recyclerView;
    private DeviceDetailAdapter deviceDetailAdapter;
    public final static String loading = "Loading";
    private ProgressDialog progress;
    Realm realm;
    SearchView searchView;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_manager);
        initViews();

        progress = new ProgressDialog(this);
        progress.setMessage(loading);
        parseJson(data);
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        data = getResources().getString(R.string.dummy_json);

        realm = Realm.getDefaultInstance();
        deviceList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.actionSearch).getActionView();
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
                LoginToken.getLoginToken().setToken("");
                Log.d("msg", LoginToken.getLoginToken().getToken());
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
        deviceDetailAdapter.searchData(newText);
        return true;
    }

    private void parseJson(String data) {
        ResponseData jsonData = new Gson().fromJson(data, ResponseData.class);

        List<DeviceDetail> deviceDetails = jsonData.getData();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(deviceDetails);
        realm.commitTransaction();
        RealmResults<ResponseData> responseData = realm.where(ResponseData.class).findAll();

        deviceList = realm.where(DeviceDetail.class).findAll();
        deviceDetailAdapter = new DeviceDetailAdapter(deviceList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(deviceDetailAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
