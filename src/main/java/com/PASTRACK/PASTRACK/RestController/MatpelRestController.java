package com.PASTRACK.PASTRACK.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.User.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/matpel")
public class MatpelRestController {
    
    @Autowired
    private MatpelService matpelService;

    // Viewall
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('GURU')")
    private List<MatpelAllRequest> listMataPelajaran(@PathVariable("username") String username, Principal principal) {
        try {
            return matpelService.getListMatpelInGuru(username);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }

    // Create
    @PostMapping(value = "/add/{username}")
    @PreAuthorize("hasRole('GURU')")
    private MataPelajaranModel createMatpel(@PathVariable("username") String username, @Valid @RequestBody addMatpelRequest matpel, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else {
            return matpelService.createMatpel(username, matpel);
        }
    }
}
