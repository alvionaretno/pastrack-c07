package com.PASTRACK.PASTRACK.MatpelRequest;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.PASTRACK.PASTRACK.Model.SemesterModel;

@Data
public class MatpelAllRequest implements Serializable {
    Long id;
    String namaMataPelajaran;
    SemesterModel semester;
    String deskripsi;
}
