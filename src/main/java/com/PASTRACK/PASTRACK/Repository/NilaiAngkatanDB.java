package com.PASTRACK.PASTRACK.Repository;


import com.PASTRACK.PASTRACK.Model.NilaiAngkatanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.OrangTuaModel;



@Repository
public interface NilaiAngkatanDB extends JpaRepository<NilaiAngkatanModel, Long> {

}