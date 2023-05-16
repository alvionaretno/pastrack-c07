package com.PASTRACK.PASTRACK.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterRequest;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterResponse;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Service.Postingan.PostinganService;

@RestController
@CrossOrigin
@RequestMapping("/api/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    //Add
    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    private addSemesterResponse createSemester(@Valid @RequestBody addSemesterRequest semesterReq, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        } else {
            return semesterService.createSemester(semesterReq);
        }
    }

    @GetMapping (value = "/")
    private List<SemesterModel> findAllSemester (){
        try {
            return semesterService.findAll();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Semester not found"
            );
        }
    }
}