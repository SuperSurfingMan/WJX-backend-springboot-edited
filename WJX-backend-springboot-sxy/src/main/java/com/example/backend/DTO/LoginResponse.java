package com.example.backend.DTO;

public class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    // getter
    public String getToken() {
        return token;
    }

}
