package com.ucb.producto;

public class AuthResponse {
    private String token;
    private long timestamp;

    public AuthResponse(String token, long timestamp) {
        this.token = "token";
        this.timestamp = 1515151;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
