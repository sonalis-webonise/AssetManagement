package com.example.webonise.assetmanager.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.webonise.assetmanager.R;

public class AssignActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView txtAssignDate;
    int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        initViews();
    }

    private void initViews() {
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        txtAssignDate = (TextView) findViewById(R.id.txtAssignDate);

        txtAssignDate.append("\t" + currentDate());
    }

    private String currentDate() {
        StringBuilder currentDate = new StringBuilder();
        month = datePicker.getMonth() + 1;
        currentDate.append("\t" + month + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
        return currentDate.toString();
    }
}
