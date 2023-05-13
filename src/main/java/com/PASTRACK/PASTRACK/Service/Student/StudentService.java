package com.PASTRACK.PASTRACK.Service.Student;

import java.util.List;
import java.util.Optional;

import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;




public interface StudentService {
    Optional<StudentModel> getUserById(String username);

    List<StudentModel> getAllSiswa();

    List<StudentModel> getStudentByTahunMasuk(Long idAngkatan);

    StudentModel getById (Long idSiswa);

    //List<StudentModel> getNotAssignedSiswa();
}