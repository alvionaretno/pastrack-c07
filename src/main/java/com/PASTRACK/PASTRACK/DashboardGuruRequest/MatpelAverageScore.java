package com.PASTRACK.PASTRACK.DashboardGuruRequest;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class MatpelAverageScore {

    MataPelajaranModel matpel;
    double nilaiAkhirMatpel;


    public MatpelAverageScore (MataPelajaranModel matpel, double nilaiAkhirMatpel) {
        this.matpel = matpel;
        this.nilaiAkhirMatpel = nilaiAkhirMatpel;
    }

}