package com.PASTRACK.PASTRACK.DashboardGuruRequest;


import com.PASTRACK.PASTRACK.Model.StudentModel;

public class AngkatanAverageScore {

    private Long idAngkatan;

    private String namaAngkatan;
    private double averageScore;


    public AngkatanAverageScore(Long idAngkatan, String namaAngkatan, double averageScore) {
        this.idAngkatan = idAngkatan;
        this.namaAngkatan = namaAngkatan;
        this.averageScore = averageScore;
    }

    public Long getIdAngkatan() {
        return idAngkatan;
    }

    public void setIdAngkatan(Long id) {
        this.idAngkatan = id;
    }

    public String namaAngkatan() {
        return namaAngkatan;
    }

    public void setNamaAngkatan(String namaAngkatan) {
        this.namaAngkatan = namaAngkatan;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }


}


