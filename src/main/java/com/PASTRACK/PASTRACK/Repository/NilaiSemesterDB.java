package com.PASTRACK.PASTRACK.Repository;

import com.PASTRACK.PASTRACK.Model.NilaiSemesterModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NilaiSemesterDB extends JpaRepository<NilaiSemesterModel, Long> {

    Optional<NilaiSemesterModel> findById(Long id);

}
