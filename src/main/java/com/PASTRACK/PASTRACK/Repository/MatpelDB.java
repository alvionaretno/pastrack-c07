package com.PASTRACK.PASTRACK.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

@Repository
public interface MatpelDB extends JpaRepository<MataPelajaranModel, Long> {
    MataPelajaranModel findById(String Id);
    @Query("SELECT M FROM MataPelajaranModel M WHERE M.guru = :guru AND M.awalTahunAjaran < :now AND M.akhirTahunAjaran > :now")
    List<MataPelajaranModel> findAllMatpelInGuru(
        @Param("guru") GuruModel guru,
        @Param("now") LocalDateTime now
    );
}
