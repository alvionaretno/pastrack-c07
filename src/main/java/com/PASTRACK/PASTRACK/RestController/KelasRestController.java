package com.PASTRACK.PASTRACK.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    // private KelasService kelasService;
    private StudentService studentService;

    @Autowired
    // private KelasService kelasService;
    private GuruService guruService;

    @GetMapping (value = "/allGuru")
    private List<GuruModel> retrieveAllGuru (){
        try {
            return guruService.getAllGuru();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Guru"
            );
        }
    }

    @GetMapping (value = "/allSiswa")
    private List<StudentModel> retrieveAllSiswa (){
        try {
            return studentService.getAllSiswa();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Siswa"
            );
        }
    }

    @GetMapping (value = "/notAssigned/Siswa")
    private List<StudentModel> retrieveNotAssignedSiswa (){
        try {
            return kelasService.getNotAssignedStudents();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Siswa"
            );
        }
    }

    @GetMapping (value = "/allMatpel")
    private List<MataPelajaranModel> retrieveAllMatpel (){
        try {
            return matpelService.getAllMatpel();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Matpel"
            );
        }
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasRole('ADMIN')")
    private KelasModel createKelas(@Valid @RequestBody addKelasRequest kelas,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else {
            return kelasService.createKelas(kelas);
        }
    }

    //Add Siswa To Kelas
    @PutMapping(value = "/addMurid/{idKelas}")
    @PreAuthorize("hasRole('ADMIN')")
    private KelasModel addMuridToKelas(@PathVariable("idKelas") String idKelas, @RequestBody addMuridRequest[] username) {
        try{
            return kelasService.addMuridToKelas(idKelas, username);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Username " + username + " not found."
            );
        }

    }

    //Add Mata Pelajaran To Kelas
    @PutMapping(value = "/addMatpel/{idKelas}")
    @PreAuthorize("hasRole('ADMIN')")
    private KelasModel addMatpelToKelas(@PathVariable("idKelas") String id, @RequestBody addMatpelKelasRequest[] listMatpel) {
        try{
            return kelasService.addMatpelToKelas(id, listMatpel);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Mata Pelajaran " + listMatpel + " not found."
            );
        }
    }

    //Retrieve All Kelas
    @GetMapping (value = "/")
    private List<KelasModel> retrieveAllKelas (){
        try {
            return kelasService.getAllKelas();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada kelas"
            );
        }
    }


    //Get Kelas By Id
    @GetMapping (value = "/{idKelas}")
    private KelasModel retrieveKelas (@PathVariable("idKelas") Long idKelas){
        try {
            return kelasService.getKelasById(idKelas);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Kelas " + idKelas + " not found"
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
