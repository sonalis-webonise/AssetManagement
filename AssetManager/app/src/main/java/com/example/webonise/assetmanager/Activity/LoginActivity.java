package com.example.webonise.assetmanager.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webonise.assetmanager.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText editEmail, editPassword;
    private TextInputLayout input_layout_loginEmail, input_layout_loginPassword;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

        editEmail.setText("sonali@web.com");
        editPassword.setText("webonise");

        input_layout_loginEmail = (TextInputLayout) findViewById(R.id.input_layout_loginEmail);
        input_layout_loginPassword = (TextInputLayout) findViewById(R.id.input_layout_loginPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        email = editEmail.getText().toString();
        password = editPassword.getText().toString();
        if (!validateUser()) {
            Toast.makeText(getApplicationContext(), R.string.toastMessageLogin, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, AssetManagerActivity.class);
            startActivity(intent);
            editEmail.setText("");
            editPassword.setText("");
        }
    }

    private boolean validateUser() {
        if (email.trim().isEmpty()) {
            input_layout_loginEmail.setError(getString(R.string.error_emailLogin));
            return false;
        } else {
            input_layout_loginEmail.setErrorEnabled(false);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_layout_loginEmail.setError(getString(R.string.invalid_emailLogin));
            return false;
        } else {
            input_layout_loginEmail.setErrorEnabled(false);
        }

        if (password.trim().isEmpty()) {
            input_layout_loginPassword.setError(getString(R.string.error_passwordLogin));
            return false;
        } else {
            input_layout_loginPassword.setErrorEnabled(false);
        }

        if (password.trim().length() < 8) {
            input_layout_loginPassword.setError(getString(R.string.invalid_passwordLogin));
            return false;
        } else {
            input_layout_loginPassword.setErrorEnabled(false);
        }

        return true;
    }
}
