package com.PASTRACK.PASTRACK.Service.User;

import java.util.List;


import com.PASTRACK.PASTRACK.Model.OrangTuaModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;


public interface UserService {
    UserModel getUserByUsername(String username);
    String encrypt(String password);
    UserModel addUser(UserModel user);
    UserModel addUser(OrangTuaModel user);
    String getRoleByUsername(String username);
    UserModel updateUser(String username, UserRequest user);
    List<UserModel> getAllUser();
    UserModel getUserById(String id);
    UserModel ChangePassword(String password, UserModel user);
}