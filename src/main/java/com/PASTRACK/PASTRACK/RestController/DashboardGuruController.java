package com.PASTRACK.PASTRACK.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Kelas.KelasService;

@RestController
@CrossOrigin
@RequestMapping("/api/dashboard/guru")
public class DashboardGuruController {

    @Autowired
    private KelasService kelasService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private AngkatanService angkatanService;

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GuruService guruService;

    //bentar ini contoh dulu
    @GetMapping (value = "/{angkatanId}")
    @PreAuthorize("hasRole('GURU')")
    private KelasModel addMuridToKelas(@PathVariable("idKelas") String idKelas, @RequestBody addMuridRequest[] username) {
        try{
            return kelasService.addMuridToKelas(idKelas, username);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Username " + username + " not found."
            );
        }

    }

    //retrieve semester
    @GetMapping (value = "/allSemester")
    private List<SemesterModel> retrieveAllSemester (){
        try {
            return semesterService.findAll();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Semester"
            );
        }
    }

    //retrieve angkatan
    @GetMapping (value = "/allAngkatan")
    private List<AngkatanModel> retrieveAllAngkatan (){
        try {
            return angkatanService.findAll();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Semester"
            );
        }
    }
}
