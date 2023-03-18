package com.PASTRACK.PASTRACK.KelasRequest;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class kelasAllRequest implements Serializable {
    Long id;
    String namaKelas;
    Boolean semester;
    LocalDateTime awalTahunAjaran;
    LocalDateTime akhirTahunAjaran;
    List<StudentModel> listMurid;
    List<MataPelajaranModel> listMataPelajaran;
}
