package com.PASTRACK.PASTRACK.Service.StudentKomponen;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;

import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;

@Service
@Transactional
public class StudentKomponenServiceImpl implements StudentKomponenService {

    @Autowired
    private StudentKomponenDB StudentKomponenDB;

    @Override
    public Optional<StudentKomponenModel> getById(Long id) {
        // TODO Auto-generated method stub
        return StudentKomponenDB.findById(id);
    }
}
