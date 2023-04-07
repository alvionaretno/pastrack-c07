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
@RequestMapping("/api/dashboard/guru")
public class DashboardGuruController {

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

    //Add Mata Pelajaran To Kelas
    //@PutMapping(value = "/")
    //@PreAuthorize("hasRole('GURU')")
    //private KelasModel addMatpelToKelas(@PathVariable("idKelas") String id, @RequestBody addMatpelKelasRequest[] listMatpel) {
   //     try{
   //         return kelasService.addMatpelToKelas(id, listMatpel);
   //     } catch (NoSuchElementException e){
   //         throw new ResponseStatusException(
   //                 HttpStatus.NOT_FOUND, "Mata Pelajaran " + listMatpel + " not found."
    //        );
    //    }
    //}
}
