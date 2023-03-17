package com.PASTRACK.PASTRACK.RestController;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.kelasMatpelRequest.addMatpelToKelasRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Kelas.KelasService;

@RestController
@CrossOrigin
@RequestMapping("/api/kelas")
public class KelasController {
    
    @Autowired
    // private KelasService kelasService;
    private KelasService kelasService;

    @PostMapping(value="/")
    private KelasModel createKelas(@Valid @RequestBody KelasModel kelas, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else {
            return kelasService.addKelas(kelas);
        }
    }
    @PutMapping(value = "/addMurid/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private KelasModel addMuridToKelas(@PathVariable("id") String id, @RequestBody addMuridRequest[] username) {
        try{
            return kelasService.addMuridToKelas(id, username);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Username " + username + " not found."
            );
        }

    }

    @PutMapping(value = "/addMatpel/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private KelasModel addMatpelToKelas(@PathVariable("id") String id, @RequestBody addMatpelToKelasRequest[] listMatpel) {
        try{
            return kelasService.addMatpelToKelas(id, listMatpel);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Mata Pelajaran " + listMatpel + " not found."
            );
        }

    }

    
}
