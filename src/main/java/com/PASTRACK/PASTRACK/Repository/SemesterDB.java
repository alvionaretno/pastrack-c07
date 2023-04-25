package com.PASTRACK.PASTRACK.Repository;



import com.PASTRACK.PASTRACK.Model.SemesterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.RoleModel;

import java.util.Optional;


@Repository
public interface SemesterDB extends JpaRepository<SemesterModel, Long> {
    Optional<SemesterModel> findById(Long id);

}
