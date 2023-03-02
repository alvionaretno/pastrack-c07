package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.Data;


@Data
public class LoginResponse {
    String username;
    String token;
    public LoginResponse(String tokenjwt, String username) {
        this.username = username;
        this.token = tokenjwt;
    }
}
