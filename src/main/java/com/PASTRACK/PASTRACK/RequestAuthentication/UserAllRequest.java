package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAllRequest implements Serializable {
    String username;
    String nama;
    String role;
}
