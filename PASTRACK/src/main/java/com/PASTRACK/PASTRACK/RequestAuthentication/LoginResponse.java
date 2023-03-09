package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.Data;


@Data
public class LoginResponse {
    String username;
    String token;
    String role;
    public LoginResponse(String tokenjwt, String username, String role) {
        this.username = username;
        this.token = tokenjwt;
        this.role = role;
    }
}
