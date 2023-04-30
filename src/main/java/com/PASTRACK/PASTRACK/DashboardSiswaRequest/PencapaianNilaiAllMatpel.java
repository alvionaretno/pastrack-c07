package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PencapaianNilaiAllMatpel implements Serializable {
    // String namaSiswa;
    List<String> namaSemester;
    List<Integer> nilaiPerSemester;

    public PencapaianNilaiAllMatpel(
        List<String> namaSemester,
        List<Integer> nilaiPerSemester
    ) {
        this.namaSemester = namaSemester;
        this.nilaiPerSemester = nilaiPerSemester;
    }
}
