package com.PASTRACK.PASTRACK.KelasRequest;

import lombok.Data;

@Data
public class addKelasResponse {
    String namaKelas;
    Boolean semester;
    public addKelasResponse (String namaKelas) {
        this.namaKelas = namaKelas;

    }
}
