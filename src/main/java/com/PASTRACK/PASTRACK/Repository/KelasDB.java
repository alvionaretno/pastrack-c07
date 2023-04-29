package com.PASTRACK.PASTRACK.Repository;

import java.util.List;
import java.util.Optional;

import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.KelasModel;

@Repository
public interface KelasDB extends JpaRepository<KelasModel, Long> {
   
    Optional<KelasModel> findById(Long id);

    @Query("SELECT K FROM KelasModel K WHERE K.id = :id")
    KelasModel findKelasById(@Param("id") Long idKelas);

    @Query("SELECT K FROM KelasModel K WHERE K.guru = :guru")
    List<KelasModel> findKelasByGuru(@Param("guru") GuruModel guru);

    @Query("SELECT K.listKelas FROM StudentModel K WHERE K.id = :id")
    List<KelasModel> findKelasBySiswa(@Param("id") String idSiswa);

}
