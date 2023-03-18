package com.PASTRACK.PASTRACK.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.kelasMatpelRequest.addMatpelToKelasRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Kelas.KelasService;

@RestController
@CrossOrigin
@RequestMapping("/api/kelas")
public class KelasRestController {
    
    @Autowired
    // private KelasService kelasService;
    private KelasService kelasService;

    @Autowired
    // private KelasService kelasService;
    private MatpelService matpelService;


    //Create Kelas
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

    //Add Siswa To Kelas
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

    //Add Mata Pelajaran To Kelas
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

    //Retrieve Kelas By Id
    @GetMapping (value = "/{idKelas}")
    private KelasModel retrieveKelas (@PathVariable("idKelas") Long idKelas){
        try {
            return kelasService.getKelasById(idKelas);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Postingan " + idKelas + " not found"
            );
        }
    }

    // View All Kelas By Guru
    @GetMapping(value = "/guru/{username}")
    @PreAuthorize("hasRole('GURU')")
    private List<kelasAllRequest> listKelasByGuru(@PathVariable("username") String usernameGuru) {
        try {
            return kelasService.getListKelasByGuru(usernameGuru);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }

    @GetMapping(value = "/{idKelas}/siswa")
    @PreAuthorize("hasRole('GURU') or hasRole('ADMIN')")
    private List<StudentModel> getListSiswaByKelas(@PathVariable("idKelas") String idKelas) {
        try {
            KelasModel kelas = kelasService.getKelasById(Long.parseLong(idKelas));
            if(kelas.getListMurid() == null){
                return new ArrayList<StudentModel>();
            }else{
                return kelas.getListMurid();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }

    @GetMapping(value = "/{idKelas}/matpel")
    @PreAuthorize("hasRole('GURU') or hasRole('ADMIN') or hasRole('MURID')")
    private List<MataPelajaranModel> getListMatpelByKelas(@PathVariable("idKelas") String idKelas) {
        try {
            KelasModel kelas = kelasService.getKelasById(Long.parseLong(idKelas));
            if(kelas.getListMataPelajaran() == null){
                return new ArrayList<MataPelajaranModel>();
            }else{
                return kelas.getListMataPelajaran();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }



}
