package com.PASTRACK.PASTRACK.DashboardSiswaRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PencapaianNilaiAllMatpel implements Serializable {
    // String namaSiswa;
    String semester;
    int avgScore;

    public PencapaianNilaiAllMatpel(){}

    public PencapaianNilaiAllMatpel(
        String semester,
        int avgScore
    ) {
        this.semester = semester;
        this.avgScore = avgScore;
    }
}
