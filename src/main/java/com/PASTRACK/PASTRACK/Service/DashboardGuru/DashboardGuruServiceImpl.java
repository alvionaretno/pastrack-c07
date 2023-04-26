package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.*;
import javax.transaction.Transactional;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.NilaiAngkatanDB;
import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
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
    private DashboardGuruService dashboardGuruService;

    @Autowired
    private AngkatanService angkatanService;

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
        List<NilaiAngkatanModel> nilaiAllAngkatan = nilaiAngkatanDB.findAll();
        return nilaiAllAngkatan;
    }

    @Override
    public List<StudentModel> getSiswaByTahunMasuk(Long angkatanId) {
        List<StudentModel> siswaInAngkatanX = studentService.getStudentByTahunMasuk(angkatanId);
        return siswaInAngkatanX;
    }

    //PBI 44-45
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