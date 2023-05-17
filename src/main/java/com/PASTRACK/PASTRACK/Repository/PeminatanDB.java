package com.PASTRACK.PASTRACK.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;

@Repository
public interface PeminatanDB extends JpaRepository<PeminatanModel, Long> {
    PeminatanModel findByNamaPeminatan(String namaPeminatan);
    Optional<PeminatanModel> findById(Long id);
}
