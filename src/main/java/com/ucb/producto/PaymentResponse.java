package com.ucb.producto;

public class PaymentResponse {
    private int statusCode;
    private String message;

    public PaymentResponse() {
        this.statusCode = 0;
        this.message = "successful";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
