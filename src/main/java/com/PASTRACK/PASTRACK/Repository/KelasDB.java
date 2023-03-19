package com.PASTRACK.PASTRACK.Repository;

import java.util.List;
import java.util.Optional;

import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.KelasModel;

@Repository
public interface KelasDB extends JpaRepository<KelasModel, Long> {
   
    Optional<KelasModel> findById(Long id);

    @Query("SELECT K FROM KelasModel K WHERE K.guru = :guru")
    List<KelasModel> findKelasByGuru(@Param("guru") GuruModel guru);

    Optional<MataPelajaranModel> findMatpelById(Long id);
}
