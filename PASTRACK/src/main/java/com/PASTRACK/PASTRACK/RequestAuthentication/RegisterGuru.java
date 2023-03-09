package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterGuru implements Serializable {
    String username;
    String password;
    String nama;
    String role;
    String guruId;
}
