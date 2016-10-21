package com.example.webonise.assetmanager.WebService;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.webonise.assetmanager.Model.ApplicationConfig;

/**
 * Created by webonise on 20/10/16.
 */

public class JsonVolleyClass extends ApplicationConfig {
    private static JsonVolleyClass jsonVolley=null;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        jsonVolley=this;
    }

    public static JsonVolleyClass getJsonVolley() {
        return jsonVolley;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }
}
