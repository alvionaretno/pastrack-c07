package com.PASTRACK.PASTRACK.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.RoleModel;

import java.util.Optional;


@Repository
public interface RoleDB extends JpaRepository<RoleModel, Long> {
     Optional<RoleModel> findById(String role);

    Optional<RoleModel> findByRole(String role);

}
