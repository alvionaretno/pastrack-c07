package com.PASTRACK.PASTRACK.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;

@Repository
public interface StudentKomponenDB extends JpaRepository<StudentKomponenModel, String> {
    Optional<StudentKomponenModel> findById(Long Id);
   
}
 