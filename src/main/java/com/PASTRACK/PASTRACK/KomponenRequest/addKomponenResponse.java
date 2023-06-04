package com.PASTRACK.PASTRACK.KomponenRequest;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class addKomponenResponse {
    Long idKomponen;
    String namaKomponen;
    String desc;
    LocalDateTime dueDate;
    int bobot;

    public addKomponenResponse(
        Long idKomponen,
        String namaKomponen,
        String desc,
        LocalDateTime dueDate,
        int bobot
    ) {
        this.idKomponen = idKomponen;
        this.namaKomponen = namaKomponen;
        this.desc = desc;
        this.dueDate = dueDate;
        this.bobot = bobot;
    }
}
