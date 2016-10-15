package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.webonise.assetmanager.R;

public class DesktopSpecificActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button btnAssign;
    private Spinner spinnerOSType;
    private TextView textView;
    private String[] osArray;
    private String[] osVersion;
    private ListView osList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews() {
        spinnerOSType = (Spinner) findViewById(R.id.spinnerOSType);
        btnAssign = (Button) findViewById(R.id.btnAssign);
        textView = (TextView) findViewById(R.id.editOsKey);
        osArray = getResources().getStringArray(R.array.osType);

        spinnerOSType.setOnItemSelectedListener(this);
        btnAssign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AssignActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String osType = spinnerOSType.getSelectedItem().toString();
        if (osType.equalsIgnoreCase("windows")) {
            textView.setVisibility(View.VISIBLE);

        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
