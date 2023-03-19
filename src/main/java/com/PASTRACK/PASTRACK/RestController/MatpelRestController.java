package com.PASTRACK.PASTRACK.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.text.DefaultStyledDocument.ElementSpec;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.PASTRACK.PASTRACK.KomponenRequest.UpdateKomponenSiswaRequest;
import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.KomponenModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.Komponen.KomponenService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import com.PASTRACK.PASTRACK.Service.StudentKomponen.StudentKomponenService;
import com.PASTRACK.PASTRACK.Service.User.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/matpel")
public class MatpelRestController {

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private KomponenService komponenService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentKomponenService studentKomponenService;

    // Viewall
    @GetMapping(value = "/guru/{username}")
    @PreAuthorize("hasRole('GURU')")
    private List<MatpelAllRequest> listMataPelajaran(@PathVariable("username") String username, Principal principal) {
        try {
            return matpelService.getListMatpelInGuru(username);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found.");
        }
    }

    // Create
    @PostMapping(value = "/guru/{username}")
    @PreAuthorize("hasRole('GURU')")
    private MataPelajaranModel createMatpel(@PathVariable("username") String username,
            @Valid @RequestBody addMatpelRequest matpel, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        } else {
            return matpelService.createMatpel(username, matpel);
        }
    }

    // Create
    @PostMapping(value = "/{id}/komponen")
    @PreAuthorize("hasRole('GURU')")
    private ResponseEntity<KomponenModel> createKomponen(@PathVariable("id") String id,
            @Valid @RequestBody addKomponenRequest komponen, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        } else {
            KomponenModel newKomponen = komponenService.createKomponen(id, komponen);
            return ResponseEntity.ok().body(newKomponen);
        }
    }

    @GetMapping(value = "/{idMatpel}/siswa")
    @PreAuthorize("hasRole('GURU')")
    private List<StudentModel> getListSiswa(@PathVariable("idMatpel") String idMatpel, Principal principal) {
        try {
            MataPelajaranModel matpel = matpelService.getMatpelById(Long.parseLong(idMatpel));
            if (matpel.getKelas() == null) {
                return new ArrayList<StudentModel>();
            } else {
                return matpel.getKelas().getListMurid();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found.");
        }
    }

    @GetMapping(value = "/{idMatpel}/komponen")
    @PreAuthorize("hasRole('GURU')")
    private List<KomponenModel> getListKomponen(@PathVariable("idMatpel") String idMatpel, Principal principal) {
        try {
            MataPelajaranModel matpel = matpelService.getMatpelById(Long.parseLong(idMatpel));
            if (matpel.getListKomponen() == null) {
                return new ArrayList<KomponenModel>();
            } else {
                return matpel.getListKomponen();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found.");
        }
    }

    @PutMapping(value = "/{idMatpel}/komponen/{kodeKomponen}")
    @PreAuthorize("hasRole('GURU')")
    private KomponenModel updateKomponen(@PathVariable("idMatpel") String idMatpel,
            @PathVariable("kodeKomponen") String kodeKomponen, @Valid @RequestBody addKomponenRequest komponenModel) {
        try {
            return komponenService.updateKomponen(idMatpel, kodeKomponen, komponenModel);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Komponen not found");
        }
    }

    @GetMapping(value = "/{idMatpel}/komponen/{kodeKomponen}")
    @PreAuthorize("hasRole('GURU')")
    private addKomponenRequest retrieveKomponen(@PathVariable("idMatpel") String idMatpel,
            @PathVariable("kodeKomponen") String kodeKomponen) {
        try {
            return komponenService.readKomponen(kodeKomponen);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Komponen " + kodeKomponen + " not found");
        }
    }

    @GetMapping(value = "/{idMatpel}/komponen/{kodeKomponen}/siswa/{username}")
    @PreAuthorize("hasRole('GURU')")
    private getComponent getKomponenSiswa(@PathVariable("idMatpel") String idMatpel,
            @PathVariable("kodeKomponen") String kodeKomponen,
            @PathVariable("username") String username) {
        Optional<StudentModel> student = studentService.getUserById(username);
        Optional<StudentKomponenModel> studentKomponen = studentKomponenService.getById(Long.parseLong(kodeKomponen));
        if (student.get() == studentKomponen.get().getStudent()) {
            try {
                return komponenService.getKomponen(studentKomponen.get());
            } catch (NullPointerException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Komponen " + kodeKomponen + " not found");
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Komponen " + kodeKomponen + " not found");
        }
    }

    @PutMapping(value = "/{idMatpel}/komponen/{kodeKomponen}/siswa/{username}")
    @PreAuthorize("hasRole('GURU')")
    private StudentKomponenModel UpdateKomponenSiswa(@PathVariable("idMatpel") String idMatpel,
            @PathVariable("kodeKomponen") String kodeKomponen,
            @PathVariable("username") String username, 
            @Valid @RequestBody UpdateKomponenSiswaRequest nilai, BindingResult bindingResult) {
                System.out.println(nilai);
        Optional<StudentModel> student = studentService.getUserById(username);
        Optional<StudentKomponenModel> studentKomponen = studentKomponenService.getById(Long.parseLong(kodeKomponen));
        if (student.get() == studentKomponen.get().getStudent()) {
            try {
                return komponenService.updateStudentKomponen(studentKomponen.get(), nilai.getNilai());
            } catch (NullPointerException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Komponen " + kodeKomponen + " not found");
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Komponen " + kodeKomponen + " not found");
        }
    }

    @GetMapping(value = "/{idMatpel}/siswa/{username}")
    @PreAuthorize("hasRole('GURU')")
    private List<getComponent> getListKomponenSiswa(@PathVariable("idMatpel") String idMatpel,
            @PathVariable("username") String username) {
        Optional<StudentModel> student = studentService.getUserById(username);
        // KomponenModel komponen =
        // komponenService.getKomponenByKode(Long.parseLong(kodeKomponen));
        MataPelajaranModel mataPelajaran = matpelService.getMatpelById(Long.parseLong(idMatpel));
        try {
            return komponenService.getListKomponen(student.get(), mataPelajaran);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Komponen " + idMatpel + " not found");
        }
    }
}
