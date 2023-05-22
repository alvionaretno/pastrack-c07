package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;

import lombok.Data;

@Data
public class PencapaianNilaiPerMatpel implements Serializable {
    // Long id;
    String semester;
    int nilaiAkhir;
    // PeminatanModel peminatan;

    public PencapaianNilaiPerMatpel(){};

    public PencapaianNilaiPerMatpel(
        String semester,
        int nilaiAkhir
    ) {
        this.semester = semester;
        this.nilaiAkhir = nilaiAkhir;
    }
}
