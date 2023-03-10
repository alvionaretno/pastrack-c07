package com.PASTRACK.PASTRACK.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.KelasModel;

@Repository
public interface KelasDB extends JpaRepository<KelasModel, Long> {
   
    Optional<KelasModel> findById(Long id);
}
