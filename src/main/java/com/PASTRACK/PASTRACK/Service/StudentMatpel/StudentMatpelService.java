package com.PASTRACK.PASTRACK.Service.StudentMatpel;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;

public interface StudentMatpelService {
    StudentMataPelajaranModel generateNilaiStudentMatpel(StudentMataPelajaranModel studentMatpel);
    int getNilaiMatpel(StudentMataPelajaranModel studentMatpel);
    // StudentMataPelajaranModel getStudentMatpel(StudentModel student, MataPelajaranModel matpel);
    List<StudentMataPelajaranModel> getListStudentMatpelByPeminatan(String username, String idPeminatan);
}
