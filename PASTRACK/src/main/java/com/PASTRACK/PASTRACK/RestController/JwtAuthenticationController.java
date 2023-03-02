package com.PASTRACK.PASTRACK.RestController;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
// import com.javainuse.service.JwtUserDetailsService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

import com.PASTRACK.PASTRACK.Model.RoleModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.LoginRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.LoginResponse;
import com.PASTRACK.PASTRACK.RequestAuthentication.RegisterRequest;
import com.PASTRACK.PASTRACK.Security.jwt.jwtutils;
import com.PASTRACK.PASTRACK.Security.service.UserDetailsServiceImpl;
import com.PASTRACK.PASTRACK.Service.Role.RoleService;
import com.PASTRACK.PASTRACK.Service.User.UserService;


// import com.javainuse.config.JwtTokenUtil;
// import com.javainuse.model.JwtRequest;
// import com.javainuse.model.JwtResponse;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private jwtutils jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        final String jwt = jwtTokenUtil.generateToken((UserDetails) authenticate.getPrincipal());
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        // UserModel users = userService.getUserByUsername(user.getUsername());
        return ResponseEntity.ok(new LoginResponse(jwt, authenticate.getName()));
    }
    @PostMapping(value = "/register")
    private UserModel    registerPasien(@RequestBody RegisterRequest request, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }

        else    {
            UserModel user = new UserModel();
            RoleModel role = roleService.getByName(request.getRole());
            user.setNama(request.getNama());
            user.setRole(role);
            user.setUsername(request.getUsername());
            user.setPassword(userService.encrypt(request.getPassword()));
            return userService.addPasien(user);
        }
    }
}