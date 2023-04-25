package com.PASTRACK.PASTRACK.Service.Semester;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.SemesterModel;

public interface SemesterService {
    List<SemesterModel> findAll();
    SemesterModel getSemesterById(Long id);

}