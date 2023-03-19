package com.PASTRACK.PASTRACK.KomponenRequest;

public class getComponent {
    Long id;
    String namaKomponen;
    int bobot;
    int nilai;

    public getComponent(Long id, String namaKomponen, int bobot, int nilai){
        this.id = id;
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
    public Long getId(){
        return this.id;
    }
}
