package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterSiswa implements Serializable {
    String username;
    String password;
    String nama;
    Long angkatanId;
    String role;
    String studentNumber;
}