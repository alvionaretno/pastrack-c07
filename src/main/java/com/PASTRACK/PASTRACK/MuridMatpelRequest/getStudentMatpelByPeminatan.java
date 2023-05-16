package com.PASTRACK.PASTRACK.MuridMatpelRequest;

import lombok.*;

import java.io.Serializable;

@Data
public class getStudentMatpelByPeminatan implements Serializable {
    Long id;
    int nilaiKomponen;
    Long idSiswa;
    String usernameSiswa;
    Long idMatpel;
    String namaMatpel;

    public getStudentMatpelByPeminatan(
        Long id,
        int nilaiKomponen,
        Long idSiswa,
        String usernameSiswa,
        Long idMatpel,
        String namaMatpel) {
        this.id = id;
        this.nilaiKomponen = nilaiKomponen;
        this.idSiswa = idSiswa;
        this.usernameSiswa = usernameSiswa;
        this.idMatpel = idMatpel;
        this.namaMatpel = namaMatpel;
    }
}
