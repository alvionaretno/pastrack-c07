package com.PASTRACK.PASTRACK.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.GuruModel;


@Repository
public interface GuruDB extends JpaRepository<GuruModel, Long>{
    GuruModel findByUsername(String username);
}
