package com.PASTRACK.PASTRACK.Repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

public interface StudentMatpelDB extends JpaRepository<StudentMataPelajaranModel, String> {
    Optional<StudentMataPelajaranModel> findById(Long Id);
    @Query("SELECT SM FROM StudentMataPelajaranModel SM WHERE SM.matapelajaran.peminatan = :peminatan AND SM.student = :student")
    List<StudentMataPelajaranModel> findListStudentMatpelByPeminatan(
        @Param("student") StudentModel student,
        @Param("peminatan") PeminatanModel peminatan
    );
}
