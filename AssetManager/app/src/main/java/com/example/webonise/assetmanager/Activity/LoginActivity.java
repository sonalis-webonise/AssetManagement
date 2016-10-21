package com.example.webonise.assetmanager.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.webonise.assetmanager.Model.LoginToken;
import com.example.webonise.assetmanager.WebService.JsonVolleyClass;
import com.example.webonise.assetmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    private Button btnLogin;
    private EditText editEmail, editPassword;
    private TextInputLayout input_layout_loginEmail, input_layout_loginPassword;
    private String email;
    private String password;
    boolean islogin;
    public final static String toastConnection = "No internet Connection, Please Connect to Network";
    private String url = "http://asset-manager.weboapps.com/api/v1/login";
    JsonObjectRequest jsonRequest;
    private boolean connected;
    JSONObject response;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        sharedPreferences=getSharedPreferences("auth_token",Context.MODE_PRIVATE);
        if (LoginToken.getLoginToken().getToken() != null) {
            Log.d("login", LoginToken.getLoginToken().getToken());
            Intent intent = new Intent(this, AssetManagerActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
        }

        initViews();
    }

    private void initViews() {
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

        editEmail.setText("raj@webonise.com");
        editPassword.setText("webonise");

        input_layout_loginEmail = (TextInputLayout) findViewById(R.id.input_layout_loginEmail);
        input_layout_loginPassword = (TextInputLayout) findViewById(R.id.input_layout_loginPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), toastConnection, Toast.LENGTH_LONG).show();
        } else {
            email = editEmail.getText().toString();
            password = editPassword.getText().toString();
            if (!validateUser()) {
                Toast.makeText(getApplicationContext(), R.string.toastMessageLogin, Toast.LENGTH_LONG).show();
            } else {
                try {
                    sendPostRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendPostRequest() throws JSONException {
        JSONObject jsonUserObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonUserObject.put("user", jsonObject);
        jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonUserObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                saveToken(response);
                Log.d("response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
                Log.d("error", error.toString());
            }
        }) {
            /*@Override
            protected Map<String,String> getParams(){
                Map<String,String> params =new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);
                userMap.put("user",params);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String,Map<String,String>> map=new HashMap<>();
                map.put("user",getParams());
                return super.getBody();
            }
*/
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        JsonVolleyClass.getJsonVolley().addToRequestQueue(jsonRequest);
    }

    private void saveToken(JSONObject response) {
        this.response = response;
        sharedPreferences = getSharedPreferences("auth_token", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        LoginToken.getLoginToken().setToken(response.toString());
        Log.d("set login token", response.toString());
        Log.d("set login token", LoginToken.getLoginToken().getToken());
        editor.putBoolean("isUserLogedIn", true).commit();
        editor.putString("user", response.toString()).commit();
        editor.commit();
        Intent intent = new Intent(this, AssetManagerActivity.class);
        startActivity(intent);
        /*editEmail.setText("");
        editPassword.setText("");*/
        finish();
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
}

/*

    @Override
    protected Map<String,String> getParams(){
        Map<String,Map<String,String>> userMap=new HashMap<String, Map<String, String>>();
        Map<String,String> params =new HashMap<String, String>();
        params.put("email",email);
        params.put("password",password);
        userMap.put("user",params);
        return params;
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Map<String,Map<String,String>> map=new HashMap<>();
        map.put("user",getParams());
        return super.getBody();
    }

*/
/*            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<String, String>();
                headers.put("Accept","application/json");
//                headers.put("Content-Type","application/json");
                return headers;
            }*/
