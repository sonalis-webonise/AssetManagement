package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.webonise.assetmanager.Adapter.DeviceListBaseAdapter;
import com.example.webonise.assetmanager.Model.DeviceTypeListData;
import com.example.webonise.assetmanager.R;

import java.util.ArrayList;
import java.util.List;

public class AssetTypeSelectorActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Class> screenList = new ArrayList<>();

    {
        screenList.add(DesktopSpecificActivity.class);
        screenList.add(DesktopSpecificActivity.class);//For Laptop
        screenList.add(DesktopSpecificActivity.class);//For MacBook
        screenList.add(DesktopSpecificActivity.class);//For IMac
        screenList.add(DesktopSpecificActivity.class);//For MacMini
        screenList.add(DongleActivity.class);
        screenList.add(ConnectorCommonActivity.class);
        screenList.add(MouseActivity.class);
        screenList.add(ConnectorCommonActivity.class);//For Headphone
        screenList.add(MouseActivity.class);//For Keyboard
        screenList.add(ConnectorCommonActivity.class);//For Monitor
        screenList.add(ConnectorCommonActivity.class);//For Router
        screenList.add(DesktopSpecificActivity.class);//For Mobile
    }

    private ListView deviceDetail;
    private Integer[] image;
    private String[] device;
    private ArrayList deviceDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_type_selector);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initButtonViews();
    }

    private void initButtonViews() {

        deviceDataList = new ArrayList();
        deviceDetail = (ListView) findViewById(R.id.deviceList);
        device = getResources().getStringArray(R.array.deviceType);
        image = new Integer[]{R.drawable.desktop, R.drawable.laptop, R.drawable.macbook, R.drawable.imac, R.drawable.mac_mini, R.drawable.dongle, R.drawable.hdmi, R.drawable.mouse, R.drawable.headphone, R.drawable.keyboard, R.drawable.monitor, R.drawable.router, R.drawable.mobile};
        getDataInList();
        deviceDetail.setAdapter(new DeviceListBaseAdapter(AssetTypeSelectorActivity.this, deviceDataList));
    }

    private void getDataInList() {
        for (int i = 0; i < device.length; i++) {
            DeviceTypeListData data = new DeviceTypeListData();
            data.setDeviceName(device[i]);
            data.setImageName(image[i]);
            deviceDataList.add(data);
        }
        deviceDetail.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gotoScreen(position);
    }

    private void gotoScreen(int position) {
        Intent intent = new Intent(this, screenList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
