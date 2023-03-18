package com.PASTRACK.PASTRACK.KomponenRequest;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;


public class getComponent {
    String namaKomponen;
    int bobot;
    int nilai;

    public getComponent(String namaKomponen, int bobot, int nilai){
        this.namaKomponen = namaKomponen;
        this.bobot = bobot;
        this.nilai = nilai;
    }
    public String getNamaKomponen() {
        return this.namaKomponen;
    }
    public int getBobot() {
        return this.bobot;
    }
    public int getNilai() {
        return this.nilai;
    }
}
