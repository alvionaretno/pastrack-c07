package com.PASTRACK.PASTRACK.Service.DashboardSiswa;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;

@Service
@Transactional
public class DashboardSiswaServiceImpl implements DashboardSiswaService {
    @Autowired
    private MatpelDB matpelDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMatpelService studentMatpelService;

    // Nilai siswa di 1 mata pelajaran
    // @Override
    // public int getNilaiSiswaInMatpel(String username, String idMatpel) {
    //     Optional<StudentModel> student = studentService.getUserById(username);
    //     StudentModel siswa = student.get();
    //     Optional<MataPelajaranModel> x = matpelDB.findById(idMatpel);
    //     MataPelajaranModel matpel = x.get();
    //     studentMatpelService.generateNilaiStudentMatpel(null);

    //     return 
    // }
}
