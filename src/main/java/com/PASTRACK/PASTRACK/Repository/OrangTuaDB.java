package com.PASTRACK.PASTRACK.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.OrangTuaModel;



@Repository
public interface OrangTuaDB extends JpaRepository<OrangTuaModel, Long> {

}
