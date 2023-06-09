package com.PASTRACK.PASTRACK.Repository;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.GuruModel;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

@Repository
public interface MatpelDB extends JpaRepository<MataPelajaranModel, String> {
    MataPelajaranModel findById(Long Id);
    @Query("SELECT M FROM MataPelajaranModel M WHERE M.guru = :guru AND M.semester.awalTahunAjaran < :now AND M.semester.akhirTahunAjaran > :now")
    List<MataPelajaranModel> findAllMatpelInGuru(
        @Param("guru") GuruModel guru,
        @Param("now") LocalDateTime now
    );

    @Query("SELECT M FROM MataPelajaranModel M WHERE M.namaMataPelajaran = :namaMatpel")
    Optional<MataPelajaranModel> findByName(
            @Param("namaMatpel") String namaMatpel
    );

    @Query("SELECT id FROM MataPelajaranModel")
    Long findIdMatpel();
    @Query("SELECT M FROM MataPelajaranModel M WHERE M.guru = :guru")
    List<MataPelajaranModel> findListAllMatpelInGuru(
        @Param("guru") GuruModel guru
    );
}
