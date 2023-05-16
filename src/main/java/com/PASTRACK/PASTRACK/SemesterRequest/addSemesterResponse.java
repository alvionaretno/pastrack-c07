package com.PASTRACK.PASTRACK.SemesterRequest;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class addSemesterResponse implements Serializable {
    Long id;
    Boolean semester;
    LocalDateTime awalTahunAjaran;
    LocalDateTime akhirTahunAjaran;

    public addSemesterResponse(Long id, Boolean semester, LocalDateTime awalTahunAjaran, LocalDateTime akhirTahunAjaran) {
        this.id = id;
        this.semester = semester;
        this.awalTahunAjaran = awalTahunAjaran;
        this.akhirTahunAjaran = akhirTahunAjaran;
    }
}
