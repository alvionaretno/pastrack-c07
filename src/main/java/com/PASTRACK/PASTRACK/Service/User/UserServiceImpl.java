package com.PASTRACK.PASTRACK.Service.User;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.OrangTuaModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.Repository.OrangTuaDB;
import com.PASTRACK.PASTRACK.Repository.UserDB;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;


@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDB userDB;

    @Autowired
    private OrangTuaDB orangTuaDB;

    @Override
    public UserModel getUserByUsername(String username) {
        return userDB.findByUsername(username);
    }
    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
    @Override
    public UserModel addUser(UserModel user){
        return userDB.save(user);
    }
    @Override
    public String getRoleByUsername(String username) {
        // TODO Auto-generated method stub
        return userDB.findByUsername(username).getRole().getRole();
        // throw new UnsupportedOperationException("Unimplemented method 'getRoleByUsername'");
    }
    @Override
    public UserModel updateUser(String username, UserRequest user) {
        // TODO Auto-generated method stub
        UserModel oldUser = getUserByUsername(username);
        oldUser.setNama(user.getNama());
        return userDB.save(oldUser);
        
    }
    @Override
    public List<UserModel> getAllUser() {
        return userDB.findAll();
    }
    @Override
    public UserModel addUser(OrangTuaModel user) {
        // TODO Auto-generated method stub
        return orangTuaDB.save(user);
        // throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }
}
