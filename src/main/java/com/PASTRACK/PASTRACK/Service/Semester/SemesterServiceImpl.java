package com.PASTRACK.PASTRACK.Service.Role;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Repository.SemesterDB;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.RoleModel;
import com.PASTRACK.PASTRACK.Repository.RoleDB;




@Service
@Transactional
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    SemesterDB semesterDb;

    @Override
    public List<SemesterModel> findAll() {

        return semesterDb.findAll();

    }
    @Override
    public SemesterModel getSemesterById(Long id) {
        Optional<SemesterModel> semester = semesterDb.findById(id);
        if (semester.isPresent()) {
            return semester.get();
        }
        return null;
    }

}
