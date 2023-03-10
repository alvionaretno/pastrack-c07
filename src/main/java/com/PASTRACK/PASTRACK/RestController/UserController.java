package com.PASTRACK.PASTRACK.RestController;

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
    @PreAuthorize("hasRole('MURID') or hasRole('GURU') or hasRole('ADMIN') or hasRole('ORANGTUA')")
    @GetMapping(value = "/{username}")
    private UserModel getuser(@PathVariable("username") String username){
        try {
            return userService.getUserByUsername(username);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "username " + username + " not found. "
            );
        }
    }

}
