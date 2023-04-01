package com.PASTRACK.PASTRACK.KelasRequest;

import java.time.LocalDateTime;

public class getMatpelRequest {
    static Long id;
    String namaMataPelajaran;
    Boolean semester;
    LocalDateTime awalTahunAjaran;
    LocalDateTime akhirTahunAjaran;
    String deskripsi;

    public getMatpelRequest(Long id, String namaMataPelajaran, Boolean semester, LocalDateTime awalTahunAjaran, LocalDateTime akhirTahunAjaran, String deskripsi){
        this.id = id;
        this.namaMataPelajaran = namaMataPelajaran;
        this.semester = semester;
        this.awalTahunAjaran = awalTahunAjaran;
        this.akhirTahunAjaran = akhirTahunAjaran;
        this.deskripsi = deskripsi;
    }
    public String getNamaMataPel() {
        return this.namaMataPelajaran;
    }
    public Boolean getSemester() {
        return this.semester;
    }
    public LocalDateTime getAwalTahunAjaran() {
        return this.awalTahunAjaran;
    }
    public LocalDateTime getAkhirTahunAjaran() {
        return this.akhirTahunAjaran;
    }
    public static Long getId(){return id;}
}
