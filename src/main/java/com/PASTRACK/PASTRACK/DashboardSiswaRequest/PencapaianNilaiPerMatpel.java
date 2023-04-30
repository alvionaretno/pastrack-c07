package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;

import lombok.Data;

@Data
public class PencapaianNilaiPerMatpel implements Serializable {
    // Long id;
    List<String> namaSemester;
    List<Integer> nilaiPerSemester;
    PeminatanModel peminatan;

    public PencapaianNilaiPerMatpel(
        List<String> namaSemester,
        List<Integer> nilaiPerSemester,
        PeminatanModel peminatan
    ) {
        this.namaSemester = namaSemester;
        this.nilaiPerSemester = nilaiPerSemester;
        this.peminatan = peminatan;
    }
}
