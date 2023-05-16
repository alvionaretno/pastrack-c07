package com.PASTRACK.PASTRACK.Service.DashboardSiswa;

import java.util.List;

import com.PASTRACK.PASTRACK.DashboardSiswaRequest.AllDashboard;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiAllMatpel;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiPerMatpel;

public interface DashboardSiswaService {
    AllDashboard getAllViewed(String username);
    // PBI 34 - 35
    List<PencapaianNilaiPerMatpel> getNilaiPerMatpel(String username);
    PencapaianNilaiAllMatpel getNilaiRataRata(String username);
    void generateAllNilaiMatpel(String usernameSiswa);
}
