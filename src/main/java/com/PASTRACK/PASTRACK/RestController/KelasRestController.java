package com.PASTRACK.PASTRACK.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.KelasRequest.*;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private SemesterService semesterService;

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

    @GetMapping (value = "/siswa/currentClass/{usernameSiswa}")
    private KelasModel retrieveKelasInCurrentSemester (@PathVariable("usernameSiswa") String usernameSiswa){
        try {
            return kelasService.getKelasCurrentSemester(usernameSiswa);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Kelas"
            );
        }
    }

    //Get semua kelas di siswa
    @GetMapping (value = "/siswa/allKelas/{usernameSiswa}")
    private List<addKelasResponse> retrieveAllKelasSiswa (@PathVariable("usernameSiswa") String usernameSiswa){
        try {
            return kelasService.getAllKelasBySiswa(usernameSiswa);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Kelas"
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

    @GetMapping (value = "/notAssigned/Matpel")
    private List<MataPelajaranModel> retrieveNotAssignedMatpel (){
        try {
            return kelasService.getNotAssignedMatpel();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada Matpel"
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

    // Create Kelas
    @PostMapping(value = "/")
    @PreAuthorize("hasRole('ADMIN')")
    private addKelasResponse createKelas(@Valid @RequestBody addKelasRequest kelas, BindingResult bindingResult) {
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
    private List<siswaKelasResponse> addMuridToKelas(@PathVariable("idKelas") String idKelas, @RequestBody addMuridRequest[] username) {
        try{
            return kelasService.addMuridToKelas(idKelas, username);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Username " + username + " not found."
            );
        }

    }

    //Add Siswa To Kelas
    @GetMapping(value = "/listMurid/{idKelas}")
    @PreAuthorize("hasRole('ADMIN')")
    private List<StudentModel> getListMuridInKelasX(@PathVariable("idKelas") String idKelas) {
        try{
            return kelasService.getListSiswaInKelasX(idKelas);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Murid " + " not found."
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
    private List<addKelasResponse> retrieveAllKelas (){
        try {
            return kelasService.getAllKelas();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada kelas"
            );
        }
    }


    //Get Kelas By Id (Detail Kelas)
    @GetMapping (value = "/{idKelas}")
    private KelasModel retrieveKelasById (@PathVariable("idKelas") Long idKelas){
        try {
            return kelasService.getById(idKelas);
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

    // View Current Kelas di Semester Ini By Guru
    @GetMapping(value = "/guru/currentClass/{username}")
    @PreAuthorize("hasRole('GURU')")
    private kelasGuruResponse currentKelasByGuru(@PathVariable("username") String usernameGuru) {
        try {
            return kelasService.getKelasGuruCurrentSemester(usernameGuru);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }

    @GetMapping(value = "/{idKelas}/siswa")
    @PreAuthorize("hasRole('GURU') or hasRole('ADMIN')")
    private List<siswaKelasResponse> getListSiswaByKelas(@PathVariable("idKelas") Long idKelas) {
        try {
            return kelasService.getListSiswaByKelas(idKelas);
            //if(kelas.getListMurid() == null){
            //    return new ArrayList<StudentModel>();
            //}else{
            //    return kelas.getListMurid();
            //}
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "not found."
            );
        }
    }

    @GetMapping(value = "/{idKelas}/matpel")
    @PreAuthorize("hasRole('GURU') or hasRole('ADMIN') or hasRole('MURID')")
    private List<MataPelajaranModel> getListMatpelByKelas(@PathVariable("idKelas") Long idKelas) {
        try {
            addKelasResponse kelas = kelasService.getKelasById(idKelas);
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

    //Delete Kelas
    @DeleteMapping("/delete/{classId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClass(@PathVariable Long classId) {
        boolean deleted = kelasService.deleteClass(classId);

        if (deleted) {
            return ResponseEntity.ok("Class deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
