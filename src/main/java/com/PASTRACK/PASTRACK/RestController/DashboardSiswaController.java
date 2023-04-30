package com.PASTRACK.PASTRACK.RestController;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.DashboardSiswaRequest.AllDashboard;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiPerMatpel;
import com.PASTRACK.PASTRACK.Service.DashboardSiswa.DashboardSiswaService;

@RestController
@CrossOrigin
@RequestMapping("/api/dashboard/siswa")
public class DashboardSiswaController {
    @Autowired
    private DashboardSiswaService dashboardSiswaService;
    
    @GetMapping(value = "/")
    @PreAuthorize("hasRole('SISWA')")
    private AllDashboard getRataRataAllMatpel(@PathVariable("username") String usernameSiswa, Principal principal) {
        try {
            return dashboardSiswaService.getAllViewed(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "not found"
            );
        }
    }
}
