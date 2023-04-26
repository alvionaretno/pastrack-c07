package com.PASTRACK.PASTRACK.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.Model.AngkatanModel;
import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import com.PASTRACK.PASTRACK.Service.Postingan.PostinganService;

@RestController
@CrossOrigin
@RequestMapping("/api/angkatan")
public class AngkatanController {

    @Autowired
    private AngkatanService angkatanService;



    @GetMapping (value = "/")
    private List<AngkatanModel> findAllSemester (){
        try {
            return angkatanService.findAll();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Semester not found"
            );
        }
    }


}
