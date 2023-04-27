package com.PASTRACK.PASTRACK.Service.NilaiSemester;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.Model.NilaiAngkatanModel;
import com.PASTRACK.PASTRACK.Model.NilaiSemesterModel;
import com.PASTRACK.PASTRACK.Repository.NilaiAngkatanDB;
import com.PASTRACK.PASTRACK.Repository.NilaiSemesterDB;
import com.PASTRACK.PASTRACK.Service.NilaiAngkatan.NilaiAngkatanService;
import com.PASTRACK.PASTRACK.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NilaiSemesterServiceImpl implements NilaiSemesterService {

    @Autowired
    NilaiSemesterDB nilaiSemesterDb;

    @Override
    public List<NilaiSemesterModel> findAll() {
        return nilaiSemesterDb.findAll();
    }

    @Override
    public NilaiSemesterModel getById(Long id) {
        Optional<NilaiSemesterModel> nilaiSemester = nilaiSemesterDb.findById(id);
        if (nilaiSemester.isPresent()) {
            return nilaiSemester.get();
        }
        return null;
    }

}