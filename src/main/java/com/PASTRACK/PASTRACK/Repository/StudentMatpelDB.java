package com.PASTRACK.PASTRACK.Repository;

import java.util.Optional;
import java.util.List;

import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;

public interface StudentMatpelDB extends JpaRepository<StudentMataPelajaranModel, String> {
    Optional<StudentMataPelajaranModel> findById(Long Id);
    @Query("SELECT SM FROM StudentMataPelajaranModel SM WHERE SM.matapelajaran.peminatan = :peminatan AND SM.student = :student")
    List<StudentMataPelajaranModel> findListStudentMatpelByPeminatan(
        @Param("student") StudentModel student,
        @Param("peminatan") PeminatanModel peminatan
    );

    @Query("SELECT SM FROM StudentMataPelajaranModel SM WHERE SM.student = :student")
    List<StudentMataPelajaranModel> findListStudentMatpelByStudent(
            @Param("student") StudentModel student
    );

    @Query("SELECT SM FROM StudentMataPelajaranModel SM WHERE SM.matapelajaran = :matapelajaran")
    List<StudentMataPelajaranModel> getStudentsByMataPelajaran(
            @Param("matapelajaran") MataPelajaranModel matapelajaran
    );

    @Query("SELECT SM FROM StudentMataPelajaranModel SM WHERE SM.matapelajaran = :matapelajaran AND SM.student = :student")
    StudentMataPelajaranModel findStudentMatpel(
        @Param("student") StudentModel student,
        @Param("matapelajaran") MataPelajaranModel matapelajaran
    );
}
