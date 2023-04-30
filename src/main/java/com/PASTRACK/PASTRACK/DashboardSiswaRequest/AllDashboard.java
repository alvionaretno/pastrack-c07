package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class AllDashboard implements Serializable {
    String idStudent;
    List<PencapaianNilaiPerMatpel> perkembanganNilai;
    PencapaianNilaiAllMatpel pencapaianNilai;
    int rankingKelas;
    int rankingSemester;

    public AllDashboard(String i, 
        List<PencapaianNilaiPerMatpel> perkembanganNilai, 
        PencapaianNilaiAllMatpel pencapaianNilai,
        int rankingKelas,
        int rankingSemester
    ) {
        this.idStudent = i;
        this.perkembanganNilai = perkembanganNilai;
        this.pencapaianNilai = pencapaianNilai;
        this.rankingKelas = rankingKelas;
        this.rankingSemester = rankingSemester;
    }
}
