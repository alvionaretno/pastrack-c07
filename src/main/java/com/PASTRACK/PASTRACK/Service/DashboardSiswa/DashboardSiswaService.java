package com.PASTRACK.PASTRACK.Service.DashboardSiswa;

import java.util.List;

import com.PASTRACK.PASTRACK.DashboardSiswaRequest.AllDashboard;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiAllMatpel;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiPerMatpel;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.allRankingSiswa;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.*;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;

public interface DashboardSiswaService {

    List<PencapaianNilaiAllMatpel> getNilaiRataRata(String username);
    void generateAllNilaiMatpel(String usernameSiswa);

    //get ranking student di angkatannya
    int getStudentRankingInAngkatan(String username);

    //get ranking student di kelasnya
    int getStudentRankingInKelas(String username);

    double getRataRataNilaiSiswaCurrentSemester(String usernameSiswa);

    int getStudentRankingInAngkatanCurrentSemester(String username);

    allRankingSiswa getAllRankingSiswa(String usernameSiswa);

    List<PencapaianNilaiPerMatpel> getNilaiPerMatpel(String username);

    List<PencapaianNilaiPerMatpel> getNilaiMatpel(String username, String namaPeminatan);
    List<StudentScoreDTO> getStudentScoresBySemester(String username);

    double getRataRataNilaiSiswaGivenSemester(String usernameSiswa, SemesterModel semester);
}
