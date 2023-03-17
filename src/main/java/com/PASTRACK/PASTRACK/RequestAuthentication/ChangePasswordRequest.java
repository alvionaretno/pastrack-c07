package com.PASTRACK.PASTRACK.RequestAuthentication;

import lombok.*;

import java.io.Serializable;


@Data
public class ChangePasswordRequest implements Serializable {
    String passwordLama;
    String passwordBaru;
}