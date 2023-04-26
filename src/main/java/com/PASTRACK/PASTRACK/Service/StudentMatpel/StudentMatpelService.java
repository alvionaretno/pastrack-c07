package com.PASTRACK.PASTRACK.Service.StudentMatpel;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;

public interface StudentMatpelService {
    void generateNilaiStudentMatpel(StudentMataPelajaranModel studentMatpel);
    int getNilaiMatpel(StudentMataPelajaranModel studentMatpel);
    // StudentMataPelajaranModel getStudentMatpel(StudentModel student, MataPelajaranModel matpel);
}
