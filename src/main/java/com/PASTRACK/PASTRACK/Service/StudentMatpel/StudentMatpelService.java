package com.PASTRACK.PASTRACK.Service.StudentMatpel;

import java.util.List;

import com.PASTRACK.PASTRACK.DashboardSiswaRequest.PencapaianNilaiAllMatpel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.MuridMatpelRequest.getStudentMatpelByPeminatan;

public interface StudentMatpelService {
    StudentMataPelajaranModel generateNilaiStudentMatpel(StudentMataPelajaranModel studentMatpel);
    int getNilaiMatpel(StudentMataPelajaranModel studentMatpel);
    // StudentMataPelajaranModel getStudentMatpel(StudentModel student, MataPelajaranModel matpel);
    List<getStudentMatpelByPeminatan> getListStudentMatpelByPeminatan(String username, String idPeminatan);
    // List<StudentMataPelajaranModel> getListStudentMatpelByPeminatan(String username, String idPeminatan);

    List<StudentMataPelajaranModel> getListStudentMatpelByStudent(String siswaId);

    List<StudentModel> getStudentsByMataPelajaran(MataPelajaranModel mataPelajaranId);

    List<StudentMataPelajaranModel> getListStudentMatpelByPeminatan(String namaPeminatan, StudentModel student);
    StudentMataPelajaranModel getStudentMatpel(StudentModel student, MataPelajaranModel matpel);

    List<StudentMataPelajaranModel> getListStudentMatpelBySemester(SemesterModel semester, String username);
}
