package com.PASTRACK.PASTRACK.Service.User;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;

// import apap.project.rumahsehat.Model.AdminModel;
// import apap.project.rumahsehat.Model.ApotekerModel;
// import apap.project.rumahsehat.Model.DokterModel;
// import apap.project.rumahsehat.Model.PasienModel;
// import apap.project.rumahsehat.Model.UserModel;

public interface UserService {
    UserModel getUserByUsername(String username);
    String encrypt(String password);
    UserModel addUser(UserModel user);
    String getRoleByUsername(String username);
    UserModel updateUser(String username, UserRequest user);
    List<UserModel> getAllUser();
}