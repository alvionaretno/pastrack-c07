package com.PASTRACK.PASTRACK.RestController;

import java.util.ArrayList;


import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

import com.PASTRACK.PASTRACK.Model.AdminModel;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.OrangTuaModel;
import com.PASTRACK.PASTRACK.Model.RoleModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.LoginRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.LoginResponse;
import com.PASTRACK.PASTRACK.RequestAuthentication.RegisterGuru;
import com.PASTRACK.PASTRACK.RequestAuthentication.RegisterRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.RegisterSiswa;
import com.PASTRACK.PASTRACK.Security.jwt.jwtutils;
import com.PASTRACK.PASTRACK.Service.Role.RoleService;
import com.PASTRACK.PASTRACK.Service.User.UserService;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtutils jwtTokenUtil;


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AngkatanService angkatanService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        final String jwt = jwtTokenUtil.generateToken((UserDetails) authenticate.getPrincipal());
        // UserDetails user = (UserDetails) authenticate.getPrincipal();
        // UserModel users = userService.getUserByUsername(user.getUsername());
        // System.out.println(authenticate.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponse(jwt, authenticate.getName(), userService.getRoleByUsername(loginRequest.getUsername())));
    }

    @PostMapping(value = "/register")
    private UserModel register(@RequestBody RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        }

        else {
            AdminModel user = new AdminModel();
            RoleModel role = roleService.getByName(request.getRole());
            user.setNama(request.getNama());
            user.setRole(role);
            user.setUsername(request.getUsername());
            user.setPassword(userService.encrypt(request.getPassword()));
            return userService.addUser(user);

        }
    }

    @PostMapping(value = "/register/student")
    private UserModel registerStudent(@RequestBody RegisterSiswa request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        }

        else {
            StudentModel user = new StudentModel();
            RoleModel role = roleService.getByName(request.getRole());
            user.setNama(request.getNama());
            user.setRole(role);
            user.setUsername(request.getUsername());
            user.setAngkatan(angkatanService.getAngkatanById(request.getAngkatanId()));
            user.setPassword(userService.encrypt(request.getPassword()));
            user.setStudentNumber(request.getStudentNumber());

            OrangTuaModel orangTua = new OrangTuaModel();
            orangTua.setNama("Orang Tua " + user.getNama());
            orangTua.setRole(roleService.getByName("ORANGTUA"));
            orangTua.setUsername("orangtua" + user.getStudentNumber());
            orangTua.setPassword( userService.encrypt(user.getStudentNumber()));
            orangTua.setAnak(new ArrayList<StudentModel>());
            orangTua.getAnak().add(user);
            user.setOrangtua(orangTua);
            userService.addUser(orangTua);
            return userService.addUser(user);
        }
    }

    @PostMapping(value = "/register/guru")
    private UserModel registerGuru(@RequestBody RegisterGuru request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        }

        else {
            GuruModel user = new GuruModel();
            RoleModel role = roleService.getByName(request.getRole());
            user.setNama(request.getNama());
            user.setRole(role);
            user.setUsername(request.getUsername());
            user.setPassword(userService.encrypt(request.getPassword()));
            user.setGuruId(request.getGuruId());
            return userService.addUser(user);
        }
    }
}
