package com.PASTRACK.PASTRACK.RestController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanRequest;
import com.PASTRACK.PASTRACK.Service.Peminatan.PeminatanService;

@RestController
@CrossOrigin
@RequestMapping("/api/peminatan")
public class PeminatanRestController {

    @Autowired
    private PeminatanService peminatanService;

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    private PeminatanModel createPeminatan(@Valid @RequestBody PeminatanRequest peminatanReq, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field.");
        } else {
            return peminatanService.createPeminatan(peminatanReq);
        }
    }
}
