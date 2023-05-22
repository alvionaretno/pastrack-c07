package com.PASTRACK.PASTRACK.Service.Semester;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterRequest;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterResponse;

public interface SemesterService {
    List<SemesterModel> findAll();
    SemesterModel getSemesterById(Long id);
    addSemesterResponse createSemester(addSemesterRequest semesterRequest);
    List<SemesterModel> sortSemester(List<SemesterModel> listSemester);
    // int compare(SemesterModel a, SemesterModel b);

    SemesterModel getCurrentSemester();

    List<SemesterModel> getListSortedSemesterInStudent(StudentModel student);

}