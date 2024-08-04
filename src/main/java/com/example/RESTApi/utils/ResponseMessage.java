package com.example.RESTApi.utils;

public class ResponseMessage<T> {
    private boolean error;
    private String message;
    private T data;

    public ResponseMessage(boolean error, String message, T data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(boolean error, String message) {
        this(error, message, null);
    }

    // Getters and Setters
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
