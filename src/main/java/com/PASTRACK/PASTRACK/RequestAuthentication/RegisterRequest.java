package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
    String username;
    String password;
    String nama;
    String role;
}