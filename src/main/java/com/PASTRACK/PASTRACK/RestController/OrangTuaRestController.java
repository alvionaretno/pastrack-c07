package com.PASTRACK.PASTRACK.RestController;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.OrangTuaRequest.OrangTuaRequest;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanRequest;
import com.PASTRACK.PASTRACK.Service.OrangTua.OrangTuaService;
import com.PASTRACK.PASTRACK.Service.Peminatan.PeminatanService;

@RestController
@CrossOrigin
@RequestMapping("/api/orangtua")
public class OrangTuaRestController {
    @Autowired
    private OrangTuaService orangTuaService;

    @GetMapping("/{usernameOrtu}")
    @PreAuthorize("hasRole('ORANGTUA')")
    private List<OrangTuaRequest> getUsernameAnak(@PathVariable("usernameOrtu") String username, Principal principal) {
        try {
            return orangTuaService.getUsernameSiswa(username);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found.");
        }
    }
}
