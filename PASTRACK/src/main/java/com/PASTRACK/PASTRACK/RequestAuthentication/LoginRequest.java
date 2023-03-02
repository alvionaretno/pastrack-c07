package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.*;

import java.io.Serializable;


@Data
public class LoginRequest implements Serializable {
    String username;
    String password;
}