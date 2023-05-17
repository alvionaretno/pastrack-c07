package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;

import lombok.Data;

@Data
public class PencapaianNilaiPerMatpel implements Serializable {
    // Long id;
    Map<String, Integer> nilaiPerSemester;
    PeminatanModel peminatan;

    public PencapaianNilaiPerMatpel(
        Map<String, Integer> nilaiPerSemester,
        PeminatanModel peminatan
    ) {
        this.nilaiPerSemester = nilaiPerSemester;
        this.peminatan = peminatan;
    }
}
