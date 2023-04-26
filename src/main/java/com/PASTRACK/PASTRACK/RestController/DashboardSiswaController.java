package com.PASTRACK.PASTRACK.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PASTRACK.PASTRACK.Service.DashboardSiswa.DashboardSiswaService;

@RestController
@CrossOrigin
@RequestMapping("/api/dashboard/siswa")
public class DashboardSiswaController {
    @Autowired
    private DashboardSiswaService dashboardSiswaService;
    
}
