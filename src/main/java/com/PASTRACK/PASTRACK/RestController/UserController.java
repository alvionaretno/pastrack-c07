package com.PASTRACK.PASTRACK.RestController;

import java.io.Console;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserAllRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;
import com.PASTRACK.PASTRACK.Service.User.UserService;



@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    

    @Autowired
    private UserService userService;

    @PutMapping(value = "/{username}")
    @PreAuthorize("hasRole('MURID') or hasRole('GURU') or hasRole('ADMIN') or hasRole('ORANGTUA')")
    private UserModel updateuser(@PathVariable("username") String username, @RequestBody UserRequest user) {
        try{
            return userService.updateUser(username, user);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Username " + username + " not found."
            );
        }

    }
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('MURID') or hasRole('GURU') or hasRole('ADMIN') or hasRole('ORANGTUA')")
    private UserModel getuser(@PathVariable("username") String username, Principal principal){
        try {
            UserModel user = userService.getUserByUsername(username);
            return user;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "username " + username + " not found. "
            );
        }
    }
    @GetMapping(value = "/")
    @PreAuthorize("hasRole('ADMIN')")
    private List<UserAllRequest> getalluser(){
        try {
            List<UserAllRequest> userRequest = new ArrayList<UserAllRequest>();
            for(UserModel user: userService.getAllUser()){
                UserAllRequest tempUser = new UserAllRequest();
                tempUser.setNama(user.getNama());
                tempUser.setUsername(user.getUsername());
                tempUser.setRole(user.getRole().getRole());
                userRequest.add(tempUser);
            }
            return userRequest;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }

}
