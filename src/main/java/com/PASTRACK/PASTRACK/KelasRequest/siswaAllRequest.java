package com.PASTRACK.PASTRACK.KelasRequest;

import com.PASTRACK.PASTRACK.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class siswaAllRequest {
    String studentNumber;
    OrangTuaModel orangtua;
    List<PeminatanModel> listPeminatan;
    List<KelasModel> listKelas;
    List <StudentKomponenModel> nilai;
    List <StudentMataPelajaranModel> nilaiAkhir;
}
