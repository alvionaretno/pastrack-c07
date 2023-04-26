package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.KelasRequest.*;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.Kelas.KelasService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.StudentKomponen.StudentKomponenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Repository.KelasDB;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;


@Service
@Transactional
public class DashboardGuruServiceImpl implements DashboardGuruService {

    @Autowired
    private KelasDB kelasDB;

    @Autowired
    private MatpelDB matpelDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DashboardGuruService dashboardGuruService;

    @Autowired
    private KelasService kelasService;

    @Autowired
    private GuruService guruService;

    @Autowired
    private MatpelService matpelService;


    //PBI 50-51
    @Override
    public int getRataRataNilaiSiswax(String usernameSiswa) {
        Optional<StudentModel> student = studentService.getUserById(usernameSiswa);
        StudentModel studentX = student.get();
        List<KelasModel> listKelasSiswaX = studentX.getListKelas();
        List<StudentMataPelajaranModel> listNilaiAkhir = studentX.getNilaiAkhir();

        Integer sumNilai = 0;
        Integer count = 0;
        for(StudentMataPelajaranModel nilaiAkhir: listNilaiAkhir){
           sumNilai += Integer.valueOf(nilaiAkhir.toString());
           count++;

        }

        Integer nilaiRataRataAkhir = sumNilai/count;
        return nilaiRataRataAkhir;
    }

    @Override
    public List<StudentModel> rankingSiswa(String tahunMasuk) {
        List<StudentModel> allSiswa = studentService.getAllSiswa();
        ArrayList<Integer> arrayNilaiSiswa = new ArrayList<Integer>();
        for(StudentModel siswa: allSiswa){
            int nilaiRataRata = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            arrayNilaiSiswa.add(nilaiRataRata);
        }
        Collections.sort(arrayNilaiSiswa);
        return null;
    }

    //PBI 42-43
    @Override
    public List<Integer> averageScorePerAngkatan(String tahunMasuk) {
        List<StudentModel> allSiswa = studentService.getAllSiswa();
        ArrayList<Integer> arrayNilaiSiswa = new ArrayList<Integer>();
        for(StudentModel siswa: allSiswa){
            int nilaiRataRata = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            arrayNilaiSiswa.add(nilaiRataRata);
        }
        Collections.sort(arrayNilaiSiswa);
        return null;
    }

}