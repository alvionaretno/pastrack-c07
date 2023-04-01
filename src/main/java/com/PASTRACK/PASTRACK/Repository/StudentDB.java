package com.PASTRACK.PASTRACK.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.StudentModel;



@Repository
public interface StudentDB extends JpaRepository<StudentModel, Long> {

    Optional<StudentModel> findById(String id);

    //@Query("SELECT DISTINCT new com.PASTRACK.PASTRACK.KelasRequest.getKelasSiswaRequest(S.id AS id, K.title AS namaKomponen, K.bobot AS bobot, S.nilaiKomponen AS nilai) FROM StudentModel S, Kelas M  where K.matapelajaran = :matpel AND S.komponen = K AND S.student = :student")
    //List<StudentModel> findNotAssignedSiswa();

    //@Query("SELECT list FROM StudentModel S WHERE S.listKelas[0].")
    //List<StudentModel> findNotAssignedSiswa();
}

