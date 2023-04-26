package com.PASTRACK.PASTRACK.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;

public interface StudentMatpelDB extends JpaRepository<StudentMataPelajaranModel, String> {
    Optional<StudentMataPelajaranModel> findById(Long Id);
}
