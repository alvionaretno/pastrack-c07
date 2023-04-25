package com.PASTRACK.PASTRACK.Service.Angkatan;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.Model.AngkatanModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Repository.AngkatanDB;
import com.PASTRACK.PASTRACK.Repository.SemesterDB;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.RoleModel;
import com.PASTRACK.PASTRACK.Repository.RoleDB;


@Service
@Transactional
public class AngkatanServiceImpl implements AngkatanService {

    @Autowired
    AngkatanDB angkatanDb;

    @Override
    public List<AngkatanModel> findAll() {

        return angkatanDb.findAll();

    }
    @Override
    public AngkatanModel getAngkatanById(Long id) {
        Optional<AngkatanModel> angkatan = angkatanDb.findById(id);
        if (angkatan.isPresent()) {
            return angkatan.get();
        }
        return null;
    }

}
