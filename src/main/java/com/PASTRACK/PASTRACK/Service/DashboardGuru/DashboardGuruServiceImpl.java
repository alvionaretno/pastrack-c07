package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.*;
import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.DashboardGuruRequest.DashboardGuruResponse;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.NilaiAngkatanRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.NilaiAngkatanDB;
import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import com.PASTRACK.PASTRACK.Service.NilaiAngkatan.NilaiAngkatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
@Service
@Transactional
public class DashboardGuruServiceImpl implements DashboardGuruService {
    @Autowired
    private NilaiAngkatanDB nilaiAngkatanDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private NilaiAngkatanService nilaiAngakanService;

    @Autowired
    private DashboardGuruService dashboardGuruService;

    @Autowired
    private AngkatanService angkatanService;

    //Get all data so multiple dashboard can use one API only
    @Override
    public DashboardGuruResponse getAllData(Long angkatanId) {
        Long idAngkatan = Long.valueOf(0);

        //PBI 42-43
        List<String> listAngkatan = new ArrayList<>();
        List<Integer>listNilaiRataRataAngkatan = new ArrayList<>();

        List<AngkatanModel> listAngkatanModel = angkatanService.findAll();
        List<NilaiAngkatanModel> listNilaiAngkatanModel = nilaiAngakanService.findAll();

        for(AngkatanModel angkatan:listAngkatanModel){
            listAngkatan.add(angkatan.getAngkatan());
        }

        for(NilaiAngkatanModel nilaiAngkatan:listNilaiAngkatanModel){
            listNilaiRataRataAngkatan.add(nilaiAngkatan.getNilaiAngkatan());
        }

        //PBI 44-45
        List<String> listNamaMuridAngkatanX = new ArrayList<>();
        List<Integer> listNilaiRataRatastudentX = new ArrayList<>();

        //List<StudentModel> listSiswaAngkatanX = angkatanService.findAll();

        DashboardGuruResponse dashboardGuruResponse = new DashboardGuruResponse(idAngkatan,listAngkatan,listNilaiRataRataAngkatan,listNamaMuridAngkatanX,listNilaiRataRatastudentX);
        return dashboardGuruResponse;
    }

    //PBI 42-43
    @Override
    public NilaiAngkatanModel averageScorePerAngkatan(Long angkatanId) {
        NilaiAngkatanModel nilaiAngkatanModel = new NilaiAngkatanModel();

        Integer sumNilai = 0;
        Integer banyakSiswaAngkatanX = 0;
        List<StudentModel> listSiswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);
        for (StudentModel siswa:listSiswaInAngkatanX) {
            int rataRataNilai = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            sumNilai += rataRataNilai;
            banyakSiswaAngkatanX++;
        }

        Integer nilaiRataRataAngkatan = sumNilai/banyakSiswaAngkatanX;

        nilaiAngkatanModel.setAngkatan(angkatanService.getAngkatanById(angkatanId));
        nilaiAngkatanModel.setNilaiAngkatan(nilaiRataRataAngkatan);
        return nilaiAngkatanDB.save(nilaiAngkatanModel);
    }

    @Override
    public List<NilaiAngkatanModel> averageScoreAllAngkatan() {
        List<AngkatanModel> allAngkatan = angkatanService.findAll();
        for(AngkatanModel angkatan:allAngkatan){
            dashboardGuruService.averageScorePerAngkatan(angkatan.getId());
        }
        List<NilaiAngkatanModel> nilaiAllAngkatan = nilaiAngkatanDB.findAll();
        return nilaiAllAngkatan;
    }

    @Override
    public List<StudentModel> getSiswaByTahunMasuk(Long angkatanId) {
        List<StudentModel> siswaInAngkatanX = studentService.getStudentByTahunMasuk(angkatanId);
        return siswaInAngkatanX;
    }

    @Override
    public List<NilaiAngkatanModel> getNilaiAkhirPerAngkatan(NilaiAngkatanRequest[] listAngkatan) {
        for (int i = 0; i < listAngkatan.length; i++) {
            AngkatanModel angkatan = angkatanService.getAngkatanById(listAngkatan[i].getAngkatanId());
        }
        return dashboardGuruService.averageScoreAllAngkatan();
    }

    //PBI 44-45
    //version 1
    @Override
    public List<Integer> rataRataNilaiSiswaAngkatanX(Long angkatanId) {
        List<Integer> listRataRataNilaiAllAngkatanX = new ArrayList<>();
        List<String> listNamaNamaAllAngkatanX = new ArrayList<>();
        List<StudentModel> siswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);

        for(StudentModel siswa : siswaInAngkatanX){
            int nilaiAkhirSemester = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            listNamaNamaAllAngkatanX.add(siswa.getNama());
            listRataRataNilaiAllAngkatanX.add(nilaiAkhirSemester);
        }
        return listRataRataNilaiAllAngkatanX;
    }

    //version 2
    @Override
    public NilaiSemesterModel rataRataNilaiAkhirSemesterSiswaAngkatanX(Long angkatanId) {
        NilaiSemesterModel nilaiAkhirSemesterModel = new NilaiSemesterModel();
        List<StudentModel> siswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);

        for(StudentModel siswa : siswaInAngkatanX){
            int nilaiAkhirSemester = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            nilaiAkhirSemesterModel.setNilaiAkhirSemester(nilaiAkhirSemester);
            nilaiAkhirSemesterModel.setAngkatan(angkatanService.getAngkatanById(angkatanId));
            nilaiAkhirSemesterModel.setStudent(siswa);
        }
        return nilaiAkhirSemesterModel;
    }

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
    public ArrayList<Integer> rankingSiswa(String tahunMasuk) {
        List<StudentModel> allSiswa = studentService.getAllSiswa();
        ArrayList<Integer> arrayNilaiSiswa = new ArrayList<Integer>();
        for(StudentModel siswa: allSiswa){
            int nilaiRataRata = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            arrayNilaiSiswa.add(nilaiRataRata);
        }
        Collections.sort(arrayNilaiSiswa);
        return arrayNilaiSiswa;
    }


}