package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.webonise.assetmanager.R;

public class AssetTypeSelectorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMonitor, btnKeyboard, btnMouse, btnThinkpad, btnMac, btnCpu, btnCable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_type_selector);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initButtonViews();
    }

    private void initButtonViews() {
        btnMonitor = (Button) findViewById(R.id.btnMonitor);
        btnKeyboard = (Button) findViewById(R.id.btnKeyboard);
        btnMouse = (Button) findViewById(R.id.btnMouse);
        btnThinkpad = (Button) findViewById(R.id.btnThinkpad);
        btnMac = (Button) findViewById(R.id.btnMac);
        btnCpu = (Button) findViewById(R.id.btnCpu);
        btnCable = (Button) findViewById(R.id.btnCable);

        btnMonitor.setOnClickListener(this);
        btnKeyboard.setOnClickListener(this);
        btnMouse.setOnClickListener(this);
        btnThinkpad.setOnClickListener(this);
        btnMac.setOnClickListener(this);
        btnCpu.setOnClickListener(this);
        btnCable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnMonitor:
                Intent intentMonitor = new Intent(this, MonitorActivity.class);
                startActivity(intentMonitor);
                return;
            case R.id.btnKeyboard:
                Intent intentKeyboard = new Intent(this, KeyboardActivity.class);
                startActivity(intentKeyboard);
                return;
            case R.id.btnMouse:
                Intent intentMouse = new Intent(this, MouseActivity.class);
                startActivity(intentMouse);
                return;
            case R.id.btnThinkpad:
                Intent intentThinkpad = new Intent(this, ThinkpadActivity.class);
                startActivity(intentThinkpad);
                return;
            case R.id.btnMac:
                Intent intentMac = new Intent(this, MacActivity.class);
                startActivity(intentMac);
                return;
            case R.id.btnCpu:
                Intent intentCpu = new Intent(this, CPUActivity.class);
                startActivity(intentCpu);
                return;
            case R.id.btnCable:
                Intent intentCable = new Intent(this, CableActivity.class);
                startActivity(intentCable);
                return;
            default:
                return;
        }
    }
}
