package com.PASTRACK.PASTRACK.KelasRequest;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class siswaKelasResponse {

    String namaSiswa;


    public siswaKelasResponse (String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

}