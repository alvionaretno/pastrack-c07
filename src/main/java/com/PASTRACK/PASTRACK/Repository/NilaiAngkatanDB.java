package com.PASTRACK.PASTRACK.Repository;


import com.PASTRACK.PASTRACK.Model.NilaiAngkatanModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.OrangTuaModel;

import java.util.Optional;


@Repository
public interface NilaiAngkatanDB extends JpaRepository<NilaiAngkatanModel, Long> {
    Optional<NilaiAngkatanModel> findById(Long id);

}