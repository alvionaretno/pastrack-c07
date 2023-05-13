package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.ArrayList;
import java.util.List;

import com.PASTRACK.PASTRACK.DashboardGuruRequest.AngkatanAverageScore;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.DashboardGuruResponse;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.NilaiAngkatanRequest;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.StudentAverageScoreResponse;
import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.KelasRequest.siswaAllRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface DashboardGuruService {

    double getRataRataNilaiSiswax(String usernameSiswa);
    //ArrayList<Integer> rankingSiswa(String tahunMasuk);

    List<StudentModel> getSiswaByTahunMasuk(Long angkatanId);

    NilaiAngkatanModel averageScorePerAngkatan(Long angkatanId);

    List<NilaiAngkatanModel> averageScoreAllAngkatan();

    List<Double> rataRataNilaiSiswaAngkatanX(Long angkatanId);

    NilaiSemesterModel rataRataNilaiAkhirSemesterSiswaAngkatanX(Long angkatanId);

    List<NilaiAngkatanModel> getNilaiAkhirPerAngkatan(NilaiAngkatanRequest[] listAngkatan);

    DashboardGuruResponse getAllData(Long idAngkatan);

    //PBI 42-43
    List<AngkatanAverageScore> getAverageScoreByAngkatan();

    //PBI 50-51
    List<StudentAverageScoreResponse> getPerankinganSiswa(Long idAngkatan, int page, int size);


}