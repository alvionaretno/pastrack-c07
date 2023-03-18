package com.PASTRACK.PASTRACK.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.Model.KomponenModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;

@Repository
public interface KomponenDB extends JpaRepository<KomponenModel, Long> {
    KomponenModel findByKode(Long kode);

    @Query("SELECT new com.PASTRACK.PASTRACK.KomponenRequest.getComponent(K.title, K.bobot, S.nilaiKomponen) FROM KomponenModel K, StudentKomponenModel S  where S.student = :student AND S.komponen = :komponen" )
    getComponent getKomponenNilai(
        @Param("student") StudentModel student,
        @Param("komponen") KomponenModel komponen
    );
    @Query("SELECT DISTINCT new com.PASTRACK.PASTRACK.KomponenRequest.getComponent(K.title AS namaKomponen , K.bobot AS bobot, S.nilaiKomponen AS nilai) FROM KomponenModel K, StudentKomponenModel S, MataPelajaranModel M  where K.matapelajaran = :matpel AND S.komponen = K AND S.student = :student")
    List<getComponent> getAllKomponenSiswa(
        @Param("student") StudentModel student,
        @Param("matpel") MataPelajaranModel matpel
    );
}
