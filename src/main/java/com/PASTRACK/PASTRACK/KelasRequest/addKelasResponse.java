package com.PASTRACK.PASTRACK.KelasRequest;

import lombok.Data;

@Data
public class addKelasResponse {
    String namaKelas;
    Long semesterId;
    String usernameGuru;

    public addKelasResponse (String namaKelas, Long semesterId, String usernameGuru) {
        this.namaKelas = namaKelas;
        this.semesterId = semesterId;
        this.usernameGuru = usernameGuru;
    }

}
