package com.PASTRACK.PASTRACK.Service.DashboardSiswa;

import java.util.List;

import com.PASTRACK.PASTRACK.DashboardSiswaRequest.AllDashboard;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiAllMatpel;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiPerMatpel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;

public interface DashboardSiswaService {
    AllDashboard getAllViewed(String username);
    // PBI 34 - 35
    List<PencapaianNilaiPerMatpel> getNilaiPerMatpel(String username);
    PencapaianNilaiAllMatpel getNilaiRataRata(String username);
    void generateAllNilaiMatpel(String usernameSiswa);

    //get ranking student di angkatannya
    int getStudentRankingInAngkatan(String username, Long idAngkatan);

    //get ranking student di kelasnya
    int getStudentRankingInKelas(String username);
}
