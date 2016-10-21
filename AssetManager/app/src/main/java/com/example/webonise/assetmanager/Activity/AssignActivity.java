package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webonise.assetmanager.R;

public class AssignActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    public static final String toast = "Fill the Details";
    public static final String toastAssigned = "Asset is assigned to ";
    public static final String webonise = "Webonise Lab";
    public static final String companyWebonise = "weboniselab.com";
    private DatePicker datePicker;
    private TextView txtAssignDate;
    private EditText editAssignEmail;
    private EditText editAssignCompany;
    private TextInputLayout input_layout_assignEmail, input_layout_assignCompany;
    int month;
    private Button btnAssign, btnSetDate;
    private String email;
    private String company;
    private String[] companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        initViews();
    }

    private void initViews() {
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        txtAssignDate = (TextView) findViewById(R.id.txtAssignDate);
        btnAssign = (Button) findViewById(R.id.btnAssign);
        btnSetDate = (Button) findViewById(R.id.btnSetDate);
        editAssignEmail = (EditText) findViewById(R.id.editAssignEmail);
        editAssignCompany = (EditText) findViewById(R.id.editAssignCompany);
        input_layout_assignEmail = (TextInputLayout) findViewById(R.id.input_layout_assignEmail);
        input_layout_assignCompany = (TextInputLayout) findViewById(R.id.input_layout_assignCompany);
        email = editAssignEmail.getText().toString();
        company = editAssignCompany.getText().toString();
        txtAssignDate.setText(currentDate());
        datePicker.setVisibility(View.GONE);
        editAssignEmail.setOnKeyListener(this);
        btnAssign.setOnClickListener(this);
        btnSetDate.setOnClickListener(this);
    }

    private boolean validateAssignee() {

        if (email.trim().isEmpty()) {
            input_layout_assignEmail.setError(getString(R.string.error_assignEmail));
            return false;
        } else {
            input_layout_assignEmail.setErrorEnabled(false);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_layout_assignEmail.setError(getString(R.string.error_validAssignEmail));
            return false;
        } else {
            input_layout_assignEmail.setErrorEnabled(false);
        }
        if (company.trim().isEmpty()) {
            input_layout_assignCompany.setError(getString(R.string.error_assignCompany));
            return false;
        } else {
            input_layout_assignCompany.setErrorEnabled(false);
        }
        return true;
    }

    private String currentDate() {
        StringBuilder currentDate = new StringBuilder();
        month = datePicker.getMonth() + 1;
        currentDate.append("\t" + datePicker.getDayOfMonth() + "/" + month + "/" + datePicker.getYear());
        return currentDate.toString();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        email = editAssignEmail.getText().toString();
        company = editAssignCompany.getText().toString();
        companyName = email.split("@");
        for (int i = 0; i < companyName.length; i++) {
            Log.d("msg", companyName[i]);
        }
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();

            if (companyName[1].equalsIgnoreCase(companyWebonise)) {
                editAssignCompany.setText(webonise);
            }
        } else {
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSetDate:
                datePicker.setVisibility(View.VISIBLE);
                txtAssignDate.setText(currentDate());
                break;
            case R.id.btnAssign:
                if (!validateAssignee()) {
                    Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), toastAssigned + companyName[0], Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, AssetManagerActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
