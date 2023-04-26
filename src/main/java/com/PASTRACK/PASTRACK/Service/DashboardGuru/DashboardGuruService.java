package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.List;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.KelasRequest.siswaAllRequest;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface DashboardGuruService {

    int getRataRataNilaiSiswax(String usernameSiswa);
    List<StudentModel> rankingSiswa(String tahunMasuk);

    List<Integer> averageScorePerAngkatan(String tahunMasuk);


}