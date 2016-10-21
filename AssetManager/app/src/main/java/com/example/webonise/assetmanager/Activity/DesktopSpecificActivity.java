package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webonise.assetmanager.R;

public class DesktopSpecificActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final String toastMessage = "Asset Details Saved Successfully";
    public static final String toastWarning = "Enter Details";
    public static final String windows = "windows";
    private Button btnSaveDevice;
    private Spinner spinnerOSType;
    private TextView textView;
    private EditText editTag, editbrand, editSerial, editOwner;
    private TextInputLayout input_layout_tag, input_layout_brand, input_layout_serialNumber, input_layout_owner;
    private String[] osArray;
    private String[] osVersion;
    private ListView osList;
    private String tag, brand, serial, owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews() {
        editTag = (EditText) findViewById(R.id.editTag);
        editbrand = (EditText) findViewById(R.id.editBrand);
        editSerial = (EditText) findViewById(R.id.editSerial);
        editOwner = (EditText) findViewById(R.id.editOwner);

        input_layout_tag = (TextInputLayout) findViewById(R.id.input_layout_tag);
        input_layout_brand = (TextInputLayout) findViewById(R.id.input_layout_brand);
        input_layout_serialNumber = (TextInputLayout) findViewById(R.id.input_layout_serialNumber);
        input_layout_owner = (TextInputLayout) findViewById(R.id.input_layout_owner);

        spinnerOSType = (Spinner) findViewById(R.id.spinnerOSType);
        btnSaveDevice = (Button) findViewById(R.id.btnSaveDevice);
        textView = (TextView) findViewById(R.id.editOsKey);
        osArray = getResources().getStringArray(R.array.osType);

        spinnerOSType.setOnItemSelectedListener(this);
        btnSaveDevice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!validateData()) {
            Toast.makeText(getApplicationContext(), toastWarning, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AssetManagerActivity.class);
            startActivity(intent);
        }
    }

    private boolean validateData() {
        tag = editTag.getText().toString();
        brand = editbrand.getText().toString();
        serial = editSerial.getText().toString();
        owner = editOwner.getText().toString();
        if (tag.trim().isEmpty()) {
            input_layout_tag.setError(getString(R.string.error_tag));
            return false;
        } else {
            input_layout_tag.setErrorEnabled(false);
        }
        if (brand.trim().isEmpty()) {
            input_layout_brand.setError(getString(R.string.error_brand));
            return false;
        } else {
            input_layout_brand.setErrorEnabled(false);
        }
        if (serial.trim().isEmpty()) {
            input_layout_serialNumber.setError(getString(R.string.error_serial));
            return false;
        } else {
            input_layout_serialNumber.setErrorEnabled(false);
        }
        if (owner.trim().isEmpty()) {
            input_layout_owner.setError(getString(R.string.error_owner));
            return false;
        } else {
            input_layout_owner.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String osType = spinnerOSType.getSelectedItem().toString();
        if (osType.equalsIgnoreCase(windows)) {
            textView.setVisibility(View.VISIBLE);

        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
