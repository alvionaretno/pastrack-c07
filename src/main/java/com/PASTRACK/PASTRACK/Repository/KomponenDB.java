package com.PASTRACK.PASTRACK.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.KomponenModel;

@Repository
public interface KomponenDB extends JpaRepository<KomponenModel, Long> {
    KomponenModel findByKode(Long kode);
}
