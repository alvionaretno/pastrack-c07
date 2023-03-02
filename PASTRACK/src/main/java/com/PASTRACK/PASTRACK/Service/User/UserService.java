package com.PASTRACK.PASTRACK.Service.User;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.UserModel;

// import apap.project.rumahsehat.Model.AdminModel;
// import apap.project.rumahsehat.Model.ApotekerModel;
// import apap.project.rumahsehat.Model.DokterModel;
// import apap.project.rumahsehat.Model.PasienModel;
// import apap.project.rumahsehat.Model.UserModel;

public interface UserService {
    UserModel getUserByUsername(String username);
    String encrypt(String password);
    UserModel addPasien(UserModel user);
}