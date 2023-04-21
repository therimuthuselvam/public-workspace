package com.in28minutes.auth;

public class AuthenticationBean {

    private String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
        //return "HelloWorldBean [message=" + message + "]";
    }

}
