package com.PASTRACK.PASTRACK.DashboardGuruRequest;


import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import lombok.Data;

import java.util.List;

@Data
//ini belom di configure
public class DashboardGuruResponse {

    Long idKelas;
    String namaKelas;
    Long semesterId;
    String usernameGuru;

    List<StudentModel> listMurid;

    List<MataPelajaranModel> listMataPelajaran;

    public DashboardGuruResponse (Long idKelas, String namaKelas, Long semesterId, String usernameGuru, List<StudentModel> listMurid, List<MataPelajaranModel> listMataPelajaran) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.semesterId = semesterId;
        this.usernameGuru = usernameGuru;
        this.listMurid = listMurid;
        this.listMataPelajaran = listMataPelajaran;
    }

}