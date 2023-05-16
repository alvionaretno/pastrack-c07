package com.PASTRACK.PASTRACK.SemesterRequest;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class addSemesterRequest implements Serializable {
    Boolean semester;
    LocalDateTime awalTahunAjaran;
    LocalDateTime akhirTahunAjaran;

    public addSemesterRequest(Boolean semester, LocalDateTime awalTahunAjaran, LocalDateTime akhirTahunAjaran) {
        this.semester = semester;
        this.awalTahunAjaran = awalTahunAjaran;
        this.akhirTahunAjaran = akhirTahunAjaran;
    }
}
