package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.*;
import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.DashboardGuruRequest.DashboardGuruResponse;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.MatpelAverageScore;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.NilaiAngkatanRequest;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.StudentAverageScoreResponse;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.NilaiAngkatanDB;
import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import com.PASTRACK.PASTRACK.Service.NilaiAngkatan.NilaiAngkatanService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;
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

    @Autowired
    private StudentMatpelService studentMatpelService;

    //Get all data so multiple dashboard can use one API only
    @Override
    public DashboardGuruResponse getAllData(Long angkatanId) {
        Long idAngkatan = Long.valueOf(0);

        //PBI 42-43
        List<String> listAngkatan = new ArrayList<>();
        List<Double>listNilaiRataRataAngkatan = new ArrayList<>();

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
        List<Double> listNilaiRataRatastudentX = new ArrayList<>();

        //List<StudentModel> listSiswaAngkatanX = angkatanService.findAll();

        DashboardGuruResponse dashboardGuruResponse = new DashboardGuruResponse(idAngkatan,listAngkatan,listNilaiRataRataAngkatan,listNamaMuridAngkatanX,listNilaiRataRatastudentX);
        return dashboardGuruResponse;
    }

    //PBI 42-43
    @Override
    public NilaiAngkatanModel averageScorePerAngkatan(Long angkatanId) {
        NilaiAngkatanModel nilaiAngkatanModel = new NilaiAngkatanModel();

        double sumNilai = 0.0;
        Integer banyakSiswaAngkatanX = 0;
        List<StudentModel> listSiswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);
        for (StudentModel siswa:listSiswaInAngkatanX) {
            double rataRataNilai = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            sumNilai += rataRataNilai;
            banyakSiswaAngkatanX++;
        }

        double nilaiRataRataAngkatan = sumNilai/banyakSiswaAngkatanX;

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
    public List<Double> rataRataNilaiSiswaAngkatanX(Long angkatanId) {
        List<Double> listRataRataNilaiAllAngkatanX = new ArrayList<>();
        List<String> listNamaNamaAllAngkatanX = new ArrayList<>();
        List<StudentModel> siswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);

        for(StudentModel siswa : siswaInAngkatanX){
            double nilaiAkhirSemester = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
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
            double nilaiAkhirSemester = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            nilaiAkhirSemesterModel.setNilaiAkhirSemester(nilaiAkhirSemester);
            nilaiAkhirSemesterModel.setAngkatan(angkatanService.getAngkatanById(angkatanId));
            nilaiAkhirSemesterModel.setStudent(siswa);
        }
        return nilaiAkhirSemesterModel;
    }

    //PBI 50-51
    @Override
    public double getRataRataNilaiSiswax(String usernameSiswa) {

        //get student dan kelas nya
        Optional<StudentModel> student = studentService.getUserById(usernameSiswa);
        StudentModel studentX = student.get();
        List<KelasModel> listKelasSiswaX = studentX.getListKelas();
        List<StudentMataPelajaranModel> listNilaiAkhir = studentX.getNilaiAkhir();


        //looping setiap kelas yang dimiliki siswa agar kita bisa mendapatkan semua mata pelajaran yang siswa miliki
        List<MataPelajaranModel> mataPelajaranSiswa = new ArrayList<>();
        for (KelasModel kelasSiswa: listKelasSiswaX){
            List<MataPelajaranModel> listMatpelInClass = kelasSiswa.getListMataPelajaran();
            for(MataPelajaranModel matpel: listMatpelInClass){
                mataPelajaranSiswa.add(matpel);
            }
        }

        double nilaiAkhir = 0.0;

        List<MatpelAverageScore> listNilaiAkhirMatpel = new ArrayList<>();
        for(MataPelajaranModel matpels:mataPelajaranSiswa){
            for (KomponenModel komponen : matpels.getListKomponen()) {
                double bobot = komponen.getBobot();
                double nilai = komponen.getNilaiComponent();
                double nilaiPembobotan = (bobot * nilai)/100;

                //disimpen dimana?
                nilaiAkhir += nilaiPembobotan;

            }
            MatpelAverageScore nilaiAkhirMatpel = new MatpelAverageScore(matpels, nilaiAkhir);
            listNilaiAkhirMatpel.add(nilaiAkhirMatpel);
        }


        double sumNilai = 0;
        Integer count = 0;
        for(MatpelAverageScore nilaiAkhirMatpel: listNilaiAkhirMatpel){
           sumNilai += nilaiAkhirMatpel.getNilaiAkhirMatpel();
           count++;
        }

        double nilaiRataRataAkhir = sumNilai/count;
        return nilaiRataRataAkhir;
    }



    // PBI 50-51: Versi Pagination
    @Override
    public List<StudentAverageScoreResponse> getPerankinganSiswa(Long idAngkatan, int page, int size) {
        AngkatanModel angkatanModel = angkatanService.getAngkatanById(idAngkatan);
        List<StudentModel> siswaList = angkatanModel.getListStudent();
        List<StudentAverageScoreResponse> result = new ArrayList<>();
        StudentMataPelajaranModel studentMatpel = new StudentMataPelajaranModel();
        studentMatpelService.generateNilaiStudentMatpel(studentMatpel);
        for (StudentModel siswa : siswaList) {
            double averageFinalScoreSiswa = dashboardGuruService.getRataRataNilaiSiswax(siswa.getUsername());
            result.add(new StudentAverageScoreResponse(siswa, averageFinalScoreSiswa));

        }
        // sort the result by average score in descending order
        Collections.sort(result, Collections.reverseOrder());
        // perform pagination
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, result.size());
        return result.subList(startIndex, endIndex);
    }



}