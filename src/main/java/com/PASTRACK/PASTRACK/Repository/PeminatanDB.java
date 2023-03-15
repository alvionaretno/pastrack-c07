package com.PASTRACK.PASTRACK.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;

@Repository
public interface PeminatanDB extends JpaRepository<PeminatanModel, Long> {
    PeminatanModel findByNamaPeminatan(String namaPeminatan);
}
