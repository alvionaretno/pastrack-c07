package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.ArrayList;
import java.util.List;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.KelasRequest.siswaAllRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface DashboardGuruService {

    int getRataRataNilaiSiswax(String usernameSiswa);
    ArrayList<Integer> rankingSiswa(String tahunMasuk);

    List<StudentModel> getSiswaByTahunMasuk(Long angkatanId);

    NilaiAngkatanModel averageScorePerAngkatan(Long angkatanId);

    List<NilaiAngkatanModel> averageScoreAllAngkatan();

    List<Integer> rataRataNilaiSiswaAngkatanX(Long angkatanId);

    NilaiSemesterModel rataRataNilaiAkhirSemesterSiswaAngkatanX(Long angkatanId);


}