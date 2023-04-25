package com.PASTRACK.PASTRACK.Repository;

import com.PASTRACK.PASTRACK.Model.AngkatanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.RoleModel;

import java.util.Optional;


@Repository
public interface AngkatanDB extends JpaRepository<AngkatanModel, Long> {
    Optional<AngkatanModel> findById(Long id);
}