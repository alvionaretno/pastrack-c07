package com.PASTRACK.PASTRACK.Service.NilaiAngkatan;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.Model.NilaiAngkatanModel;
import com.PASTRACK.PASTRACK.Repository.NilaiAngkatanDB;
import com.PASTRACK.PASTRACK.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NilaiAngkatanServiceImpl implements NilaiAngkatanService {

    @Autowired
    NilaiAngkatanDB nilaiAngkatanDb;

    @Override
    public List<NilaiAngkatanModel> findAll() {
        return nilaiAngkatanDb.findAll();
    }

    @Override
    public NilaiAngkatanModel getById(Long id) {
        Optional<NilaiAngkatanModel> nilaiAngkatan = nilaiAngkatanDb.findById(id);
        if (nilaiAngkatan.isPresent()) {
            return nilaiAngkatan.get();
        }
        return null;
    }

}