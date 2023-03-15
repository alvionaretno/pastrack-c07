package com.PASTRACK.PASTRACK.MatpelRequest;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class addMatpelRequest implements Serializable {
    String namaMataPelajaran;
    String semester;
    String namaPeminatan;
    LocalDateTime awalTahunAjaran;
    LocalDateTime akhirTahunAjaran;
}
