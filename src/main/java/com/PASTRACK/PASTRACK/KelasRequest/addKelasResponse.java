package com.PASTRACK.PASTRACK.KelasRequest;

import lombok.Data;

@Data
public class addKelasResponse {
    String namaKelas;
    Long semesterId;
    public addKelasResponse (String namaKelas, Long semesterId) {
        this.namaKelas = namaKelas;
        this.semesterId = semesterId;

    }
}
