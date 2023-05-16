package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PencapaianNilaiAllMatpel implements Serializable {
    // String namaSiswa;
    Map<String, Integer> nilaiSemester;

    public PencapaianNilaiAllMatpel(
        Map<String, Integer> nilaiSemester
    ) {
        this.nilaiSemester = nilaiSemester;
    }
}
