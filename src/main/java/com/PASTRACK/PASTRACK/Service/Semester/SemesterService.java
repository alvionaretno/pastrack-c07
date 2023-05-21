package com.PASTRACK.PASTRACK.Service.Semester;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterRequest;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterResponse;

public interface SemesterService {
    List<SemesterModel> findAll();
    SemesterModel getSemesterById(Long id);
    addSemesterResponse createSemester(addSemesterRequest semesterRequest);

    SemesterModel getCurrentSemester();

}