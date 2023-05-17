package com.PASTRACK.PASTRACK.Service.DashboardSiswa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.DashboardSiswaRequest.AllDashboard;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiAllMatpel;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiPerMatpel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.StudentDB;
import com.PASTRACK.PASTRACK.Repository.StudentMatpelDB;
import com.PASTRACK.PASTRACK.Service.Peminatan.PeminatanService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;

@Service
@Transactional
public class DashboardSiswaServiceImpl implements DashboardSiswaService {
    @Autowired
    private MatpelDB matpelDB;

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private StudentMatpelDB studentMatpelDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMatpelService studentMatpelService;

    @Autowired
    private PeminatanService peminatanService;

    @Override
    public AllDashboard getAllViewed(String username) {
        Optional<StudentModel> user = studentService.getUserById(username);
        StudentModel student = user.get();
        List<PencapaianNilaiPerMatpel> perkembanganNilai = getNilaiPerMatpel(username);
        PencapaianNilaiAllMatpel pencapaianNilai = getNilaiRataRata(username);
        int rankingKelas = 0; // PBI 52
        int rankingSemester = 0; // PBI 53
        return new AllDashboard(student.getId(), perkembanganNilai, pencapaianNilai, rankingKelas, rankingSemester);
    }

    // PBI 34 - 35
    @Override
    public List<PencapaianNilaiPerMatpel> getNilaiPerMatpel(String username) {
        List<PencapaianNilaiPerMatpel> listPencapaian = new ArrayList<PencapaianNilaiPerMatpel>();
        Optional<StudentModel> x = studentService.getUserById(username);
        StudentModel student = x.get();
        List<PeminatanModel> peminatanInSiswa = student.getListPeminatan();

        for (StudentMataPelajaranModel studentMatpel : student.getNilaiAkhir()) {
            
        }
        return listPencapaian;
    }

    // PBI 46 - 47
    @Override
    public PencapaianNilaiAllMatpel getNilaiRataRata(String username) {
        Optional<StudentModel> student = studentService.getUserById(username);
        StudentModel siswa = student.get();
        List<StudentMataPelajaranModel> listStudentMatpel = siswa.getNilaiAkhir();
        Map<String, Integer> nilaiSemester = new HashMap<>();
        
        return new PencapaianNilaiAllMatpel(nilaiSemester);
    }

    @Override
    public void generateAllNilaiMatpel(String usernameSiswa) {
        Optional<StudentModel> x = studentService.getUserById(usernameSiswa);
        StudentModel student = x.get();
        List<StudentMataPelajaranModel> listStudentMatpel = student.getNilaiAkhir();
        for (StudentMataPelajaranModel studentMatpel : listStudentMatpel) {
            studentMatpelService.generateNilaiStudentMatpel(studentMatpel);
            studentMatpelDB.save(studentMatpel);
        }
    }
}
