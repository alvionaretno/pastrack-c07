package com.PASTRACK.PASTRACK.DashboardGuruRequest;


import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import lombok.Data;

import java.util.List;

@Data
public class DashboardGuruResponse {

    //identifier PBI 42-43 dan 44-45
    Long idAngkatan;

    //PBI 42-43
    List<String> listAngkatan;
    List<Integer>listNilaiRataRataAngkatan;

    //PBI 44-45
    List<String> listNamaMuridAngkatanX;
    List<Integer> listNilaiRataRatastudentX;

    public DashboardGuruResponse (Long idAngkatan,List<String> listAngkatan,List<Integer>listNilaiRataRataAngkatan,List<String> listNamaMuridAngkatanX,List<Integer> listNilaiRataRatastudentX) {
        this.idAngkatan = idAngkatan;
        this.listAngkatan = listAngkatan;
        this.listNilaiRataRataAngkatan = listNilaiRataRataAngkatan;
        this.listNamaMuridAngkatanX = listNamaMuridAngkatanX;
        this.listNilaiRataRatastudentX = listNilaiRataRatastudentX;
    }
}