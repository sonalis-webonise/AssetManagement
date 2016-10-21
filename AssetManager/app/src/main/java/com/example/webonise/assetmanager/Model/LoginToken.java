package com.example.webonise.assetmanager.Model;

public class LoginToken {
    private String token;
    private static LoginToken loginToken;

    public static LoginToken getLoginToken() {
        if (loginToken == null) {
            loginToken = new LoginToken();
        }
        return loginToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
