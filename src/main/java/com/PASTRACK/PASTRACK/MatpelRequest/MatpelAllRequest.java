package com.PASTRACK.PASTRACK.MatpelRequest;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MatpelAllRequest implements Serializable {
    String namaMataPelajaran;
    Boolean semester;
    String deskripsi;
    LocalDateTime awalTahunAjaran;
    LocalDateTime akhirTahunAjaran;
}
