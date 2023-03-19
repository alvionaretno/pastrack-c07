package com.PASTRACK.PASTRACK.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.StudentModel;



@Repository
public interface StudentDB extends JpaRepository<StudentModel, Long> {

    Optional<StudentModel> findById(String id);
}

